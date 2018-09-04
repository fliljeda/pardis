import java.util.concurrent.*;
public class Exercise1{
    
    private class HelloThread implements Runnable{
    
        public void run(){
            System.out.println("Hello World " + Thread.currentThread().getId());
        }
    }

    public Exercise1(){
        for(int i = 0; i < 5; i++){
            Thread thread = new Thread(new HelloThread());
            thread.start();
        }    
    }
    public static void main(String[] args) {
        new Exercise1();

    }
}