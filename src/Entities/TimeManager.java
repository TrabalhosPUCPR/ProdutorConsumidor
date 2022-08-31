package Entities;

public class TimeManager extends Thread{

    private int days_passed;
    public static final int DAY_DURATION = 1440;
    public static int timeMultiplier = 1;
    private final DailyTask[] dailyTasks;

    public TimeManager(DailyTask[] dailyTasks){
        this.dailyTasks = dailyTasks;
    }
    public static int[] delay(int[] mins){
        for (int i = 0; i < mins.length; i++){
            mins[i] *= TimeManager.timeMultiplier;
        }
        return mins;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while(true){
            if(System.currentTimeMillis() - startTime >= (long) TimeManager.DAY_DURATION * TimeManager.timeMultiplier){
                this.days_passed++;
                startTime = System.currentTimeMillis();
                for(DailyTask task : this.dailyTasks){
                    task.endDay();
                }
            }
        }
    }

    public int getDays_passed() {
        return days_passed;
    }
}
