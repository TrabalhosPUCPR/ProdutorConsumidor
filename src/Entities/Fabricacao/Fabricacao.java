package Entities.Fabricacao;

import Entities.Transporte.Entrega;
import Entities.Filas.FilaEntrega;
import Entities.Loja.Venda;

import java.util.concurrent.Semaphore;

public class Fabricacao extends Thread{
    FilaEntrega fila_entrega;
    Venda venda;

    Semaphore sem_transport;

    public Fabricacao(FilaEntrega fila_entrega, Venda venda, Semaphore sem_transport){
        this.fila_entrega = fila_entrega;
        this.venda = venda;
        this.sem_transport = sem_transport;
    }

    @Override
    public void run() {
        try {
            System.out.println("Fabricando " + this.venda.getProduto());
            Thread.sleep(5000);
            System.out.println(this.venda.getProduto() + " fabricado!");
            this.fila_entrega.add(new Entrega(this.venda));
            this.sem_transport.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
