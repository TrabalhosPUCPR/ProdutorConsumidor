package Entities.Queues;

import java.util.LinkedList;

public abstract class Queue<T> {

    LinkedList<T> queue;

    public Queue(LinkedList<T> queue) {
        this.queue = queue;
    }
    public void add(T value){
        this.queue.add(value);
    }
    public T removeFirst(){ // remove e retorna o que foi removido
        T removed = this.queue.getFirst();
        this.queue.remove();
        return removed;
    }
}
