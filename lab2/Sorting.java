import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Sorting{

    public static void mergeSortSeq(Integer[] list, int start, int end, Integer[] returnList){
        int size = end - start + 1;
        //System.out.println(size);
        if (size == 1){
           // System.out.println("START:" + start);
            returnList[start] = list[start];
            return;
        }
        int middle = start + (size-1)/2;
      
        mergeSortSeq(list, start, middle, returnList);
        mergeSortSeq(list, middle+1, end, returnList);
        
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


        //System.out.print("Start: "+ start +" End: " + end +  "[" );
       // for(int i = 0; i<returnList.length; i++){
           // System.out.print(" " + returnList[i] + ",");
       // }
       // System.out.println("]");
        
    }
   


    public static void main(String[] args){
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
        GenerateArrays g = new GenerateArrays(false);
        ArrayList<Integer> listToSort = g.generateArray(size);

        Integer[] arrToSort = new Integer[listToSort.size()];
        listToSort.toArray(arrToSort);
        Integer[] retArr = new Integer[listToSort.size()];
        
        //System.out.println("Unsorted: " + listToSort);
        ArrayList<Integer> l =  new ArrayList<Integer>(listToSort.size());
        Sorting.mergeSortSeq(arrToSort, 0 , listToSort.size() - 1, retArr);
        for (int i = 0; i < retArr.length; i++){
            l.add(retArr[i]);
        }
        // System.out.println("Sorted: "+ l);

        // // For validation
        // Integer[] sorted = new Integer[listToSort.size()];
        // listToSort.toArray(sorted);
        // Arrays.sort(sorted);
        // System.out.print("Arrays.sort = [ ");
        // for(int i = 0; i<sorted.length;i++){
        //     System.out.print(" "+ sorted[i]+ ",");
        // }
        // System.out.println("]");


    }

}
