package Entities.Fabrication;

import Entities.Transporter.Delivery;
import Entities.Queues.QueueDelivery;
import Entities.Stores.Sales;

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
            System.out.println("Fabricando " + this.sales.getID_sale());
            Random random = new Random();
            int indexProduct = this.fabricator.catalog.indexOf(this.sales.getProduct());
            int fabricationDelay =  random.nextInt(this.fabricator.fabrication_delay[indexProduct], this.fabricator.fabrication_delay[indexProduct] + 200);
            Thread.sleep(fabricationDelay);
            System.out.println(this.sales.getID_sale() + " fabricado!");
            this.fabricator.n_fabricating--;
            this.fabricator.daily_times.add(fabricationDelay);
            this.fabricator.fabricated_count++;
            this.queue_delivery.add(new Delivery(this.sales));
            this.sem_transport.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
