import java.util.concurrent.*;
public class Exercise2{

    volatile public long counter = 0;
    
    private class HelloThread implements Runnable{
        private Exercise2 parent = null;

        public HelloThread(Exercise2 p){
            this.parent = p;
        }
    
        public void run(){
            for(int i = 0; i < 1000000; i++){
                parent.increment();
            }
        }

    }

    private synchronized void increment(){
        counter++;
    }

    public Exercise2(){
        Thread[] threads = new Thread[2];
        for(int i = 0; i < threads.length; i++){
            Thread thread = new Thread(new HelloThread(this));
            threads[i] = thread;
            thread.start();
        }

        try{
            for(int i = 0; i < threads.length; i++){
                threads[i].join(); //Wait for both threads to finsish
            }
        }catch(Exception e){
        }
        
        System.out.println(counter);
    }
    public static void main(String[] args) {
        new Exercise2();
    }
}
