package Entities.Stores;

import Entities.Queues.QueueSale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Store extends Thread{

    ArrayList<String> catalog;
    String name;
    int sales_count;
    int[] prodSales_count;
    Semaphore semaphore;
    Semaphore sem_fabricator;
    QueueSale queue;
    int[] sales_delay;

    public Store(ArrayList<String> catalog, String name, QueueSale queue, Semaphore semaphore, Semaphore sem_fabricator, int[] sales_delay) {
        this.catalog = catalog;
        this.name = name;
        this.semaphore = semaphore;
        this.queue = queue;
        this.sem_fabricator = sem_fabricator;
        this.prodSales_count = new int[this.catalog.size()];
        this.sales_delay = sales_delay;
        Arrays.fill(this.prodSales_count, 0);
    }

    @Override
    public void run() {
        try {
            Random rng = new Random();
            while (true){
                Thread.sleep(rng.nextInt(this.sales_delay[0], this.sales_delay[1]));
                this.semaphore.acquire();
                int index_prod = rng.nextInt(catalog.size());
                this.sales_count++;
                this.prodSales_count[index_prod]++;
                Sales sales = new Sales(catalog.get(index_prod), this, String.format("%05d", this.sales_count));
                this.queue.add(sales);
                System.out.println("Item " + sales.product + " vendido! Agora vai ser fabricado!");
                this.sem_fabricator.release();
                this.semaphore.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getStoreName() {
        return name;
    }

    public int getSales_count() {
        return sales_count;
    }

    public ArrayList<String> getCatalog() {
        return catalog;
    }

    public int[] getProdSales_count() {
        return prodSales_count;
    }
}
