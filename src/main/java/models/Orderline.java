package models;

public class Orderline {
    private Long id;
    private int numberOfProducts;
    private Long productId;

    public Orderline() {
    }

    public Orderline(Long id, int numberOfProducts, Long productId) {
        this.id = id;
        this.numberOfProducts = numberOfProducts;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
