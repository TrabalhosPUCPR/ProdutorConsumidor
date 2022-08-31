package Entities.Fabrication;

import Entities.DailyTask;
import Entities.Queues.QueueDelivery;
import Entities.Queues.QueueSale;
import Entities.Stores.Sales;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Fabricator extends Thread implements DailyTask {
    String name;
    Semaphore semaphore;
    Semaphore sem_transport;
    QueueSale queue_sale;
    QueueDelivery queue_delivery;
    int n_fabricating;
    int n_fabricating_limit;
    int fabricated_count;
    int[] fabrication_delay;
    ArrayList<String> catalog;

    public Fabricator(String name, Semaphore semaphore, Semaphore sem_transport, QueueSale queue_sale, QueueDelivery queue_delivery, int n_fabricating_limit, int[] fabrication_delay, ArrayList<String> catalog) {
        this.name = name;
        this.semaphore = semaphore;
        this.sem_transport = sem_transport;
        this.queue_sale = queue_sale;
        this.queue_delivery = queue_delivery;
        this.n_fabricating = 0;
        this.n_fabricating_limit = n_fabricating_limit;
        this.fabricated_count = 0;
        this.fabrication_delay = fabrication_delay;
        this.catalog = catalog;
    }

    @Override
    public void run() {
        try {
            while(true){
                do{
                    this.semaphore.acquire();
                }while (n_fabricating_limit <= n_fabricating);
                n_fabricating++;
                Sales sale = this.queue_sale.removeFirst();
                Fabrication fabrication = new Fabrication(this, this.queue_delivery, sale, sem_transport);
                fabrication.start();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFabricatorName() {
        return name;
    }

    public int getFabricated_count() {
        return fabricated_count;
    }
}
