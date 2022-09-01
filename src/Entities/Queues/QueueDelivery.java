package Entities.Queues;

import Entities.Transporter.Delivery;

import java.util.LinkedList;

public class QueueDelivery {

    LinkedList<Delivery> sales;

    public QueueDelivery() {
        this.sales = new LinkedList<>();
    }
    public void add(Delivery delivery){
        this.sales.add(delivery);
    }
    public Delivery removeFirst(){ // remove e retorna o que foi removido
        Delivery sales = this.sales.getFirst();
        this.sales.remove();
        return sales;
    }
}
