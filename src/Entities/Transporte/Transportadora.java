package Entities.Transporte;

import Entities.Filas.FilaEntrega;

import java.util.concurrent.Semaphore;

public class Transportadora extends Thread{
    String nome;
    Semaphore semaphore;
    FilaEntrega fila_entrega;

    public Transportadora(String nome, Semaphore semaphore, FilaEntrega fila_entrega) {
        this.nome = nome;
        this.semaphore = semaphore;
        this.fila_entrega = fila_entrega;
    }

    @Override
    public void run(){
        try {
            while(true){
                this.semaphore.acquire();
                Entrega entrega = this.fila_entrega.removeFirst();
                System.out.println("Criado transporte");
                Transporte transporte = new Transporte(entrega);
                transporte.start();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
