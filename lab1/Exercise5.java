/** 
 * Example showcasing a deadlock with two threads and two locks. This can be seen as a failed solutuon to the Dining
 * Philosopher problem. One tool that can be used for finding deadlocks is JPF(Java pathfinder)
 * This program verifies the java bytecode of the compiled code , this is often used in modelchecking, but can 
 * also be used to analyze concurrent programs and detects data races and deadlocks.
 * 
 * 
*/

import java.util.concurrent.*;
import java.util.Random;
public class Exercise5{
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    private class Actor implements Runnable{
        Random ran = new Random();
        public Object obj1;
        public Object obj2;

        public Actor(Object ob1, Object ob2){
            this.obj1 = ob1;
            this.obj2 = ob2;
        }

        public void run(){
            while(true){
               synchronized(obj1){
                    synchronized(obj2){
                        System.out.println("Doing things..." + ran.nextInt());
                    }
                }
            }
        }
    }
    public Exercise5(){
        Actor actor1 = new Actor(lock1, lock2);
        Actor actor2 = new Actor(lock2, lock1);
        
        Thread thread1 = new Thread(actor1);
        Thread thread2 = new Thread(actor2);
        
        thread1.start();
        thread2.start();


    }

    public static void main(String[] args){
        new Exercise5();
    }
}