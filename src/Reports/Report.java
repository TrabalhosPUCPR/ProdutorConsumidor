package Reports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class Report {
    String name;
    FileWriter writer;
    long time;

    Report(String name, String output_path){
        this.name = name;
        try{
            File file = new File(output_path);
            file.createNewFile();
            this.writer = new FileWriter(output_path);
            this.writer.write("");
            this.writer = new FileWriter(output_path, true);
        }catch (Exception ignored){

        }
    }

    public void start_timer(){
        this.time = System.currentTimeMillis();
    }
    public long elapsedTime(){return System.currentTimeMillis() - this.time;}

    public void print(){
        try {
            DateFormat date_format = new SimpleDateFormat("mm:ss");
            Date time = new Date(this.elapsedTime());
            writer.write("RELATORIO " + this.name + ": \n");
            writer.write("Tempo aberto: " + date_format.format(time) + "\n");
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}