package Entities;

import java.util.ArrayList;

public abstract class Entity extends Thread implements DailyTask{
    protected String name;
    protected int count;
    protected int[] delay;
    protected ArrayList<String> productCatalog;
    protected int daysPassed;
    ArrayList<Integer> daily_times = new ArrayList<>();
    ArrayList<Integer> averageDailyTimes = new ArrayList<>();

    public Entity(String name, int[] delays, ArrayList<String> productCatalog) {
        this.name = name;
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

    public void endDay(){
        this.daysPassed++;
        if(this.daily_times.size() == 0) {
            this.averageDailyTimes.add(null);
            return;
        }
        int sum = 0;
        for(Integer delay: this.daily_times) {
            sum += delay;
        }
        this.averageDailyTimes.add(sum/this.daily_times.size());
        this.daily_times.clear();
    }

    public int getDaysPassed() {
        return daysPassed;
    }
    @Override
    public void addDelayTime(Integer delay){
        this.daily_times.add(delay);
    }

    public ArrayList<Integer> getDailyDelays(){
        return this.daily_times;
    }
    public ArrayList<Integer> getAverageDailyTimes(){
        return this.averageDailyTimes;
    }
}
