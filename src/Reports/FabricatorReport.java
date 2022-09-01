package Reports;

import Entities.Fabrication.Fabricator;
import Entities.TimeManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FabricatorReport extends Report{
    Fabricator[] fabricators;
    TimeManager timeManager;

    public FabricatorReport(String name, String output_path, Fabricator[] fabricators, TimeManager timeManager){
        super(name, output_path);
        this.fabricators = fabricators;
        this.timeManager = timeManager;
    }
    @Override
    public void print() {
        try {
            super.print();
            int fabricatedTotal = 0;
            int totalTime = 0;
            writer.write("Dias simulado: " + this.fabricators[0].getDaysPassed() + "\n\n");
            for(Fabricator fabricator : this.fabricators){
                writer.write("Nome: " + fabricator.getEntityName() + "\n");
                writer.write("Total fabricado: " + fabricator.getCount()  + "\n");
                fabricatedTotal += fabricator.getCount();
                writer.write("Medias do tempo entre fabricacoes de cada dia: " + fabricator.getAverageDailyTimes());
                writer.write("\n\n");
                for (int i : fabricator.getDailyDelays()){
                    totalTime += i;
                }
            }
            writer.write("Total fabricado por todos os fabricantes: " + fabricatedTotal + "\n");
            writer.write("Tempo total gasto por todos os fabricantes: " + totalTime / 1000 + "\n");
            writer.write("\n\n\n\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
