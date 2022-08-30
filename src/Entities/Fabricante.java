package Entities;

import java.util.concurrent.Semaphore;

public class Fabricante extends Thread{
    String nome;
    Semaphore semaphore;
    FilaVenda fila;

    public Fabricante(String nome, Semaphore semaphore, FilaVenda fila) {
        this.nome = nome;
        this.semaphore = semaphore;
        this.fila = fila;
    }

    @Override
    public void run() {
        try {
            this.semaphore.acquire();

            this.semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
