package Entities.Transporte;

public class Transporte extends Thread{

    Entrega entrega;

    public Transporte(Entrega entrega){this.entrega = entrega;}

    @Override
    public void run() {
        try {
            System.out.println("Transportando " + this.entrega.getVenda().getProduto());
            Thread.sleep(5000);
            System.out.println(this.entrega.getVenda().getProduto() + " transportado!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
