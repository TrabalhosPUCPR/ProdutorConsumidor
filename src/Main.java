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
        QueueSale queue_sales = new QueueSale();
        QueueDelivery queue_deliveries = new QueueDelivery();

        // semaforos
        Semaphore sem_fabricator = new Semaphore(0);
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
        stores[0] = new Store("A", queue_sales, store_delay, products, sem_fabricator);
        stores[1] = new Store("B", queue_sales, store_delay, products, sem_fabricator);
        stores[2] = new Store("C", queue_sales, store_delay, products, sem_fabricator);
        stores[3] = new Store("D", queue_sales, store_delay, products, sem_fabricator);
        stores[4] = new Store("E", queue_sales, store_delay, products, sem_fabricator);
        stores[5] = new Store("F", queue_sales, store_delay, products, sem_fabricator);
        stores[6] = new Store("G", queue_sales, store_delay, products, sem_fabricator);
        stores[7] = new Store("H", queue_sales, store_delay, products, sem_fabricator);
        TimeManager stores_timer = new TimeManager(stores);

        // delays dos fabricantes
        int[] fabricator1_delays = TimeManager.delay(new int[]{600, 200, 1000, 400, 800, 1400, 400, 800});
        int[] fabricator2_delays = TimeManager.delay(new int[]{400, 800, 1200, 800, 200, 1000, 1000, 600});
        int[] fabricator3_delays = TimeManager.delay(new int[]{1000, 1200, 400, 600, 400, 400, 1000, 400});
        int[] fabricator4_delays = TimeManager.delay(new int[]{800, 600, 400, 1000, 1200, 800, 600, 1200});

        // fabricantes
        Fabricator[] fabricators = new Fabricator[4];
        fabricators[0] = new Fabricator("A", sem_fabricator, queue_sales, fabricator1_delays, products, queue_deliveries, sem_transport, 4);
        fabricators[1] = new Fabricator("B", sem_fabricator, queue_sales, fabricator2_delays, products, queue_deliveries, sem_transport, 1);
        fabricators[2] = new Fabricator("C", sem_fabricator, queue_sales, fabricator3_delays, products, queue_deliveries, sem_transport, 4);
        fabricators[3] = new Fabricator("D", sem_fabricator, queue_sales, fabricator4_delays, products, queue_deliveries, sem_transport, 4);
        TimeManager fabricator_timer = new TimeManager(fabricators);

        // transportadoras
        Transporter[] transporters = new Transporter[2];
        transporters[0] = new Transporter("A", sem_transport, queue_deliveries, TimeManager.delay(new int[]{100, 200}), products, 10);
        transporters[1] = new Transporter("B", sem_transport, queue_deliveries, TimeManager.delay(new int[]{400, 600}), products, 20);
        TimeManager transporter_timer = new TimeManager(transporters);

        String output_path = "src/output.txt";

        Report store_reports = new StoreReport("Store", output_path, stores);
        Report fabricator_reports = new FabricatorReport("Fabricator", output_path, fabricators, fabricator_timer);
        Report transporter_reports = new TransporterReport("Transporter", output_path, transporters, transporter_timer);

        System.out.println("Aberto!");

        long start = System.currentTimeMillis();

        // .start()
        stores_timer.start();
        store_reports.start_timer();
        for (Store store : stores) {
            store.start();
        }
        fabricator_timer.start();
        fabricator_reports.start_timer();
        for (Fabricator fabricator : fabricators) {
            fabricator.start();
        }
        transporter_timer.start();
        transporter_reports.start_timer();
        for(Transporter transporter : transporters){
            transporter.start();
        }
        int daysToWait = 10;
        while(System.currentTimeMillis() - start <= ((long) TimeManager.DAY_DURATION * TimeManager.TIME_MULTIPLIER) * daysToWait);
        store_reports.print();
        fabricator_reports.print();
        transporter_reports.print();
        System.out.println("Relatorio impresso no " + output_path);
        System.exit(0);
    }
}
