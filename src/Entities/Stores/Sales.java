package Entities.Stores;

public class Sales {

    String ID_sale;
    String product;
    Store store;

    public Sales(String product, Store store, String id) {
        this.product = product;
        this.store = store;
        this.ID_sale = store.name + id;
    }

    public String getProduct() {
        return product;
    }

    public String getID_sale() {
        return ID_sale;
    }
}
