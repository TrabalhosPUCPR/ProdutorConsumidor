package Reports;

import Entities.TimeManager;
import Entities.Transporter.Transporter;

import java.io.IOException;

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
            for(Transporter transporter : this.transporters){
                writer.write("Dias simulado: " + transporter.getDaysPassed() + "\n");
                writer.write("\n");
                writer.write("Nome: " + transporter.getEntityName() + "\n");
                writer.write("Total Transportado: " + transporter.getCount()  + "\n");
                transportedTotal += transporter.getCount();
                writer.write("Medias do tempo de transporte de cada dia: " + transporter.getAverageDailyTimes());
                writer.write("\n");
            }
            writer.write("Total transportado por todos os transportadores: " + transportedTotal + "\n\n\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
