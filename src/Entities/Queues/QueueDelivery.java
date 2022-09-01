package Entities.Queues;

import Entities.Transporter.Delivery;
import java.util.LinkedList;

public class QueueDelivery extends Queue<Delivery>{

    public QueueDelivery() {
        super(new LinkedList<>());
    }
}
