package part1.ch06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Boggle {
    public static boolean myHasWord(int y, int x, String word, List<List<String>> table) {
        //base case
        // 1. 남은 단어가 없으면 ("") true 반환
        // 2. 찾고자 하는 범위가 정상적인 범위 밖일 때 false 반환
        // 3. 해당 인덱스가 만들고자 하는 글자의 첫글자가 아닐 때 false 반환
        if (word.length() == 0) {
            return true;
        }
        if (y < 0 || y >= table.size() || x < 0 || x >= table.get(0).size()) {
            return false;
        }
        if (!word.startsWith(table.get(y).get(x))) {
            return false;
        }


        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (myHasWord(y + j, x + i, word.substring(1), table)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 예제 답안
    static final int[] dx = {-1, -1, -1, 1, 1, 1, 0, 0};
    static final int[] dy = {-1, 0, 1, -1, 0, 1, -1, 1};

    public static boolean hasWord(int y, int x, String word, List<List<String>> table) {
        //기저사례1: 시작 위치가 범위 밖이면 실패
        if (y < 0 || y >= table.size() || x < 0 || x >= table.get(0).size()) {
            return false;
        }
        // 기저사례2: 첫글자가 일치하지 않으면 실패
//        if (!table.get(y).get(x).equals(word.charAt(0))) {
//            return false;
//        }
        // 이거 돌리면 if문에 무조건 걸림(String과 Char의 값 비교는 항상 False 반환 -> equals로 비교 못함)
        if (!word.startsWith(table.get(y).get(x))) {
            return false;
        }
        // 기저사례3: 단어 길이가 1이면 성공
        if (word.length() == 1) {
            return true;
        }
        for (int direction = 0; direction < 8; ++direction) {
            int nextY = y + dy[direction], nextX = x + dx[direction];
            if (hasWord(nextY, nextX, word.substring(1), table)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<String>> table = new ArrayList<>();
        table.add(new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e")));
        table.add(new ArrayList<String>(Arrays.asList("e", "f", "g", "h", "i")));
        table.add(new ArrayList<String>(Arrays.asList("j", "k", "l", "m", "n")));
        table.add(new ArrayList<String>(Arrays.asList("o", "p", "q", "r", "s")));
        table.add(new ArrayList<String>(Arrays.asList("t", "u", "v", "w", "x")));
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.println(myHasWord(i, j, "chnsx", table));
//            }
//        }
        System.out.println(myHasWord(0, 2, "chnsx", table));
        System.out.println(hasWord(0, 2, "chnsx", table));
    }
}