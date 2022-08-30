package Entities.Filas;

import Entities.Transporte.Entrega;

import java.util.LinkedList;

public class FilaEntrega {

    private LinkedList<Entrega> entregas;

    public FilaEntrega() {
        this.entregas = new LinkedList<>();
    }
    public void add(Entrega entrega){
        this.entregas.add(entrega);
    }
    public Entrega removeFirst(){
        Entrega entrega = this.entregas.getFirst();
        this.entregas.remove();
        return entrega;
    }
}
