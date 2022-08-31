import Entities.Fabrication.Fabricator;
import Entities.Queues.QueueDelivery;
import Entities.Queues.QueueSale;
import Entities.Stores.Store;
import Entities.TimeManager;
import Entities.Transporter.Transporter;
import Reports.FabricatorReport;
import Reports.Report;
import Reports.StoreReport;
import Reports.TransporterReport;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        // filas
        QueueSale fila_venda = new QueueSale();
        QueueDelivery fila_entregas = new QueueDelivery();

        // semaforos
        Semaphore sem_loja = new Semaphore(1);
        Semaphore sem_fabricante = new Semaphore(0);
        Semaphore sem_transport = new Semaphore(0);

        // produtos
        ArrayList<String> products = new ArrayList<>();
        products.add("A");
        products.add("B");
        products.add("C");
        products.add("D");
        products.add("E");
        products.add("F");
        products.add("G");
        products.add("H");

        // lojas
        int[] store_delay = TimeManager.delay(new int[]{100, 1500});
        Store[] stores = new Store[8];
        stores[0] = new Store(products, "A", fila_venda, sem_loja, sem_fabricante, store_delay);
        stores[1] = new Store(products, "B", fila_venda, sem_loja, sem_fabricante, store_delay);
        stores[2] = new Store(products, "C", fila_venda, sem_loja, sem_fabricante, store_delay);
        stores[3] = new Store(products, "D", fila_venda, sem_loja, sem_fabricante, store_delay);
        stores[4] = new Store(products, "E", fila_venda, sem_loja, sem_fabricante, store_delay);
        stores[5] = new Store(products, "F", fila_venda, sem_loja, sem_fabricante, store_delay);
        stores[6] = new Store(products, "G", fila_venda, sem_loja, sem_fabricante, store_delay);
        stores[7] = new Store(products, "H", fila_venda, sem_loja, sem_fabricante, store_delay);

        // delays dos fabricantes
        int[] fabricator1_delays = TimeManager.delay(new int[]{600, 200, 1000, 400, 800, 1400, 400, 800});
        int[] fabricator2_delays = TimeManager.delay(new int[]{400, 800, 1200, 800, 200, 1000, 1000, 600});
        int[] fabricator3_delays = TimeManager.delay(new int[]{1000, 1200, 400, 600, 400, 400, 1000, 400});
        int[] fabricator4_delays = TimeManager.delay(new int[]{800, 600, 400, 1000, 1200, 800, 600, 1200});

        // fabricantes
        Fabricator[] fabricators = new Fabricator[4];
        fabricators[0] = new Fabricator("A", sem_fabricante, sem_transport, fila_venda, fila_entregas, 4, fabricator1_delays, products);
        fabricators[1] = new Fabricator("B", sem_fabricante, sem_transport, fila_venda, fila_entregas, 1, fabricator2_delays, products);
        fabricators[2] = new Fabricator("C", sem_fabricante, sem_transport, fila_venda, fila_entregas, 4, fabricator3_delays, products);
        fabricators[3] = new Fabricator("D", sem_fabricante, sem_transport, fila_venda, fila_entregas, 4, fabricator4_delays, products);
        TimeManager fabricator_timer = new TimeManager(fabricators);

        // transportadoras
        Transporter[] transporters = new Transporter[2];
        transporters[0] = new Transporter("A", sem_transport, fila_entregas, TimeManager.delay(new int[]{100, 200}), 10);
        transporters[1] = new Transporter("B", sem_transport, fila_entregas, TimeManager.delay(new int[]{400, 600}), 20);
        TimeManager transpoter_timer = new TimeManager(transporters);

        String output_path = "src/output.txt";

        Report store_reports = new StoreReport("Store", output_path, stores);
        Report fabricator_reports = new FabricatorReport("Fabricator", output_path, fabricators, fabricator_timer);
        Report transporter_reports = new TransporterReport("Transporter", output_path, transporters, transpoter_timer);

        System.out.println("Aberto!");

        long start = System.currentTimeMillis();

        // .start()
        store_reports.start_timer();
        for (int i = 0; i < stores.length; i++) {
            stores[i].start();
        }
        fabricator_timer.start();
        fabricator_reports.start_timer();
        for (Fabricator fabricator : fabricators) {
            fabricator.start();
        }
        transpoter_timer.start();
        transporter_reports.start_timer();
        for(Transporter transporter : transporters){
            transporter.start();
        }
        while(System.currentTimeMillis() - start <= TimeManager.DAY_DURATION * 5){}
        System.out.println("\nFechado!");

        store_reports.print();
        fabricator_reports.print();
        transporter_reports.print();
        System.out.println("Relatorio impresso no " + output_path);
        System.exit(0);
    }
}
