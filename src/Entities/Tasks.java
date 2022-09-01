package Entities;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public abstract class Tasks extends Entity{

    protected int current;
    protected Semaphore semaphore;
    protected Semaphore limit;

    public Tasks(String name, Semaphore semaphore, int[] delays, ArrayList<String> productCatalog, int limit) {
        super(name, delays, productCatalog);
        this.semaphore = semaphore;
        this.limit = new Semaphore(limit);
        this.current = 0;
    }

    public void decrementCurrent() {
        this.current--;
    }
    public void doTask(int timeTaken){
        this.addDelayTime(timeTaken);
        try {
            sleep(timeTaken);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.incrementCount();
        this.decrementCurrent();
        this.limit.release();
    }
}
