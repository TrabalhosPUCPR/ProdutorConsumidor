package Entities.Loja;

import Entities.Filas.FilaVenda;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Loja extends Thread{

    ArrayList<String> produtos;
    String nome;
    int cont_vendas;
    Semaphore semaphore;
    Semaphore sem_fabricante;
    FilaVenda fila;
    int[] delay_vendas = new int[]{10, 150};
    int multiplicadorDelay = 100;

    public Loja(ArrayList<String> produtos, String nome, FilaVenda fila, Semaphore semaphore, Semaphore sem_fabricante) {
        this.produtos = produtos;
        this.nome = nome;
        this.semaphore = semaphore;
        this.fila = fila;
        this.sem_fabricante = sem_fabricante;
    }

    @Override
    public void run() {
        try {
            Random rng = new Random();
            while (true){
                Thread.sleep(rng.nextInt(this.delay_vendas[0] * multiplicadorDelay, this.delay_vendas[1] * multiplicadorDelay));
                this.semaphore.acquire();
                Venda venda = new Venda(produtos.get(rng.nextInt(produtos.size())), this, rng.nextInt());
                this.fila.add(venda);
                System.out.println("Item " + venda.produto + " vendido! Agora vai ser fabricado!");
                this.sem_fabricante.release();
                this.semaphore.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
