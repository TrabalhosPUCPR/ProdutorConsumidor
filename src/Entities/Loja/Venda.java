package Entities.Loja;

public class Venda {

    String ID_venda;
    String produto;
    Loja loja;

    public Venda(String produto, Loja loja, int id) {
        this.produto = produto;
        this.loja = loja;
        this.ID_venda = loja.nome + id;
    }

    public String getProduto() {
        return produto;
    }
}
