package com.mycompany.paintphake;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class Pencil extends HinhVe {

    private int t = 0;
//    protected double netVe = 1;

    {
        this.maHinh = 0;
        hinh = new Polyline();
        ((Polyline) hinh).addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (isDoiMauNetVe == true) {
                    ((Polyline) hinh).setStroke(mauNetVe);
                }
            }
        });
    }

    public Pencil() {

    }

    public Pencil(double netVe, Color mauNetVe) {
        this.netVe = netVe;
        this.mauNetVe = mauNetVe;
    }

    public Pencil(double netVe, Color mauNetVe, double doMoNetVe, int maHinh) {
        this.netVe = netVe;
        this.mauNetVe = mauNetVe;
        this.doMoNetVe = doMoNetVe;
        this.maHinh = maHinh;
    }

    @Override
    public void capNhat() {
        if (viTriChuot != -1) {
            ((Polyline) hinh).setStrokeWidth(netVe);
            ((Polyline) hinh).setStroke(mauNetVe);
        }
    }

    @Override
    public void suKienClickChuot(MouseEvent e) {
    }

    /*Su kien keo chuot de chinh sua*/
    @Override
    public void suKienKeoChuot(MouseEvent e) {
        ((Polyline) hinh).setStrokeWidth(netVe);
        ((Polyline) hinh).setStroke(mauNetVe);
        ((Polyline) hinh).getPoints().addAll(e.getX(), e.getY());

    }

    /*Su kien tha chuot */
    @Override
    public void suKienThaChuot(MouseEvent e) {
        this.viTriChuot = -1;

    }
}
