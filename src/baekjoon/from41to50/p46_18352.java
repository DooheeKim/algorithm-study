package baekjoon.from41to50;

import java.io.*;
import java.util.*;

public class p46_18352 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int numCities = Integer.parseInt(st.nextToken());
        int numRoads = Integer.parseInt(st.nextToken());
        int objectivePathLength = Integer.parseInt(st.nextToken());
        int departCity = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[numCities+1];
        for(int i=1; i<=numCities; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<numRoads; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            graph[n1].add(n2);
        }
        List<Integer> counter = numPaths(numCities, graph, departCity, objectivePathLength);
        Collections.sort(counter);
        for(int city:counter){
            bw.write(Integer.toString(city));
            bw.write("\n");
        }
        bw.close();
        br.close();
    }
    private static List<Integer> numPaths(int numCities, List<Integer>[] graph, int departCity, int objectivePathLength){
        boolean[] visited = new boolean[numCities+1];
        Queue<int[]> queue = new LinkedList<>(); //[도시명, 이동거리]
        queue.add(new int[]{departCity, 0});
        visited[departCity] = true;
        List<Integer> paths = new ArrayList<>();
        while(!queue.isEmpty()){
            ///BFS를 이용한 탐색
            int[] currPath = queue.poll();
            if(currPath[1]==objectivePathLength) paths.add(currPath[0]);
            if(currPath[1]>objectivePathLength) break;
            for(Integer city:graph[currPath[0]]){
                if(visited[city]) continue;
                visited[city]=true;
                queue.add(new int[]{city, currPath[1]+1}); // 거리 하나 증가
            }
        }
        if(paths.size()==0) paths.add(-1);
        return paths;
    }
}
