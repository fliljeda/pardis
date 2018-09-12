import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class mergeSortExecService implements Callable<ArrayList<Integer>>{
    static ExecutorService exec = Executors.newCachedThreadPool();
    public ArrayList<Integer> list;
    
    public mergeSortExecService(ArrayList<Integer> list){
        this.list = list;
    
    }

    public ArrayList<Integer> call() throws Exception{
    
        if (list.size() == 1){return list;}
    
        int middle = list.size()/2;
        ArrayList<Integer> firstHalf = new ArrayList<Integer>(middle+1);
        ArrayList<Integer> secondHalf = new ArrayList<Integer>(middle+1);
    
        for (int i = 0; i < middle; i++ ){
            firstHalf.add(list.get(i));
        }
    
        for(int i = middle; i < list.size(); i++){
            secondHalf.add(list.get(i));
        }
    
        Future<ArrayList<Integer>> firstSortedFuture = exec.submit(new mergeSortExecService(firstHalf));
        Future<ArrayList<Integer>> secondSortedFuture = exec.submit(new mergeSortExecService(secondHalf));
        
        ArrayList<Integer> firstSorted = firstSortedFuture.get();
        ArrayList<Integer> secondSorted = secondSortedFuture.get();
    
        ArrayList retList = new ArrayList();    
        while(firstSorted.size() > 0 && secondSorted.size() > 0){
            if (firstSorted.get(0) > secondSorted.get(0)){
                retList.add(secondSorted.get(0));
                secondSorted.remove(0);
            }
            else{
                retList.add(firstSorted.get(0));
                firstSorted.remove(0);
            }
        }

        for(Integer v : firstSorted){
            retList.add(v);
        }
        for(Integer v : secondSorted){
            retList.add(v);
        }

        return retList;
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

        ArrayList<Integer> l = new GenerateArrays(false).generateArray(size);
        mergeSortExecService m = new mergeSortExecService(l);
        //System.out.println("Unsorted: "+ l);
        //System.out.println("Sorted: " + m.call());
        m.call();
        m.exec.shutdown();
    }

   
} 
