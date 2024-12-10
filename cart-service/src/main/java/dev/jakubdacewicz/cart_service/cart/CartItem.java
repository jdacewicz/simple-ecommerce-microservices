package dev.jakubdacewicz.cart_service.cart;

public class CartItem {

    private String productId;

    private int quantity;

    CartItem() {
    }

    CartItem(String productId,
             int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public void subtractQuantity(int quantity) {
        this.quantity -= quantity;
    }


    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    void setProductId(String productId) {
        this.productId = productId;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
