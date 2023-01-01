package part1.ch08;

public class JumpGameRunner {

    public static void main(String[] args){
        int[][] gameBoard = {{2,5,1,6,1,4,1},
                {6,1,1,2,2,9,3},
                {7,2,3,2,1,3,1},
                {1,1,3,1,7,1,2},
                {4,1,2,3,4,1,2},
                {3,3,1,2,3,4,1},
                {1,5,2,9,4,7,-1}};
        JumpGame jumpGame = new JumpGame(7, gameBoard);
        System.out.println(jumpGame.jump(0,0));
        System.out.println(jumpGame.jump2(0,0));
    }
}
