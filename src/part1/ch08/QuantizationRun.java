package part1.ch08;

public class QuantizationRun {
    public static void main(String[] args){
        int[] A = {1, 744, 755, 4, 897, 902, 890, 6, 777};
//        int[] A = {1,2,3,2,2,2,3,3,3,1,1,1,1};
        Quantization quantization = new Quantization(A);
        System.out.println(quantization.quantize(0, 3));
    }
}
