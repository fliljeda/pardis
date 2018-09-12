import java.util.*;
public class GenerateArrays{
    public enum Mode{RANDOM, REVERSE, SORTED}

    public GenerateArrays(boolean test){
        if(test){
            System.out.println(generateArray(50));
            System.out.println(generateArray(50, Mode.REVERSE));
            System.out.println(generateArray(50, Mode.SORTED));
        }

    }

    public ArrayList<Integer> generateArray(int size){
        return generateArray(size, Mode.RANDOM);
    }

    public ArrayList<Integer> generateArray(int size, Mode mode){
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        if(mode == Mode.RANDOM){
            Random rand = new Random();
            for(int i = 0; i < size; i++){
                list.add(rand.nextInt(size));
            }
            return list;
        }else{
            for(int i = 0; i < size; i++){
                int num = (mode == Mode.REVERSE) ? size - i : i;
                list.add(num);
            }
            return list;
        }
    }

    public static void main(String[] args){
        new GenerateArrays(true);
    }
}
