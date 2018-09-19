import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class mergeSortStreamsLambda {
    public static final int THRESHOLD = 100000; // What size we switch to sequential


    public static class Bound{
        public int start;
        public int end;
        public Bound(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public static void mergeSort(Integer[] list, int start, int end , Integer[] returnList){
        int size = end - start + 1;
        //System.out.println(size);
        if (size == 1){
           // System.out.println("START:" + start);
            returnList[start] = list[start];
            return;
        }
        if (size < THRESHOLD){
            Sorting.mergeSortSeq(list, start, end, returnList);
            return;
        }
        int middle = start + (size-1)/2;

        ArrayList<Bound> bounds = new ArrayList<Bound>();
        bounds.add(new Bound(start, middle));
        bounds.add(new Bound(middle+1, end));

        bounds.parallelStream().forEach(
            b -> mergeSort(list, b.start, b.end, returnList));
      
        int firstIndex = start;
        int sndIndex = middle+1;
        int writeIndex = start; 
        
        while(firstIndex != middle+1 && sndIndex !=end+1){
            if (returnList[firstIndex] > returnList[sndIndex]){
                list[writeIndex] = returnList[sndIndex];
                sndIndex++;
                writeIndex++;
            }
            else{
                list[writeIndex] = returnList[firstIndex];
                firstIndex++;
                writeIndex++;
            }
        }

        for(int i = firstIndex; i < middle+1; i++){
            list[writeIndex] = returnList[firstIndex];
            writeIndex++;
            firstIndex++;
        }
        for(int i = sndIndex; i < end+1; i++){
            list[writeIndex] = returnList[sndIndex];
            writeIndex++;
            sndIndex++;
        }
        for(int i = start; i < end+1; i++){
            returnList[i] = list[i];
        }

    }

    public static void main(String[] args) throws Exception{
        int size = 0;
        if(args.length < 1){
            size = 10000;
        }
        try{
            size = Integer.parseInt(args[0]);
        }catch(Exception e){
            System.out.println("Could not parse size. Using 10000 as replacement");
            size = 10000;
        }
        ArrayList<Integer> l = new GenerateArrays(false).generateArray(size,GenerateArrays.Mode.REVERSE);
        //System.out.println("Unsorted: "+ l);
        Integer[] arrToSort = new Integer[l.size()];
        l.toArray(arrToSort); 
        Integer[] retArr = new Integer[l.size()];

        mergeSort(arrToSort,0,arrToSort.length-1,retArr);
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        for (int i = 0; i < retArr.length; i++){
            l2.add(retArr[i]);
        }
        //System.out.println("Sorted: " + l2);
        
        //System.out.println("NOT SORTED: " + l);

        //System.out.println("Unsorted: "+ l);
        //System.out.println("Sorted: " + m.call());
        

    }
}