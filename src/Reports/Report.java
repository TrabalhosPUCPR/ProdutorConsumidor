package Reports;

import Entities.TimeManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            System.out.println("Erro ao criar o arquivo!");
        }
    }

    public void start_timer(){
        this.time = System.currentTimeMillis();
    }
    public long elapsedTime(){return System.currentTimeMillis() - this.time;}

    public void print(){
        try {
            writer.write("RELATORIO " + this.name + ": \n");
            writer.write("Tempo real aberto: " + TimeManager.toDateFormat(this.elapsedTime()) + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
