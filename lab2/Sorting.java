import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Sorting{

    public static ArrayList<Integer> mergeSortSeq(ArrayList<Integer> list){
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
        ArrayList<Integer> firstSorted = mergeSortSeq(firstHalf);
        ArrayList<Integer> secondSorted = mergeSortSeq(secondHalf);
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
        //System.out.println("Unsorted: " + listToSort);
        //System.out.println("Sorted: " + Sorting.mergeSortSeq(listToSort));
        Sorting.mergeSortSeq(listToSort);




    }

}
