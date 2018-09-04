import java.util.concurrent.*;
import java.util.*;

/**
 * The expected behaviour is for thread with id 1 to wait for another thread to push
 * an item onto the shared linkedlist. What happens without synchronization is that 
 * maybe both threads run at the same time and with some bad thread skipping then
 * the while(l.size()... will be true and the other thread pushes and notifies before 
 * the thread waits for the notify.
 *
 */
public class Exercise6{

    public Object obj = new Object();
    public LinkedList<Integer> l = new LinkedList<Integer>();

    public class Actor implements Runnable{
        public int id;

        public Actor(int id){
            this.id = id;
        }

        public void run() {

            try{
                if(id == 0){
                    //synchronized(obj){
                        l.push(15);
                        obj.notify();
                    //}
                }else if(id == 1){
                    //synchronized(obj){
                        while(l.size() == 0){
                            obj.wait();
                        }
                        int i = l.pop();
                        System.out.println(i);
                    //}
                }
            }catch(Exception e){
            }
        }
    }

    public Exercise6(){
        Thread t1 = new Thread(new Actor(0));
        Thread t2 = new Thread(new Actor(1));
        t1.start();
        t2.start();
        for(;;){}
    }

    public static void main(String[] args){
        new Exercise6();
    }
}
