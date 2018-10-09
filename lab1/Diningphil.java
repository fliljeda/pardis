/**
 * A simple example of the Dining philosopher problem.
 * For a detailed explanation of the solution to the problem, read DiningPhilosopherArgument.txt
 * 
 */

import java.util.concurrent.*;
public class Diningphil{
   // Fork object, works as a lock.
    private class Fork {}

    private class Philosopher implements Runnable{
        int name;
        Fork leftFork;
        Fork rightFork;

        public Philosopher(int name, Fork left, Fork right){
            this.name = name;
            this.leftFork = left;
            this.rightFork = right;
        }
        public void run(){
            while (true){  //Synchronize on a Fork object is equivalent to "owning" the fork 
            synchronized(leftFork){
                synchronized(rightFork){ // Needs to "own" two forks to eat
                    System.out.println(""+ name + " is eating");
                }
            }
        }
            
        }

    }
    public Diningphil(int nr){
        Thread[] threads = new Thread[nr];
        Fork[] forks = new Fork[nr];
        Philosopher[] philos = new Philosopher[nr];
        for(int i = 0; i < nr; i++){
            forks[i] = new Fork();
        }
        for(int i = 0; i < nr-1; i++){
            philos[i] = new Philosopher(i, forks[i], forks[i+1]);
        }
            philos[nr-1] = new Philosopher(nr-1, forks[0], forks[nr-1]); // Takes forks in different order, this breaks the symmetry

        for(int i = 0; i < nr; i++){
            threads[i] = new Thread(philos[i]);
            threads[i].start();
        }

    }

    public static void main(String[] args){
        Diningphil d = new Diningphil(5);
    }

}