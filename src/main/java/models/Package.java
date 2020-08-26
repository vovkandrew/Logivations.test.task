package models;

import java.util.ArrayList;
import java.util.List;

public class Package {
    private long orderId;
    private Case casee;
    private final List<Product> products = new ArrayList<>();
    private static final int FIRST_ELEMENT_INDEX = 0;

    public void addOneProductToPackage(Product product) {
        products.add(product);
    }

    public void addSeveralProductsToPackage(int n, Product product) {
        for (int i = 0; i < n; i++) {
            products.add(product);
        }
    }

    public double getEffectiveVolumeFillingFactor() {
        return ((double) products.size()
                * products.get(FIRST_ELEMENT_INDEX).getVolume())
                / ((double) casee.getVolume());
    }

    public Package(long orderId, Case casee) {
        this.orderId = orderId;
        this.casee = casee;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Case getCase() {
        return casee;
    }

    public void setCase(Case casee) {
        this.casee = casee;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "models.Package{" +
                "orderId=" + orderId +
                ", casee=" + casee +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Package)) {
            return false;
        }
        Package p = (Package) o;
        if(p.products.size() != this.products.size()) {
            return false;
        }
        for (int i = 0; i < p.products.size(); i++) {
            if (!p.products.get(i).equals(this.products.get(i))) {
                return false;
            }
        }
        return (p.orderId == this.orderId && p.casee.equals(this.casee));
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = ((int) this.getOrderId() + casee.hashCode() + products.hashCode()) / 3;
        return result;
    }
}
