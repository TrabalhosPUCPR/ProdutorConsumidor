package Reports;

import Entities.Stores.Store;

import java.io.FileWriter;
import java.io.IOException;

public class StoreReport extends Report{

    Store[] stores;

    public StoreReport(String name, String output_path, Store[] stores){
        super(name, output_path);
        this.stores = stores;
    }

    @Override
    public void print() {
        try {
            super.print();
            int salesTotal = 0;
            for(Store store : this.stores){
                writer.write("Nome: " + store.getStoreName() + "\n");
                writer.write("Catalogo: " );
                for(String product : store.getCatalog()){
                    writer.write(String.format("%1$8s", product));
                }
                writer.write("\n");
                writer.write("Vendido:  ");
                for(int product : store.getProdSales_count()){
                    writer.write(String.format("%1$8s", product));
                }
                writer.write("\n");
                writer.write("Total de vendas: " + store.getSales_count() + "\n");
                salesTotal += store.getSales_count();
                writer.write("\n");
            }
            writer.write("Total de vendas de todas as lojas: " + salesTotal + "\n\n\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
