package part1.ch07;

import java.util.Queue;

public class QuadTree {
    char decompressed[][];
    int i = 0;
    String compressedString;
    public QuadTree(String string){
        this.compressedString = string;
        this.decompressed = new char[32][32];
    }

    void decompress(int y, int x, int size){
        char head = compressedString.charAt(i++);
//        i+=1;
        if(head == 'b' || head == 'w'){
            for(int dy=0;dy<size;++dy){
                for(int dx=0;dx<size;++dx){
                    decompressed[y+dy][x+dx] = head;
                }
            }
        }else{
            int half = size/2;
            decompress(y, x, half);
            decompress(y, x+half, half);
            decompress(y+half, x, half);
            decompress(y+half, x+half, half);
        }
    }
    String reverse(){
        char head = compressedString.charAt(i++);
        if(head=='b' || head == 'w')
            return Character.toString(head);
        String upperLeft = reverse();
        String upperRight = reverse();
        String lowerLeft = reverse();
        String lowerRight = reverse();

        return "x"+lowerLeft+lowerRight+upperLeft+upperRight;
    }

}
