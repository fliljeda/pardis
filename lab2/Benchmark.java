import java.util.*;
public class Benchmark{

    public ArrayList<Integer> gen1000;
    public ArrayList<Integer> gen100000;
    public ArrayList<Integer> gen4000000;

    public Benchmark(){

        GenerateArrays g = new GenerateArrays(false);
        gen1000 = g.generateArray(1000, GenerateArrays.Mode.REVERSE);
        gen100000 = g.generateArray(100000, GenerateArrays.Mode.REVERSE);
        gen4000000 = g.generateArray(4000000, GenerateArrays.Mode.REVERSE);

        Sequential();
        ExecutorService();
        ForkAndJoin();
        StreamLambda();
    }

    public void Sequential(){
        Integer[] arrToSort = new Integer[gen1000.size()];
    }
    public void ExecutorService(){
    }
    public void ForkAndJoin(){
    }
    public void StreamLambda(){
    }
    
    public static void main(String args[]){
        new Benchmark();
    }
}
