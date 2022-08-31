package Reports;

import Entities.Fabrication.Fabricator;

import java.io.FileWriter;
import java.io.IOException;

public class FabricatorReport extends Report{
    Fabricator[] fabricators;

    public FabricatorReport(String name, String output_path, Fabricator[] fabricators){
        super(name, output_path);
        this.fabricators = fabricators;
    }
    @Override
    public void print() {
        try {
            super.print();
            int fabricatedTotal = 0;
            for(Fabricator fabricator : this.fabricators){
                writer.write("Nome: " + fabricator.getFabricatorName() + "\n");
                writer.write("Total fabricado: " + fabricator.getFabricated_count()  + "\n");
                fabricatedTotal += fabricator.getFabricated_count();
                writer.write("\n");
            }
            writer.write("Total fabricado por todos os fabricantes: " + fabricatedTotal + "\n\n\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
