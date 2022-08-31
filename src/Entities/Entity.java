package Entities;

import Entities.Queues.QueueSale;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public abstract class Entity extends Thread{
    String name;
    Semaphore semaphore;
    QueueSale queue;
    int count;
    int[] delay;
    ArrayList<String> productCatalog;

    public Entity(String name, Semaphore semaphore, QueueSale queue, int[] delays, ArrayList<String> productCatalog) {
        this.name = name;
        this.semaphore = semaphore;
        this.queue = queue;
        this.delay = delays;
        this.count = 0;
        this.productCatalog = productCatalog;
    }

    @Override
    abstract public void run();
}
