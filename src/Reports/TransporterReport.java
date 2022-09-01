package Reports;

import Entities.Fabrication.Fabricator;
import Entities.TimeManager;
import Entities.Transporter.Transporter;

import java.io.IOException;
import java.util.ArrayList;

public class TransporterReport extends Report {

    Transporter[] transporters;
    TimeManager timeManager;

    public TransporterReport(String name, String output_path, Transporter[] transporters, TimeManager timeManager){
        super(name, output_path);
        this.transporters = transporters;
        this.timeManager = timeManager;
    }

    @Override
    public void print() {
        try {
            super.print();
            int transportedTotal = 0;
            int totalTime = 0;
            ArrayList<Integer> dailyTotalAverage = new ArrayList<>();
            writer.write("Dias simulado: " + this.transporters[0].getDaysPassed() + "\n");
            writer.write("\n");
            for(Transporter transporter : this.transporters){
                writer.write("Nome: " + transporter.getEntityName() + "\n");
                writer.write("Total Transportado: " + transporter.getCount()  + "\n");
                transportedTotal += transporter.getCount();
                writer.write("Medias do tempo de transporte de cada dia: " + transporter.getAverageDailyTimes() + "\n");
                writer.write("\n");
                for (int t : transporter.getAverageDailyTimes()) {
                    totalTime += t;
                }
            }
            for(int t = 0; t < this.transporters[0].getDaysPassed(); t++){
                int sum = 0;
                for(Transporter f : this.transporters){
                    sum += f.getAverageDailyTimes().get(t);
                }
                dailyTotalAverage.add(sum/this.transporters.length);
            }
            writer.write("Total transportado por todos os transportadores: " + transportedTotal + "\n");
            writer.write("Tempo total gasto por todos os transportadores: " + totalTime + "\n");
            writer.write("Media diaria de transporte de todos os transportadores: " + dailyTotalAverage + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
