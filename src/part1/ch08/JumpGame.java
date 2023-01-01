package part1.ch08;

import java.util.Arrays;

public class JumpGame {
    private int n;
    private int[][] board;
    private int[][] cache = new int[100][100];
    public JumpGame(int n, int[][] board){
        this.n = n;
        this.board = board;
        //cache 초기화
        for(int i=0; i<cache.length;i++){
            Arrays.fill(cache[i],-1);
        }
    }
    public boolean jump(int row, int col){
        //기저조건 - 판 밖으로 벗어난 경우
        if(row >= n || col>=n) return false;
        //기저조건 - 마지막칸에 도달한 경우
        if(row == n-1 && col == n-1) return  true;

        //나머지: 재귀로
        return jump(row+board[row][col], col) || jump(row, col+board[row][col]);
    }

    public int jump2(int row, int col){
        //기저조건 - 판 밖으로 벗어난 경우
        if(row >= n || col>=n) return 0;
        //기저조건 - 마지막칸에 도달한 경우
        if(row == n-1 && col == n-1) return  1;

        //메모이제이션
        int ret = cache[row][col];
        if(ret!=-1){
            return ret;
        }

        //나머지: 재귀로
        cache[row][col] = Integer.max(jump2(row+board[row][col], col), jump2(row, col+board[row][col]));
        return cache[row][col];
    }
}
