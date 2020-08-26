package models;

public class Case extends Box{
    public Case(long id, int sizeX, int sizeY, int sizeZ) {
        super(id, sizeX, sizeY, sizeZ);
    }

    public Case(int sizeX, int sizeY, int sizeZ) {
        super(sizeX, sizeY, sizeZ);
    }

    Case() {
    }

    public Case copy() {
        return new Case(this.getId(), this.getSizeX(), this.getSizeY(), this.getSizeZ());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Case)) {
            return false;
        }
        Case c = (Case) o;
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
