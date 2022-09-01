package Entities.Stores;

import Entities.DailyTask;
import Entities.Entity;
import Entities.Queues.QueueSale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Store extends Entity implements DailyTask{
    int[] prodSales_count;
    Semaphore sem_fabricator;
    QueueSale queueSale;

    public Store(String name, Semaphore semaphore, QueueSale queueSale, int[] delays, ArrayList<String> productCatalog, Semaphore sem_fabricator) {
        super(name, semaphore, delays, productCatalog);
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
                Thread.sleep(rng.nextInt(this.delay[0], this.delay[1]));
                this.semaphore.acquire();
                int index_prod = rng.nextInt(this.productCatalog.size());
                this.count++;
                this.prodSales_count[index_prod]++;
                Sales sales = new Sales(String.format("%05d", this.count), this.productCatalog.get(index_prod), this);
                this.queueSale.add(sales);
                System.out.println("Item " + sales.product + " vendido! Agora vai ser fabricado!");
                this.sem_fabricator.release();
                this.semaphore.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public int[] getProdSales_count() {
        return prodSales_count;
    }
}
