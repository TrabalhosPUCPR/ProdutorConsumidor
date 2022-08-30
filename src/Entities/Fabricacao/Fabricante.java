package Entities.Fabricacao;

import Entities.Filas.FilaEntrega;
import Entities.Filas.FilaVenda;
import Entities.Loja.Venda;

import java.util.concurrent.Semaphore;

public class Fabricante extends Thread{
    String nome;
    Semaphore semaphore;
    Semaphore sem_transport;
    FilaVenda fila_vendas;
    FilaEntrega fila_entregas;

    public Fabricante(String nome, Semaphore semaphore, Semaphore sem_transport, FilaVenda fila_vendas, FilaEntrega fila_entregas) {
        this.nome = nome;
        this.semaphore = semaphore;
        this.sem_transport = sem_transport;
        this.fila_vendas = fila_vendas;
        this.fila_entregas = fila_entregas;
    }

    @Override
    public void run() {
        try {
            while(true){
                this.semaphore.acquire();
                Venda venda = this.fila_vendas.removeFirst();
                Fabricacao fabricacao = new Fabricacao(this.fila_entregas, venda, sem_transport);
                fabricacao.start();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
