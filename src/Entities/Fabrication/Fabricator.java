package Entities.Fabrication;

import Entities.Queues.QueueDelivery;
import Entities.Queues.QueueSale;
import Entities.Stores.Sales;
import Entities.Tasks;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Fabricator extends Tasks {
    Semaphore sem_transport;
    QueueSale queue_sale;
    QueueDelivery queueDelivery;

    public Fabricator(String name, Semaphore semaphore, QueueSale queue_sale, int[] delays, ArrayList<String> productCatalog, QueueDelivery queueDelivery, Semaphore sem_transport, int limit) {
        super(name, semaphore, delays, productCatalog, limit);
        this.sem_transport = sem_transport;
        this.queue_sale = queue_sale;
        this.queueDelivery = queueDelivery;
    }

    @Override
    public void run() {
        try {
            while(true){
                do{
                    this.semaphore.acquire();
                }while (this.limit <= this.current);
                this.current++;
                Sales sale = this.queue_sale.removeFirst();
                Fabrication fabrication = new Fabrication(this, this.queueDelivery, sale, sem_transport);
                fabrication.start();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
