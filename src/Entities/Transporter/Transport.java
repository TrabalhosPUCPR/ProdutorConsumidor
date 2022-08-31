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
            System.out.println("Transportando " + this.delivery.getVenda().getID_sale());
            Thread.sleep(random.nextInt(this.delivery_delay[0], this.delivery_delay[1]));
            this.transporter.count_delivered++;
            this.transporter.n_transporting--;
            System.out.println(this.delivery.getVenda().getID_sale() + " transportado!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
