package part2.ch16;

public class BitmaskSample {
    public static void main(String[] args){
        int fullPizza = (1<<20)-1;
        int toppings = 0b00101010101001000000;
        System.out.println("Full Pizza: "+ Integer.toBinaryString(fullPizza));

        if((toppings & (1<<6))!=0){
            System.out.println("Pepperoni is in");
        }
        toppings &= ~(1<<6);
        if((toppings & (1<<6))==0){
            System.out.println("Pepperoni removed");
        }

        toppings ^= (1<<6);
        if((toppings & (1<<6))!=0){
            System.out.println("Pepperoni is added again");
        }
        int a = 0b11010101000;
        int b = 0b10101110111;

        //합집합
        int added = a|b;
        //교집합
        int intersection = a&b;
        //차집합
        int removed = a & ~b;
        //둘중 하나에만 포함된 원소집합
        int toggled = a ^ b;

        System.out.println("a: "+a);
        System.out.println("number of bits in a: "+Integer.bitCount(a));
        System.out.println("least bit of a: "+ Integer.numberOfTrailingZeros(a));
        System.out.println("least bit of b: "+ Integer.numberOfTrailingZeros(b));

        //피자의 토핑들 순회하기
        int pizza = 0b101001;
        System.out.println("모든 원소 순회");
        for(int subset=pizza; subset>0; subset=((subset-1)&pizza)){
            System.out.println(Integer.toBinaryString(subset));
        }
        System.out.println("모든 원소 순회2");
        int subset=pizza;
        while(subset>0){
            System.out.println(Integer.toBinaryString(subset));
            subset = pizza&(subset-1);
        }
        System.out.println("작은거부터 하나씩 꺼내기");
        subset=pizza;
        while(subset>0){
            System.out.println(Integer.toBinaryString(subset));
            subset &= (subset-1);
        }

    }
}
