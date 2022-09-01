package Entities.Queues;

import Entities.Stores.Sales;

import java.util.LinkedList;

public class QueueSale extends Queue<Sales>{
    public QueueSale() {
        super(new LinkedList<>());
    }
}
