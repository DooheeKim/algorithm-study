package baekjoon.from51to60;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class p52_1043 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        //첫째 줄 : 파티와 사람들의 수
        st = new StringTokenizer(br.readLine());
        int peopleCount = Integer.parseInt(st.nextToken());
        int partyCount = Integer.parseInt(st.nextToken());
        int[] dontKnowTruth = new int[peopleCount+1];// 진실을 아는 사람은 0, 나머지는 다른 그룹으로
        for(int i=0; i<=peopleCount; i++){
            // union-find 를 사용하기 위한 초기화
            dontKnowTruth[i] = i;
        }

        //둘째 줄 : 진실을 아는 사람들의 수
        st = new StringTokenizer(br.readLine());
        int dontKnowTruthCount = Integer.parseInt(st.nextToken());
        for(int i=0; i<dontKnowTruthCount; i++){
            int personIndex = Integer.parseInt(st.nextToken());
            dontKnowTruth[personIndex] = 0;
        }

        //나머지 줄: 파티와 파티에 참여하는 사람 정보 --- 동일한 파티에 참여한 사람끼리 묶어서
        // 진실을 아는 사람과 한번이라도 엮인 사람의 그룹을 0으로 만들자
        List<Integer>[] party = new ArrayList[partyCount];
        for(int i=0; i<partyCount; i++){
            party[i] = new ArrayList<Integer>();

            st = new StringTokenizer(br.readLine());
            int partyParticipants = Integer.parseInt(st.nextToken());
            int firstPersonIndex = Integer.parseInt(st.nextToken());
            party[i].add(firstPersonIndex);
            for(int j = 1; j<partyParticipants; j++){
                int personIndex = Integer.parseInt(st.nextToken());
                party[i].add(personIndex);
                uniteGroup(dontKnowTruth, firstPersonIndex, personIndex);
            }
        }

        int liablePartyCount = 0;
        for(int i=0; i<partyCount; i++){
            int personIdx = party[i].get(0);
            int parent = findParent(dontKnowTruth, personIdx);
            if(parent!=0) liablePartyCount+=1;
        }
        bw.write(Integer.toString(liablePartyCount));
        bw.close();
        br.close();
    }
    public static int findParent(int[] parent, int node){
        if(node == parent[node]) return node;
        return findParent(parent, parent[node]);
    }
    public static void uniteGroup(int[] parent, int node1, int node2){
        node1 = findParent(parent, node1);
        node2 = findParent(parent, node2);
        if(node1<node2) parent[node2] = node1;
        else parent[node1] = node2;
    }
}
