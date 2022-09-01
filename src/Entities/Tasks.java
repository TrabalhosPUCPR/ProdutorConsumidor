package Entities;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public abstract class Tasks extends Entity{

    protected int current;
    protected int limit;

    public Tasks(String name, Semaphore semaphore, int[] delays, ArrayList<String> productCatalog, int limit) {
        super(name, semaphore, delays, productCatalog);
        this.limit = limit;
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
    }
}
