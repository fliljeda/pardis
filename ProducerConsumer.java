import java.util.concurrent.*;

public class ProducerConsumer{
    public Object lock = new Object();

    public int[] buffer = new int[10];
    public boolean[] isFree = new boolean[10];


    private class Producer implements Runnable {
        public int[] sequenceToSend;
    
        public Producer(int[] seq){
            sequenceToSend = seq;
           
        }
        public void run(){
            try{
                sendSequence(sequenceToSend);
            } catch(Exception e){
                System.out.println(e.getStackTrace());
            }
            
        }
        private void sendSequence(int[] seq) throws Exception{
            for(int i = 0; i < seq.length; i++){
                sendInt(seq[i]);
            }
        }

        private void sendInt(int n) throws Exception{
            
            while(true){
                synchronized(lock){
                    for(int i=0; i < isFree.length; i++){
                        if(isFree[i]){
                            buffer[i] = n;
                            isFree[i] = false;
                            lock.notifyAll();
                            return;
                        }
                    }
                    lock.wait();
                }   
            }
         }

    }

    private class Consumer implements Runnable {
        private int current = 0; //Index to write to
        public Consumer(){

        }
        public void run(){
            try{
                readBuffer();
            } catch(Exception e){
                System.out.println("Exception Consumer");
            }
           
            
        }
    

        public void readBuffer() throws Exception{
            while(true){
                    synchronized(lock){
                    for(int i = 0; i < isFree.length; i++){
                        if(!isFree[i]){
                            System.out.println("INT RECIEVED: " + buffer[i]);
                            isFree[i] = true;
                            lock.notify();
                        }   
            
                    }
                    lock.wait();
                }
            }
        }
    }

    public ProducerConsumer(){
        for(int i = 0; i < isFree.length; i++){
            isFree[i] = true;
        }
        Producer p = new Producer(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18});
        Consumer c = new Consumer();
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }

    public static void main(String[] args){
        new ProducerConsumer();
    }


}