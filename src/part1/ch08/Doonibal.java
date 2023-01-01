package part1.ch08;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Doonibal {
    int n, d, p, q; // n: 마을의 수, d: 경과날짜, p: 교도소가 있는 마을, q: 확률을 계산할 마을
    int[][] connected; // 마을 간 연결관계를 나타내는 행렬
    int[] deg; //마을 i와 연결된 마을의 수
    double cache[][];

    public Doonibal(int d, int p, int q, int[][] adjacencyMatrix){
        this.connected = adjacencyMatrix;
        this.n = this.connected.length;
        this.deg = new int[this.n];
        this.d = d;
        this.p = p;
        this.q = q;

        this.cache = new double[this.n][this.d];

        for(int i=0; i<this.n;i++){
            this.deg[i] = IntStream.of(this.connected[i]).sum();
        }
        for(int i=0; i<this.n;++i){
            Arrays.fill(this.cache[i], -1);
        }

    }
    double search(List<Integer> path){
        if(path.size() == d+1){
            if(path.get(path.size()-1)!=q) return 0;
            double ret = 1.0;
            for(int i=0; i+1<path.size(); ++i){
                ret /= deg[path.get(i)];
            }
            return ret;
        }
        double ret = 0;
        for(int there=0; there<n; ++there){
            if(connected[path.get(path.size()-1)][there]==1){
                path.add(there);
                ret += search(path);
                path.remove(path.size()-1);
            }
        }
        return ret;
    }

    double search2(int here, int days){
        //기저사례: d일이 지남
        if(days == d) return (here==q ? 1.0 : 0.0);
        //메모이제이션

        if(cache[here][days]>-0.5) return cache[here][days];
        cache[here][days] = 0.0;
        for(int there=0; there<n; ++there){
            if(connected[here][there]==1){
                cache[here][days] += search2(there, days+1)/deg[here];
            }
        }
        return cache[here][days];
    }
}
