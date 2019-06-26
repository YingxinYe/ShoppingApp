package android.example.june20_shoppingapp.models;

public class Cart {
    private int CartItemId;
    private String CartItemName;
    private String CartItemImage;
    private int productPrice;
    private int quantity;

    public Cart(){};

    public Cart(int cartItemId, String cartItemName, String cartItemImage, int productPrice, int quantity) {
        CartItemId = cartItemId;
        CartItemName = cartItemName;
        CartItemImage = cartItemImage;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return CartItemId;
    }

    public void setCartItemId(int cartItemId) {
        CartItemId = cartItemId;
    }

    public String getCartItemName() {
        return CartItemName;
    }

    public void setCartItemName(String cartItemName) {
        CartItemName = cartItemName;
    }

    public String getCartItemImage() {
        return CartItemImage;
    }

    public void setCartItemImage(String cartItemImage) {
        CartItemImage = cartItemImage;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
