import Entities.Fabricante;
import Entities.FilaVenda;
import Entities.Loja;
import Entities.Produto;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        FilaVenda fila = new FilaVenda();
        Semaphore sem_loja = new Semaphore(1);
        Semaphore sem_fabricante = new Semaphore(1);

        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("A"));

        Loja loja = new Loja(produtos, "A", fila, sem_loja);
        Fabricante fabricante = new Fabricante("A", sem_fabricante, fila);
    }
}
