package Entities.Transporter;

import Entities.Queues.QueueDelivery;
import Entities.Tasks;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Transporter extends Tasks {

    QueueDelivery queueDelivery;

    public Transporter(String name, Semaphore semaphore, QueueDelivery queueDelivery, int[] delay, ArrayList<String> productCatalog, int limit) {
        super(name, semaphore, delay, productCatalog, limit);
        this.queueDelivery = queueDelivery;
    }

    @Override
    public void run(){
        try {
            while(true){
                this.semaphore.acquire();
                this.limit.acquire();
                Delivery delivery = this.queueDelivery.removeFirst();
                this.current++;
                Random random = new Random();
                Transport transport = new Transport(this, delivery, random.nextInt(this.delay[0], this.delay[1]));
                transport.start();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
