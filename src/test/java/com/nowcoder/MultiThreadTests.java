package com.nowcoder;

import java.util.concurrent.BlockingDeque;

class MyThread extends Thread{
    private int tid;
    public MyThread(int tid){
        this.tid = tid;
    }

    @Override
    public void run() {
        try{
            for(int i=0;i<10;++i){
                Thread.sleep(1000);
                System.out.println(String.format("%d%d",tid,i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{
    private BlockingDeque<String> q;
    public Consumer(BlockingDeque<String> q){
        this.q = q;
    }

    @Override
    public void run() {

    }
}
public class MultiThreadTests {

    public static void main(String[] args) {

    }
}
