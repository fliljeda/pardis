import java.util.concurrent.*;
public class Exercise1{
    
    private class HelloThread implements Runnable{
        public void run(){
            System.out.println("Hello World");
        }
    }

    public Exercise1(){
        Thread thread = new Thread(new HelloThread());
        thread.start();
        
    }
    public static void main(String[] args) {
        new Exercise1();
        
    }
}