import java.util.concurrent.*;

public class Semaphore{
    public volatile int value; 
    public Object lock = new Object();


    public Semaphore(int n){
        value = n;
    }

    //Increments value of semaphore by 1, indicating a resource
    //has been made available
    public void semaSignal(){
        synchronized(lock){
            boolean notify = value < 0;
            value += 1;
            if(notify){
                lock.notify();
            }
        }
    }

    //Decrements the value of the semaphore by 1, if the value is
    //not negative
    public void semaWait(){
        synchronized(lock){
            if(value >= 0){
                value -= 1;
            }
            while(value < 0){
                try{
                    lock.wait();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args){
        Semaphore sema = new Semaphore(5);
    }
}
