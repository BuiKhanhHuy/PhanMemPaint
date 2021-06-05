package com.mycompany.paintphake;

public class Diem {

    private double x;
    private double y;

    public Diem() {
        this.x = 0;
        this.y = 0;
    }

    public Diem(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Diem(Diem point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Diem traDiem() {
        return new Diem(this.getX(), this.getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
