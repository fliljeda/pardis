import java.util.ArrayList;

import com.sun.javafx.image.impl.General;

public class SeqSort{

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> list){
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
        ArrayList<Integer> firstSorted = mergeSort(firstHalf);
        ArrayList<Integer> secondSorted = mergeSort(secondHalf);
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
        GenerateArrays g = new GenerateArrays(false);
        ArrayList<Integer> listToSort = g.generateArray(21);
        System.out.println("Unsorted: " + listToSort);
        System.out.println("Sorted: " + SeqSort.mergeSort(listToSort));




    }

}