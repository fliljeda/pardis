import java.util.concurrent.*;
public class Diningphil{
   
    private class Fork {
        int nrPhil;
        Object lock = new Object();
        Philosopher owner;

        public Fork(){
            owner = null;
        
        }

        private void setOwner(Philosopher phil){
            synchronized(lock){
                owner = phil;

            }
        }
    

    }

    private class Philosopher implements Runnable{
        int name;
        Fork leftFork;
        Fork rightFork;

        public Philosopher(int name, Fork left, Fork right){
            this.name = name;
            this.leftFork = left;
            this.rightFork = right;
        }
        public void run(){  // 
            leftFork.setOwner(this);
            rightFork.setOwner(this);
            if(leftFork.owner == this && rightFork.owner == this){
                System.out.println(""+ name + " is eating");
            }
        }

    }
    public Diningphil(int nr){
        Fork[] forks = new Fork[nr];
        Philosopher[] philos = new Philosopher[nr];
        for(int i = 0; i < nr; i++){
            forks[i] = new Fork();
        }
        for(int i = 0; i < nr-1; i++){
            philos[i] = new Philosopher(i, forks[i], right[i+1%nr]);
        }
            philos[nr-1] = new Philosopher(nr-1, forks[0], forks[nr-1]); // Takes forks in different order


    }

}