package Entities;

import java.util.ArrayList;

public interface DailyTask {
    void endDay();
    ArrayList<Integer> getDailyDelays();
    ArrayList<Integer> getAverageDailyTimes();
    void addDelayTime(Integer delay);
}
