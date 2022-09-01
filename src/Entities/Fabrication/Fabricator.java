package Entities.Fabrication;

import Entities.Queues.QueueDelivery;
import Entities.Queues.QueueSale;
import Entities.Stores.Sales;
import Entities.Tasks;
import Entities.TimeManager;

import java.util.ArrayList;
import java.util.Random;
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
            while (true) {
                do {
                    this.semaphore.acquire();
                } while (this.limit <= this.current);
                this.current++;
                Sales sale = this.queue_sale.removeFirst();
                Random random = new Random();
                int productIndex = this.productCatalog.indexOf(sale.getProduct());
                int delay = random.nextInt(this.delay[productIndex], this.delay[productIndex] + TimeManager.delay(200));
                Fabrication fabrication = new Fabrication(this, this.queueDelivery, sale, sem_transport, delay);
                fabrication.start();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
