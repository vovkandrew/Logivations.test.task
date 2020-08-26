package models;

public abstract class Box {
    private long id;
    private int sizeX;
    private int sizeY;
    private int sizeZ;

    Box(long id, int sizeX, int sizeY, int sizeZ) {
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    public Box(int sizeX, int sizeY, int sizeZ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    public Box() {
    }

    public void changePosition() {
        while (this.sizeX < this.sizeY || this.sizeX < this.sizeZ || this.sizeZ < this.sizeY) {
            if (this.sizeX < this.sizeY) {
                rotateZ();
            }
            if (this.sizeX < this.sizeZ) {
                rotateY();
            }
            if (this.sizeZ < this.sizeY) {
                rotateX();
            }
        }
    }

    private void rotateX() {
        int sub = this.getSizeZ();
        this.setSizeZ(this.getSizeY());
        this.setSizeY(sub);
    }

    private void rotateY() {
        int sub = this.getSizeX();
        this.setSizeX(this.getSizeZ());
        this.setSizeZ(sub);
    }

    private void rotateZ() {
        int sub = this.getSizeX();
        this.setSizeX(this.getSizeY());
        this.setSizeY(sub);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSizeX() {
        return sizeX;
    }

    private void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    private void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeZ() {
        return sizeZ;
    }

    private void setSizeZ(int sizeD) {
        this.sizeZ = sizeD;
    }

    public int getVolume() {
        return this.getSizeX() * this.getSizeY() * this.getSizeZ();
    }

    @Override
    public String toString() {
        return "models.Box{" +
                "id=" + id +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", sizeZ=" + sizeZ +
                '}';
    }
}
