package com.mycompany.paintphake;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GiaTri {

    protected double netVe;
    protected Color mauNetVe;
    protected Color mauNen;
    protected Font font;
    protected int maHinh;

    {
        this.maHinh = 0;
        this.netVe = 2;
        this.mauNetVe = Color.BLACK;
        this.mauNen = Color.rgb(0, 0, 0, 0);
    }

    public GiaTri() {
    }

    public GiaTri(double netVe, Color mauNetVe, Color mauNen, Font font, int maHinh) {
        this.netVe = netVe;
        this.mauNetVe = mauNetVe;
        this.mauNen = mauNen;
        this.font = font;
        this.maHinh = maHinh;
    }

}
