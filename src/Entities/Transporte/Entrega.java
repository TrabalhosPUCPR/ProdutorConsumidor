package Entities.Transporte;

import Entities.Loja.Venda;

public class Entrega {
    Venda venda; // so precisa desse pq o produto a ser vendido ja ta aqui

    public Entrega(Venda venda) {
        this.venda = venda;
    }

    public Venda getVenda() {
        return venda;
    }
}
