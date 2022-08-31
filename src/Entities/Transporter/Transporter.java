package Entities.Transporter;

import Entities.DailyTask;
import Entities.Queues.QueueDelivery;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Transporter extends Thread implements DailyTask {
    String name;
    Semaphore semaphore;
    QueueDelivery queue_delivery;
    int[] delivery_delay;
    int count_delivered;
    int transport_limit;
    int n_transporting;

    public Transporter(String name, Semaphore semaphore, QueueDelivery queue_delivery, int[] delivery_delay, int transport_limit) {
        this.name = name;
        this.semaphore = semaphore;
        this.queue_delivery = queue_delivery;
        this.delivery_delay = delivery_delay;
        this.count_delivered = 0;
        this.transport_limit = transport_limit;
    }

    @Override
    public void run(){
        try {
            while(true){
                do{
                    this.semaphore.acquire();
                }while (transport_limit <= n_transporting);
                Delivery delivery = this.queue_delivery.removeFirst();
                this.n_transporting++;
                Transport transport = new Transport(this, delivery, this.delivery_delay);
                transport.start();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public int getCount_delivered() {
        return count_delivered;
    }
    public String getTransporterName() {
        return name;
    }
}
