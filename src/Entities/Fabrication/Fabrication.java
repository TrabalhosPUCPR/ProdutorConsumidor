package Entities.Fabrication;

import Entities.Queues.QueueDelivery;
import Entities.Stores.Sales;
import Entities.Transporter.Delivery;

import java.util.concurrent.Semaphore;

public class Fabrication extends Thread{
    QueueDelivery queue_delivery;
    Sales sales;
    Fabricator fabricator;
    Semaphore sem_transport;
    int delay;

    public Fabrication(Fabricator fabricator, QueueDelivery queue_delivery, Sales sales, Semaphore sem_transport, int delay){
        this.queue_delivery = queue_delivery;
        this.sales = sales;
        this.sem_transport = sem_transport;
        this.fabricator = fabricator;
        this.delay = delay;
    }

    @Override
    public void run() {
        System.out.println("Fabricando " + this.sales.getID());
        this.fabricator.doTask(this.delay);
        System.out.println(this.sales.getID() + " fabricado! Agora vai transportar!");
        this.queue_delivery.add(new Delivery(this.sales));
        this.sem_transport.release();
    }
}
