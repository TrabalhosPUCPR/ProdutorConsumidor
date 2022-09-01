package Entities;

public class TimeManager extends Thread{

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
    public static int delay(int mins){
        return mins*TimeManager.timeMultiplier;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while(true){
            if(System.currentTimeMillis() - startTime >= (long) TimeManager.DAY_DURATION * TimeManager.timeMultiplier){

                System.out.println("\n\n\n NOVO DIA \n\n");

                startTime = System.currentTimeMillis();
                for(DailyTask task : this.dailyTasks){
                    task.endDay();
                }
            }
        }
    }
}
