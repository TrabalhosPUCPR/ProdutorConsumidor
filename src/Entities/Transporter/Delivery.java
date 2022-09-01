package Entities.Transporter;

import Entities.Stores.Sales;

public class Delivery {
    Sales sales; // so precisa desse pq o produto a ser vendido ja ta aqui

    public Delivery(Sales sales) {
        this.sales = sales;
    }
}
