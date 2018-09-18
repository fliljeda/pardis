import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class mergeSortExecService implements Callable<Integer[]>{
    static ExecutorService exec = Executors.newCachedThreadPool();
    public Integer[] list;
    public int start;
    public int end;
    public Integer[] retList;
    
    public mergeSortExecService(Integer[] list, int start, int end, Integer[] retList){
        this.list = list;
        this.start = start;
        this.end = end;
        this.retList= retList;
    
    }

    public Integer[] call() throws Exception{
    
        int size = end - start + 1;
        if (size == 1){
           // System.out.println("START:" + start);
            returnList[start] = list[start];
            return returnList;
        }
        int middle = start + (size-1)/2;
      

        Future<Integer[]> firstSortedFuture = exec.submit(new mergeSortExecService(list, start, middle, returnList));
        Future<Integer[]> secondSortedFuture = exec.submit(new mergeSortExecService(list, middle+1, end,returnList));
        
        Integer[] firstSorted = firstSortedFuture.get();
        Integer[] secondSorted = secondSortedFuture.get();
        
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
        return returnList;
     
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
        System.out.println("Unsorted: "+ l);
        Integer[] arrToSort = new Integer[l.size()];
        l.toArray(arrToSort); 
        Integer[] retArr = new Integer[l.size()];
        mergeSortExecService m = new mergeSortExecService(arrToSort,0,l.size()-1,retArr);
        retArr = m.call();
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        for (int i = 0; i < retArr.length; i++){
            l2.add(retArr[i]);
        }
  
        System.out.println("Sorted: " + l2);
        m.call();
        m.exec.shutdown();
    }

   
} 
