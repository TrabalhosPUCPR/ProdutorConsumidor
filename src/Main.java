import Entities.Fabricacao.Fabricante;
import Entities.Filas.FilaEntrega;
import Entities.Filas.FilaVenda;
import Entities.Loja.Loja;
import Entities.Transporte.Transportadora;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        // filas
        FilaVenda fila_venda = new FilaVenda();
        FilaEntrega fila_entregas = new FilaEntrega();

        // semaforos
        Semaphore sem_loja = new Semaphore(1);
        Semaphore sem_fabricante = new Semaphore(0);
        Semaphore sem_transport = new Semaphore(0);

        // produtos
        ArrayList<String> produtos = new ArrayList<>();
        produtos.add("A");
        produtos.add("B");
        produtos.add("C");
        produtos.add("D");
        produtos.add("E");
        produtos.add("F");
        produtos.add("G");
        produtos.add("H");

        // lojas
        Loja[] lojas = new Loja[8];
        lojas[0] = new Loja(produtos, "A", fila_venda, sem_loja, sem_fabricante);
        lojas[1] = new Loja(produtos, "B", fila_venda, sem_loja, sem_fabricante);
        lojas[2] = new Loja(produtos, "C", fila_venda, sem_loja, sem_fabricante);
        lojas[3] = new Loja(produtos, "D", fila_venda, sem_loja, sem_fabricante);
        lojas[4] = new Loja(produtos, "E", fila_venda, sem_loja, sem_fabricante);
        lojas[5] = new Loja(produtos, "F", fila_venda, sem_loja, sem_fabricante);
        lojas[6] = new Loja(produtos, "G", fila_venda, sem_loja, sem_fabricante);
        lojas[7] = new Loja(produtos, "H", fila_venda, sem_loja, sem_fabricante);

        // fabricantes
        Fabricante[] fabricantes = new Fabricante[4];
        fabricantes[0] = new Fabricante("A", sem_fabricante, sem_transport, fila_venda, fila_entregas);
        fabricantes[1] = new Fabricante("B", sem_fabricante, sem_transport, fila_venda, fila_entregas);
        fabricantes[2] = new Fabricante("C", sem_fabricante, sem_transport, fila_venda, fila_entregas);
        fabricantes[3] = new Fabricante("D", sem_fabricante, sem_transport, fila_venda, fila_entregas);

        // transportadoras
        Transportadora[] transportadoras = new Transportadora[2];
        transportadoras[0] = new Transportadora("A", sem_transport, fila_entregas);
        transportadoras[1] = new Transportadora("B", sem_transport, fila_entregas);

        // .start()
        for (Loja loja : lojas) {
            loja.start();
        }
        for (Fabricante fabricante : fabricantes) {
            fabricante.start();
        }
        for(Transportadora transportadora : transportadoras){
            transportadora.start();
        }
    }
}
