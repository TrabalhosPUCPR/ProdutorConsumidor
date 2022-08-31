package Entities;

import java.util.ArrayList;

public interface DailyTask {

    ArrayList<Integer> daily_times = new ArrayList<>();
    ArrayList<Integer> averageDailyDelays = new ArrayList<>();
    default void endDay(){
        int sum = 0;
        for(Integer delay: this.daily_times){
            sum += delay;
        }
        this.averageDailyDelays.add(sum/this.daily_times.size());
        this.daily_times.clear();
    }
}
