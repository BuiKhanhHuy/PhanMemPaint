package com.mycompany.paintphake;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

public class DuongThang extends HinhVe {

    protected Polygon polygon;
    private int dauTien = 0;
    private final Translate trans;
    private double w2;
    private double h2;

    {
        maHinh = 1;
        soDiemDieuKhien = 2;
        hinh = new Line();
        trans = new Translate();
        ((Line) (hinh)).getTransforms().add(trans);
        polygon = new Polygon();
        polygon.setId("polygon-duongThang");
        polygon.setStrokeWidth(10);
        polygon.setStroke(Color.rgb(1, 1, 1, 0.5));
        this.diemBatDau = new Diem();
        this.diemKetThuc = new Diem();
        this.diemChuotNhan = new Diem();
        this.dsChamTronDieuKhien = new Circle[soDiemDieuKhien];
        for (int i = 0; i < soDiemDieuKhien; i++) {
            dsChamTronDieuKhien[i] = new Circle();
            dsChamTronDieuKhien[i].getStyleClass().add("cham-tron-dieu-khien");
            dsChamTronDieuKhien[i].setId("cham-tron-dieu-khien-" + i);
            dsChamTronDieuKhien[i].setRadius(7);
        }
        ((Line) (hinh)).addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (isDoiMauNetVe == true) {
                    ((Line) (hinh)).setStroke(mauNetVe);
                }
            }
        });
    }

    public DuongThang() {
    }

    public DuongThang(double netVe, Color mauNetVe) {
        this.netVe = netVe;
        this.mauNetVe = mauNetVe;
    }

    public DuongThang(double netVe, Color mauNetVe, Color mauNen,
            Image hinhNen, double doMoNen, double doMoNetVe,
            Diem diemBatDau, Diem diemKetThuc, Diem diemChuotNhan,
            int viTriChuot, int soDiemDieuKhien, Circle dsChamTronDieuKhien[],
            Rectangle khungBao, int maHinh, boolean diChuyen, boolean thayDoiKichThuoc, boolean xoayHinh) {
        this.netVe = netVe;
        this.mauNetVe = mauNetVe;
        this.doMoNetVe = doMoNetVe;
        this.diemBatDau = diemBatDau;
        this.diemKetThuc = diemKetThuc;
        this.diemChuotNhan = diemChuotNhan;
        this.viTriChuot = viTriChuot;
        this.soDiemDieuKhien = soDiemDieuKhien;
        this.dsChamTronDieuKhien = dsChamTronDieuKhien;
        this.maHinh = maHinh;
        this.diChuyen = diChuyen;
        this.thayDoiKichThuoc = thayDoiKichThuoc;
        this.xoayHinh = xoayHinh;

    }

    @Override
    public void capNhat() {
        if (viTriChuot != -1) {
            ((Line) (hinh)).setStroke(mauNetVe);
            ((Line) (hinh)).setStrokeWidth(netVe);
        }
    }


    /* khoang cach hai diem*/
    @Override
    public double khoangCachHaiDiem(Diem p1, Diem p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    /* khoang cach hai diem*/
    @Override
    public double khoangCachHaiDiem(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /* kiem tra mot diem co thuoc duong tron*/
    @Override
    public boolean kiemTraDiemCoThuocDoiTuong(Circle circle, Diem p) {
        if (khoangCachHaiDiem(circle.getCenterX(), circle.getCenterY(), p.getX(), p.getY()) <= circle.getRadius()) {
            return true;
        } else {
            return false;
        }
    }

    /* tra ve duong tron ma chuot duoc click*/
    @Override
    public int traVeViTriDiemDoiTuong(Diem diemChuot, Circle[] cir) {
        for (int i = 0; i < cir.length; i++) {
            if (kiemTraDiemCoThuocDoiTuong(cir[i], diemChuot) == true) {
                return i;
            }
        }
        return -1;
    }

    /*di chuyen doi tuong*/
    public void diChuyenDoiTuong(double ex, double ey) {
        if (((Line) (hinh)).getEndX() < ((Line) (hinh)).getStartX() && ((Line) (hinh)).getEndY() < ((Line) (hinh)).getStartY()) {
            trans.setX(ex - ((Line) (hinh)).getEndX() - w);
            trans.setY(ey - ((Line) (hinh)).getEndY() - h);
            dsChamTronDieuKhien[1].setCenterX(ex - w);
            dsChamTronDieuKhien[1].setCenterY(ey - h);
            dsChamTronDieuKhien[0].setCenterX(ex + w2);
            dsChamTronDieuKhien[0].setCenterY(ey + h2);

        } else if (((Line) (hinh)).getEndX() > ((Line) (hinh)).getStartX() && ((Line) (hinh)).getEndY() > ((Line) (hinh)).getStartY()) {
            trans.setX(ex - ((Line) (hinh)).getEndX() + w);
            trans.setY(ey - ((Line) (hinh)).getEndY() + h);
            dsChamTronDieuKhien[1].setCenterX(ex + w);
            dsChamTronDieuKhien[1].setCenterY(ey + h);
            dsChamTronDieuKhien[0].setCenterX(ex - w2);
            dsChamTronDieuKhien[0].setCenterY(ey - h2);
        } else if (((Line) (hinh)).getEndX() > ((Line) (hinh)).getStartX() && ((Line) (hinh)).getEndY() < ((Line) (hinh)).getStartY()) {
            trans.setX(ex - ((Line) (hinh)).getEndX() + w);
            trans.setY(ey - ((Line) (hinh)).getEndY() - h);
            dsChamTronDieuKhien[1].setCenterX(ex + w);
            dsChamTronDieuKhien[1].setCenterY(ey - h);
            dsChamTronDieuKhien[0].setCenterX(ex - w2);
            dsChamTronDieuKhien[0].setCenterY(ey + h2);
        } else {
            trans.setX(ex - ((Line) (hinh)).getEndX() - w);
            trans.setY(ey - ((Line) (hinh)).getEndY() + h);
            dsChamTronDieuKhien[1].setCenterX(ex - w);
            dsChamTronDieuKhien[1].setCenterY(ey + h);
            dsChamTronDieuKhien[0].setCenterX(ex + w2);
            dsChamTronDieuKhien[0].setCenterY(ey - h2);
        }
        ((Line) (hinh)).setStartX(dsChamTronDieuKhien[0].getCenterX());
        ((Line) (hinh)).setStartY(dsChamTronDieuKhien[0].getCenterY());
        ((Line) (hinh)).setEndX(dsChamTronDieuKhien[01].getCenterX());
        ((Line) (hinh)).setEndY(dsChamTronDieuKhien[01].getCenterY());
        polygon.getPoints().setAll(((Line) (hinh)).getStartX(), ((Line) (hinh)).getStartY(), ((Line) (hinh)).getEndX(), ((Line) (hinh)).getEndY());
    }

    /* Su kien khi chuot duoc click*/
    @Override
    public void suKienClickChuot(MouseEvent e) {
        diemBatDau.setX(e.getX());
        diemBatDau.setY(e.getY());
        diemChuotNhan.setX(e.getX());
        diemChuotNhan.setY(e.getY());
        /*kiem tra co duoc thay doi kich thuoc*/
        if (traVeViTriDiemDoiTuong(diemChuotNhan, dsChamTronDieuKhien) != -1) {
            this.thayDoiKichThuoc = true;
            this.viTriDiemDieuKhien = traVeViTriDiemDoiTuong(diemChuotNhan, dsChamTronDieuKhien);
            /*kiem tra co duoc di chuyen*/
        } else if (traVeViTriDiemDoiTuong(diemChuotNhan, dsChamTronDieuKhien) == -1) {
            polygon.addEventHandler(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    System.out.println("Di chuyen duong thang");
                    diChuyen = true;
                }
            });
            if (diChuyen == true) {
                w = Math.abs(diemChuotNhan.getX() - ((Line) (hinh)).getEndX());
                h = Math.abs(diemChuotNhan.getY() - ((Line) (hinh)).getEndY());
                w2 = Math.abs(diemChuotNhan.getX() - ((Line) (hinh)).getStartX());
                h2 = Math.abs(diemChuotNhan.getY() - ((Line) (hinh)).getStartY());
            }
            if (diChuyen == false && dauTien == 1) {
                viTriChuot = -1;
            }
        }

    }

    /* Su kien keo chuot khi tao hinh ve lan dau*/
    @Override
    public void suKienKeoChuotVeHinh(MouseEvent e) {
        ((Line) (hinh)).setStroke(mauNetVe);
        ((Line) (hinh)).setStrokeWidth(netVe);
        ((Line) (hinh)).setStartX(diemBatDau.getX());
        ((Line) (hinh)).setStartY(diemBatDau.getY());
        ((Line) (hinh)).setEndX(e.getX());
        ((Line) (hinh)).setEndY(e.getY());
        this.dsChamTronDieuKhien[0].setCenterX(diemBatDau.getX());
        this.dsChamTronDieuKhien[0].setCenterY(diemBatDau.getY());
        this.dsChamTronDieuKhien[1].setCenterX(e.getX());
        this.dsChamTronDieuKhien[1].setCenterY(e.getY());
        polygon.getPoints()
                .setAll(dsChamTronDieuKhien[0].getCenterX(),
                        dsChamTronDieuKhien[0].getCenterY(),
                        dsChamTronDieuKhien[1].getCenterX(),
                        dsChamTronDieuKhien[1].getCenterY());
    }

    /*Su kien keo chuot de chinh sua*/
    @Override
    public void suKienKeoChuot(MouseEvent e) {
        if (thayDoiKichThuoc == true) {
            /*thay doi kich thuoc hinh ve*/
            if (viTriDiemDieuKhien == 0) {
                ((Line) (hinh)).setStartX(e.getX());
                ((Line) (hinh)).setStartY(e.getY());
                this.dsChamTronDieuKhien[0].setCenterX(e.getX());
                this.dsChamTronDieuKhien[0].setCenterY(e.getY());
            } else if (viTriDiemDieuKhien == 1) {
                this.dsChamTronDieuKhien[1].setCenterX(e.getX());
                this.dsChamTronDieuKhien[1].setCenterY(e.getY());
                ((Line) (hinh)).setEndX(dsChamTronDieuKhien[1].getCenterX());
                ((Line) (hinh)).setEndY(dsChamTronDieuKhien[1].getCenterY());
            }
            polygon.getPoints().setAll(dsChamTronDieuKhien[0].getCenterX(),
                    dsChamTronDieuKhien[0].getCenterY(),
                    dsChamTronDieuKhien[1].getCenterX(),
                    dsChamTronDieuKhien[1].getCenterY());
        } else if (diChuyen == true) {
            /*di chuyen hinh ve*/
            diChuyenDoiTuong(e.getX(), e.getY());
        }
    }

    /*Su kien khi tha chuot ra*/
    @Override
    public void suKienThaChuot(MouseEvent e) {
        diemKetThuc = new Diem(e.getX(), e.getY());
        /*======================================*/
 /*======================================*/
        thayDoiKichThuoc = false;
        diChuyen = false;
        dauTien = 1;
    }
}
