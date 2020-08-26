package models;

public class Product extends Box{
    public Product(long id, int sizeX, int sizeY, int sizeZ) {
        super(id, sizeX, sizeY, sizeZ);
    }

    public Product(int sizeX, int sizeY, int sizeZ) {
        super(sizeX, sizeY, sizeZ);
    }

    public Product() {
    }

    public Product copy() {
        return new Product(this.getId(), this.getSizeX(), this.getSizeY(), this.getSizeZ());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product c = (Product) o;
        return (c.getId() == this.getId() && c.getSizeX() == this.getSizeX()
                && c.getSizeY() == this.getSizeY() && c.getSizeZ() == this.getSizeZ());
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = ((int) this.getId() + this.getSizeX() + this.getSizeY() + this.getSizeZ()) / 3;
        return result;
    }
}
