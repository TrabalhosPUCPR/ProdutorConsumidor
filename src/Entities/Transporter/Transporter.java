package Entities.Transporter;

import Entities.Queues.QueueDelivery;
import Entities.Stores.Sales;
import Entities.Tasks;

import java.util.ArrayList;
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
                do{
                    this.semaphore.acquire();
                }while (this.limit <= this.current);
                Delivery delivery = this.queueDelivery.removeFirst();
                this.current++;
                Transport transport = new Transport(this, delivery, this.delay);
                transport.start();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
