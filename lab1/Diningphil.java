import java.util.concurrent.*;
public class Diningphil{
   
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
            while (true){  // 
            synchronized(leftFork){
                synchronized(rightFork){
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
            philos[nr-1] = new Philosopher(nr-1, forks[0], forks[nr-1]); // Takes forks in different order

        for(int i = 0; i < nr; i++){
            threads[i] = new Thread(philos[i]);
            threads[i].start();
        }

    }

    public static void main(String[] args){
        Diningphil d = new Diningphil(5);
    }

}