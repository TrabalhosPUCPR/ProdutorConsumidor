package Entities;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Loja extends Thread{

    ArrayList<Produto> produtos;
    String nome;
    int cont_vendas;
    Semaphore semaphore;
    FilaVenda fila;

    public Loja(ArrayList<Produto> produtos, String nome, FilaVenda fila, Semaphore semaphore) {
        this.produtos = produtos;
        this.nome = nome;
        this.semaphore = semaphore;
        this.fila = fila;
    }

    @Override
    public void run() {
        try {
            while (true){
                this.semaphore.acquire();
                this.semaphore.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
