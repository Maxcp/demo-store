package test.poc.demostore.model.request;

import java.util.List;


public class CheckoutRequest {

    List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
