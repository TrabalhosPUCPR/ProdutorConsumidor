package Entities;

import java.util.ArrayList;

public interface DailyTask {
    ArrayList<Integer> daily_times = new ArrayList<>();
    ArrayList<Integer> averageDailyDelays = new ArrayList<>();
    void endDay();

    default ArrayList<Integer> getAverageDailyDelays(){
        return averageDailyDelays;
    }
    default ArrayList<Integer> getDailyDelays(){
        return daily_times;
    }
}
