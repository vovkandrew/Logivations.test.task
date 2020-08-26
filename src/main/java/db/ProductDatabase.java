package db;

import java.util.ArrayList;
import java.util.List;
import models.Product;

public class ProductDatabase {
    private static final List<Product> products = new ArrayList<>();

    public static void addProducts(List<Product> productList) {
        products.addAll(productList);
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("There is no product with such id in the database"));
    }

    public static int getNumberOfAvailableProducts() {
        return products.size();
    }

    public static void clearDatabase() {
        products.clear();
    }
}
