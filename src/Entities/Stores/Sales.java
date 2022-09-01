package Entities.Stores;

public class Sales {

    String ID;
    Store store;
    String product;

    public Sales(String ID, String product, Store store) {
        this.ID = store.getEntityName() + ID;
        this.product = product;
        this.store = store;
    }

    public String getID() {
        return ID;
    }

    public String getProduct() {
        return product;
    }
}
