package Entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeManager extends Thread{

    public static final int DAY_DURATION = 1440;
    public static final int TIME_MULTIPLIER = 1;
    private final DailyTask[] dailyTasks;

    public TimeManager(DailyTask[] dailyTasks){
        this.dailyTasks = dailyTasks;
    }
    public static int[] delay(int[] mins){
        for (int i = 0; i < mins.length; i++){
            mins[i] *= TimeManager.TIME_MULTIPLIER;
        }
        return mins;
    }
    public static int delay(int mins){
        return mins*TimeManager.TIME_MULTIPLIER;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while(true){
            if(System.currentTimeMillis() - startTime >= (long) TimeManager.DAY_DURATION * TimeManager.TIME_MULTIPLIER){
                startTime = System.currentTimeMillis();
                for(DailyTask task : this.dailyTasks){
                    task.endDay();
                }
            }
        }
    }

    public static String toDateFormat(long mins){
        DateFormat date_format = new SimpleDateFormat("mm:ss");
        Date time = new Date(mins);
        return date_format.format(time);
    }
}
