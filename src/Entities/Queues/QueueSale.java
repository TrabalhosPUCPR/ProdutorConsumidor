package Entities.Queues;

import Entities.Stores.Sales;

import java.util.LinkedList;

public class QueueSale {

    LinkedList<Sales> sales;

    public QueueSale() {
        this.sales = new LinkedList<>();
    }
    public void add(Sales sales){
        this.sales.add(sales);
    }
    public Sales removeFirst(){ // remove e retorna o que foi removido
        Sales sales = this.sales.getFirst();
        this.sales.remove();
        return sales;
    }
}
