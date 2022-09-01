package Entities.Transporter;

public class Transport extends Thread{

    Transporter transporter;
    Delivery delivery;
    int delivery_delay;

    public Transport(Transporter transporter, Delivery delivery, int delivery_delay){
        this.delivery = delivery;
        this.delivery_delay = delivery_delay;
        this.transporter = transporter;
    }

    @Override
    public void run() {
        System.out.println("Transportando " + this.delivery.sales.getID());
        this.transporter.doTask(this.delivery_delay);
        System.out.println(this.delivery.sales.getID() + " transportado!");
    }

}
