package baekjoon.from21to30;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class p27_2178 {
    static Integer[][] arr;
    static boolean[][] visited;
    static int N;
    static int M;
    private static class Tuple{
        int row;
        int col;
        int depth;
        private Tuple(int row, int col, int depth){
            this.row = row;
            this.col = col;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new Integer[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            String[] tmp = br.readLine().split("");
            arr[i] = Arrays.stream(tmp).map(x->Integer.parseInt(x)).toArray(Integer[]::new);
        }

        bw.write(Integer.toString(bfs()));
        bw.close();
        br.close();
    }
    public static int bfs(){
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(0,0, 1));
        visited[0][0] = true;

        int ret = 0;
        while(!queue.isEmpty()){
            Tuple node = queue.poll();
            if(node.row==N-1 && node.col==M-1){
                ret = node.depth;
                break;
            }
            //node 주변으로 사방의 노드를 더하기
            //위
            if (node.row - 1 >= 0 && arr[node.row-1][node.col]==1 &&!visited[node.row-1][node.col]) {
                queue.add(new Tuple(node.row-1, node.col, node.depth+1));
                visited[node.row-1][node.col] = true;
            }
            //오른쪽
            if (node.col + 1 < M && arr[node.row][node.col+1]==1 &&!visited[node.row][node.col+1]) {
                queue.add(new Tuple(node.row, node.col+1, node.depth+1));
                visited[node.row][node.col+1] = true;
            }
            //왼쪽
            if (node.col - 1 >= 0 && arr[node.row][node.col-1]==1 && !visited[node.row][node.col-1]) {
                queue.add(new Tuple(node.row, node.col-1, node.depth+1));
                visited[node.row][node.col-1] = true;
            }
            //하단
            if (node.row + 1 < N && arr[node.row+1][node.col]==1 && !visited[node.row+1][node.col]) {
                queue.add(new Tuple(node.row+1, node.col, node.depth+1));
                visited[node.row+1][node.col] = true;
            }
        }
        return ret;
    }
}
