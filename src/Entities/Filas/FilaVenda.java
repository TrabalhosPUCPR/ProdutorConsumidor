package Entities.Filas;

import Entities.Loja.Venda;

import java.util.LinkedList;

public class FilaVenda {

    LinkedList<Venda> vendas;

    public FilaVenda() {
        this.vendas = new LinkedList<>();
    }
    public void add(Venda venda){
        this.vendas.add(venda);
    }
    public Venda removeFirst(){ // remove e retorna o que foi removido
        Venda venda = this.vendas.getFirst();
        this.vendas.remove();
        return venda;
    }
}
