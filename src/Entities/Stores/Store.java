package Entities.Stores;

import Entities.Entity;
import Entities.Queues.QueueSale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Store extends Entity{
    int[] prodSales_count;
    Semaphore sem_fabricator;
    QueueSale queueSale;

    public Store(String name, QueueSale queueSale, int[] delays, ArrayList<String> productCatalog, Semaphore sem_fabricator) {
        super(name, delays, productCatalog);
        this.sem_fabricator = sem_fabricator;
        this.prodSales_count = new int[productCatalog.size()];
        this.queueSale = queueSale;
        Arrays.fill(this.prodSales_count, 0);
    }

    @Override
    public void run() {
        try {
            Random rng = new Random();
            while (true){
                int delay = rng.nextInt(this.delay[0], this.delay[1]);
                Thread.sleep(delay);
                int index_prod = rng.nextInt(this.productCatalog.size());
                this.incrementCount();
                this.addDelayTime(delay);
                this.prodSales_count[index_prod]++;
                Sales sales = new Sales(String.format("%05d", this.count), this.productCatalog.get(index_prod), this);
                this.queueSale.add(sales);
                System.out.println("Item " + sales.product + " na loja " + this.getEntityName() + " vendido! Agora vai ser fabricado!");
                this.sem_fabricator.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public int[] getProdSales_count() {
        return prodSales_count;
    }
}
