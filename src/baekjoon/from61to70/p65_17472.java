package baekjoon.from61to70;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p65_17472 {
    static PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int rowsCount = Integer.parseInt(st.nextToken());
        int colsCount = Integer.parseInt(st.nextToken());
        int[][] map = new int[rowsCount][colsCount];
        for(int i=0; i<rowsCount; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<colsCount; j++){
                map[i][j] = Integer.parseInt(st.nextToken())*10; // 섬을 1~6으로 분리할거라서 1이 아닌 큰 수넣기
            }
        }
        int island = 1;
        for(int i=0; i<rowsCount; i++){
            for(int j=0; j<colsCount; j++){
                if(map[i][j]==10){
                    dfsFindIsland(map, i, j, island);
                    island++;
                }

            }
        }
        // Union - Find 구현을 위한 parent array
        int[] parent = new int[island];
        for(int i=0; i<island; i++){
            parent[i] = i;
        }

        for(int i=0; i<rowsCount; i++){
            for(int j=0; j<colsCount; j++){
                if(map[i][j]!=0){
                    dfsFindHorizontalBridge(map, i, j, map[i][j], 0);
                    dfsFindVerticalBridge(map, i, j, map[i][j], 0);
                }
            }
        }

        int totalBridgeLength = 0;
        while(!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if(findParent(edge.start,parent)==findParent(edge.end,parent)) continue; //이미 같은 그룹이면 다리길이 구할필요 없음
            unite(edge.start, edge.end, parent);
            totalBridgeLength += edge.weight;
        }
        boolean sameParentFlag = true;

        int parent1 = findParent(1, parent);
        for(int i=2; i<island; i++){
            if(parent1!=findParent(i, parent)){
                sameParentFlag = false;
                break;

            }
        }
        if(sameParentFlag) bw.write(Integer.toString(totalBridgeLength));
        else bw.write("-1");
        bw.close();
        br.close();
    }

    private static int findParent(int node, int[] parent){
        if(node==parent[node]) return node;
        return parent[node] = findParent(parent[node], parent);
    }

    private static void unite(int n1, int n2, int[] parent) {
        n1 = findParent(n1, parent);
        n2 = findParent(n2, parent);
        if(n1<n2) parent[n2] = n1;
        else parent[n1] = n2;
    }

    private static void dfsFindIsland(int[][] map, int row, int col, int island){
        // 기저조건: 만약에 범위 밖이거나 map[row][col] = 0이거나 이미 칠해졌으면
        if(row<0 || row>=map.length || col<0 || col>=map[row].length || map[row][col]!=10) return;

        map[row][col] = island;
        dfsFindIsland(map, row-1, col, island);
        dfsFindIsland(map, row, col-1, island);
        dfsFindIsland(map, row+1, col, island);
        dfsFindIsland(map, row, col+1, island);
    }

    private static void dfsFindHorizontalBridge(int[][] map, int row, int col, int island, int length){
        //기저조건 1: row/col  이 범위 밖이면 섬이 다리가 안됐다는 뜻 -> 그냥 종료
        if(col>=map[row].length) return;
        //기저조건 2: length>0 인데 island == map[row][col] 이면 같은 섬이라는 뜻 -> 그냥 리턴
        if(length>0 && (island == map[row][col])) return;
        //길이 미달인 다리 -> return
        if(length>0 && length<3 && (map[row][col]!=0)) return;
        if(length>=3 && (map[row][col]!=0)){
            Edge edge = new Edge(island, map[row][col], length-1);
            priorityQueue.add(edge);
            return;
        }
        dfsFindHorizontalBridge(map, row, col+1, island, length+1);
    }
    private static void dfsFindVerticalBridge(int[][] map, int row, int col, int island, int length){
        //기저조건 1: row/col  이 범위 밖이면 섬이 다리가 안됐다는 뜻 -> 그냥 종료
        if(row>=map.length) return;
        //기저조건 2: length>0 인데 island == map[row][col] 이면 같은 섬이라는 뜻 -> 그냥 리턴
        if(length>0 && (island == map[row][col])) return;
        //길이 미달인 다리 -> return
        if(length>0 && length<3 && (map[row][col]!=0)) return;
        if(length>=3 && (map[row][col]!=0)){
            Edge edge = new Edge(island, map[row][col], length-1);
            priorityQueue.add(edge);
            return;
        }
        dfsFindVerticalBridge(map, row+1, col, island, length+1);
    }
    private static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        private Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
