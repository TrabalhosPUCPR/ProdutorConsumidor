package Reports;

import Entities.Transporter.Transporter;

import java.io.IOException;

public class TransporterReport extends Report {

    Transporter[] transporters;

    public TransporterReport(String name, String output_path, Transporter[] transporters){
        super(name, output_path);
        this.transporters = transporters;
    }

    @Override
    public void print() {
        try {
            super.print();
            int transportedTotal = 0;
            for(Transporter transporter : this.transporters){
                writer.write("Nome: " + transporter.getTransporterName() + "\n");
                writer.write("Total fabricado: " + transporter.getCount_delivered()  + "\n");
                transportedTotal += transporter.getCount_delivered();
                writer.write("\n");
            }
            writer.write("Total transportado por todos os transportadores: " + transportedTotal + "\n\n\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
