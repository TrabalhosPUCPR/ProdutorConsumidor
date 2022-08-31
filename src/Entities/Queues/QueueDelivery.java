package Entities.Queues;

import Entities.Transporter.Delivery;

import java.util.LinkedList;

public class QueueDelivery {

    private final LinkedList<Delivery> deliveries;

    public QueueDelivery() {
        this.deliveries = new LinkedList<>();
    }
    public void add(Delivery delivery){
        this.deliveries.add(delivery);
    }
    public Delivery removeFirst(){
        Delivery delivery = this.deliveries.getFirst();
        this.deliveries.remove();
        return delivery;
    }
}
