package Entities;

import java.util.LinkedList;

public class FilaVenda {

    LinkedList<Venda> vendas;

    public FilaVenda() {
    }
    public void add(Venda venda){
        this.vendas.add(venda);
    }
    public void removeFirst(){
        this.vendas.remove();
    }
}
