package part1.ch07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Multiply {
    static void normalize(List<Integer> num){
        num.add(0);

        for(int i=0; i+1<num.size(); i++){
            if(num.get(i)<0){
                int borrow = (Math.abs(num.get(i))+9)/10;
                num.set(i+1, num.get(i+1)-borrow);
                num.set(i, num.get(i)+borrow*10);
            }else{
                num.set(i+1, num.get(i+1)+num.get(i)/10);
                num.set(i, num.get(i)%10);
            }
        }
        while(num.size()>1 && num.get(num.size()-1)==0) num.remove(num.size()-1);
    }
    static List<Integer> multiply(List<Integer> a, List<Integer> b){
        Integer[] tmp = new Integer[a.size()+b.size()+1];
        Arrays.fill(tmp, 0);
        List<Integer> c = new ArrayList(Arrays.asList(tmp));

        for(int i=0; i<a.size();i++){
            for(int j=0; j<b.size(); j++){
                c.set(i+j, a.get(i)*b.get(j)+c.get(i+j));
            }
        }
        normalize(c);
        return c;
    }

    static void addTo(List<Integer> a, List<Integer> b, int k){
        // b*10^k
        Integer[] tmp = new Integer[k];
        Arrays.fill(tmp, 0);
        b.addAll(0, new ArrayList(Arrays.asList(tmp)));
//        System.out.println("b: "+b);
        while(a.size()<b.size()) a.add(0);
        while(b.size()<a.size()) b.add(0);
        a.add(0);

        for(int i=0; i<b.size(); i++){ //자리맞추기 용도로 0하나 추가했으니 size-1까지 돌면됨
            int carrier = (a.get(i)+ b.get(i))/10;
            int remainder = (a.get(i)+ b.get(i))%10;

            a.set(i, remainder);
            a.set(i+1, a.get(i+1)+carrier);
        }
        while(a.size()>1 && a.get(a.size()-1)==0) a.remove(a.size()-1);
    }

    static void subFrom(List<Integer> a, List<Integer> b){
        for(int i=0; i<a.size(); i++){
            if(i>=b.size()) b.add(0);
            if(a.get(i)<b.get(i)){
                //빌려오기
                a.set(i+1, a.get(i+1)-1);
                a.set(i, a.get(i)+10);
            }
            a.set(i, a.get(i)-b.get(i));
        }
    }

    static List<Integer> karatsuba(List<Integer> a, List<Integer> b){
        int an = a.size(), bn = b.size();
        if(an<bn) return karatsuba(b, a);

        //기저사례: a나 b가 비어있는 경우
        if(an==0||bn==0) return new ArrayList<>(0);
        //기저사례: a랑 b가 충분히 짧으면 그냥 곱셈으로 한다
        if(an<=5) return multiply(a, b); //책에서는  50이었는데 검수 귀찮으니까 그냥 5로
        int half = an/2;
        /// sublist는 부모가 바뀌면 자식도 같이 바뀌게 된다
        // new ArrayList( sublist) 와 같이 사용해야 이러한 문제를 피할 수  있다.
        List<Integer> a0 = new ArrayList<>(a.subList(0, half));
        List<Integer> a1 = new ArrayList<>(a.subList(half, a.size()));
        List<Integer> b0 = new ArrayList<>(b.subList(0, Integer.min(b.size(), half)));
        List<Integer> b1 = new ArrayList<>(b.subList(Integer.min(b.size(), half), b.size()));

        List<Integer> z2 = karatsuba(a1, b1);
        List<Integer> z0 = karatsuba(a0, b0);
        addTo(a0, a1, 0); addTo(b0, b1, 0);
        List<Integer> z1 = karatsuba(a0, b0);
        subFrom(z1, z0);
        subFrom(z1, z2);

        List<Integer> ret = new ArrayList<>();
        addTo(ret, z0, 0);
        addTo(ret, z1, half);
        addTo(ret, z2, half+half);
        return ret;
    }

    public static void main(String[] args){
        List<Integer> a = new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9));
        List<Integer> b = new ArrayList(Arrays.asList(9,9,9,9,9,9,9,9,9,9,9,9,9));
        a.add(0);
        System.out.println(multiply(a,b));
        System.out.println(karatsuba(a,b));
    }
}
