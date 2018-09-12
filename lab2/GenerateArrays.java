public class GenerateArrays{
    public enum Mode{RANDOM, REVERSE, SORTED}

    public GenerateArrays(){

    }

    public int[] generateArray(int size){
        return generateArray(size, Mode.RANDOM);
    }

    public int[] generateArray(int size, Mode mode){
        return null;
    }

    public static void main(String[] args){
        new GenerateArrays();
    }
}
