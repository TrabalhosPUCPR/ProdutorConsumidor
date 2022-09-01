package Entities.Fabrication;

import Entities.Queues.QueueDelivery;
import Entities.Stores.Sales;
import Entities.Transporter.Delivery;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Fabrication extends Thread{
    QueueDelivery queue_delivery;
    Sales sales;
    Fabricator fabricator;
    Semaphore sem_transport;

    public Fabrication(Fabricator fabricator, QueueDelivery queue_delivery, Sales sales, Semaphore sem_transport){
        this.queue_delivery = queue_delivery;
        this.sales = sales;
        this.sem_transport = sem_transport;
        this.fabricator = fabricator;
    }

    @Override
    public void run() {
        try {
            System.out.println("Fabricando " + this.sales.getID());
            Random random = new Random();
            int indexProduct = this.fabricator.getProductCatalog().indexOf(this.sales.getProduct());
            int fabricationDelay =  random.nextInt(this.fabricator.getDelay()[indexProduct], this.fabricator.getDelay()[indexProduct] + 200);
            Thread.sleep(fabricationDelay);
            System.out.println(this.sales.getID() + " fabricado!");
            this.fabricator.decrementCurrent();
            this.fabricator.daily_times.add(fabricationDelay);
            this.fabricator.incrementCount();
            this.queue_delivery.add(new Delivery(this.sales));
            this.sem_transport.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
