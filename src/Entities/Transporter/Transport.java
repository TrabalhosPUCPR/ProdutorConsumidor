package Entities.Transporter;

import java.util.Random;

public class Transport extends Thread{

    Transporter transporter;
    Delivery delivery;
    int[] delivery_delay;

    public Transport(Transporter transporter, Delivery delivery, int[] delivery_delay){
        this.delivery = delivery;
        this.delivery_delay = delivery_delay;
        this.transporter = transporter;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            System.out.println("Transportando " + this.delivery.sales.getID());
            Thread.sleep(random.nextInt(this.delivery_delay[0], this.delivery_delay[1]));
            this.transporter.incrementCount();
            this.transporter.decrementCurrent();
            System.out.println(this.delivery.sales.getID() + " transportado!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
