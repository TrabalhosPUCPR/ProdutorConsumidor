package Entities;

import Entities.Queues.QueueSale;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public abstract class Entity extends Thread implements DailyTask{
    protected String name;
    protected Semaphore semaphore;
    protected int count;
    protected int[] delay;
    protected ArrayList<String> productCatalog;
    protected int daysPassed;

    public Entity(String name, Semaphore semaphore, int[] delays, ArrayList<String> productCatalog) {
        this.name = name;
        this.semaphore = semaphore;
        this.delay = delays;
        this.count = 0;
        this.productCatalog = productCatalog;
        this.daysPassed = 0;
    }

    @Override
    abstract public void run();

    public String getEntityName() {
        return name;
    }

    public int getCount() {
        return count;
    }
    public void incrementCount(){
        this.count++;
    }

    public ArrayList<String> getProductCatalog() {
        return productCatalog;
    }

    public int[] getDelay() {
        return delay;
    }

    @Override
    public void endDay(){
        this.daysPassed++;
        if(this.daily_times.size() == 0) return;
        int sum = 0;
        for(Integer delay: this.daily_times){
            sum += delay;
        }
        this.averageDailyDelays.add(sum/this.daily_times.size());
        this.daily_times.clear();
    }



    public int getDaysPassed() {
        return daysPassed;
    }
}
