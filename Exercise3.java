import java.util.concurrent.*;
public class Exercise3{

    volatile public long counter = 0;
    volatile public boolean completed = false;
    
    private class HelloThread implements Runnable{
        private Exercise3 parent = null;
        private boolean print;

        public HelloThread(Exercise3 p, boolean print){
            this.parent = p;
            this.print = print;
        }
    
        public void run(){
            if(print){
                for(int i = 0; i < 1000000; i++){
                    parent.increment();
                }
                completed = true;
            }else{
                while(!completed);
                System.out.println(parent.counter);
            }
        }

    }

    private synchronized void increment(){
        counter++;
    }

    public Exercise3(){
        Thread thread1 = new Thread(new HelloThread(this, true));
        Thread thread2 = new Thread(new HelloThread(this, false));
        thread1.start();
        thread2.start();
    }
    public static void main(String[] args) {
        new Exercise3();
    }
}
