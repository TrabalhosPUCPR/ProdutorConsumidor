package Entities;

import Entities.Queues.Queue;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public abstract class Company extends Entity{

    protected Semaphore semaphore;
    protected Semaphore limit;

    public Company(String name, Semaphore semaphore, int[] delays, ArrayList<String> productCatalog, int limit) {
        super(name, delays, productCatalog);
        this.semaphore = semaphore;
        this.limit = new Semaphore(limit);
    }

    public void doTask(int timeTaken){
        this.addDelayTime(timeTaken);
        try {
            sleep(timeTaken);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.incrementCount();
        this.limit.release();
    }

    public void run(){
        try {
            this.semaphore.acquire();
            this.limit.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
