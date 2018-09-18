import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class mergeSortForkJoin extends RecursiveTask<ArrayList<Integer>>{
    static ForkJoinPool pool = new ForkJoinPool();
    public ArrayList<Integer> list;
    
    public mergeSortForkJoin(ArrayList<Integer> list){
        this.list = list;
    
    }
    @Override
    public ArrayList<Integer> compute(){
        
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

        mergeSortForkJoin first = new mergeSortForkJoin(firstHalf);
        mergeSortForkJoin second = new mergeSortForkJoin(secondHalf);
        pool.execute(first);
        pool.execute(second);
        ArrayList<Integer> firstSorted = first.join();
        ArrayList<Integer> secondSorted = second.join();

    
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
        mergeSortForkJoin m = new mergeSortForkJoin(l);
        //System.out.println("NOT SORTED: " + l);
        System.out.println("SORTED: " + pool.invoke(m));
        //System.out.println("Unsorted: "+ l);
        //System.out.println("Sorted: " + m.call());
        

    }
}