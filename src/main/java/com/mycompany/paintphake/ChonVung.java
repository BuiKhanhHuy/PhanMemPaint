package com.mycompany.paintphake;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ChonVung extends HinhVe {

    // bien tam cuc bo
    {
        this.maHinh = 20;
        this.tam = new Diem();
        this.diemBatDau = new Diem();
        this.diemKetThuc = new Diem();
        this.diemChuotNhan = new Diem();
        this.khungBao = new Rectangle();
        this.khungBao.setId("Chon-Vung");
        this.khungBao.setFill(Color.rgb(0, 0, 0, 0));
        this.khungBao.setStrokeWidth(1);
        this.dsChamTronDieuKhien = new Circle[soDiemDieuKhien];
        for (int i = 0; i < soDiemDieuKhien; i++) {
            dsChamTronDieuKhien[i] = new Circle();
            dsChamTronDieuKhien[i].getStyleClass().add("cham-tron-dieu-khien");
            dsChamTronDieuKhien[i].setId("cham-tron-dieu-khien-" + i);
        }
    }

    public void toMau() {

    }

    public ChonVung() {
    }


    /* Ve khung bao*/
    @Override
    public void veKhung() {
        khungBao.setX(diemChuotNhan.getX());
        khungBao.setY(diemChuotNhan.getY());
        khungBao.setWidth(1);
        khungBao.setHeight(1);
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

    /*Thay doi kich thuoc khung bao*/
    @Override
    public void thayDoiKichThuoc(double ex, double ey, int viTriDiemDieuKhien) {
        switch (viTriDiemDieuKhien) {
            case 0:
                diemBatDau.setX(dsChamTronDieuKhien[2].getCenterX());
                diemBatDau.setY(dsChamTronDieuKhien[2].getCenterY());
                if (ex < diemBatDau.getX()) {
                    khungBao.setX(ex);
                    khungBao.setWidth(Math.abs(ex - diemBatDau.getX()));
                    tam.setX(diemBatDau.getX());
                }
                if (ey < diemBatDau.getY()) {
                    khungBao.setY(ey);
                    khungBao.setHeight(Math.abs(ey - diemBatDau.getY()));
                    tam.setY(diemBatDau.getY());
                }
                break;
            case 1:
                diemBatDau.setX(dsChamTronDieuKhien[3].getCenterX());
                diemBatDau.setY(dsChamTronDieuKhien[3].getCenterY());
                if (ex > diemBatDau.getX()) {
                    khungBao.setX(diemBatDau.getX());
                    khungBao.setWidth(Math.abs(ex - diemBatDau.getX()));
                    tam.setX(ex);
                }
                if (ey < diemBatDau.getY()) {
                    khungBao.setY(ey);
                    khungBao.setHeight(Math.abs(ey - diemBatDau.getY()));
                    tam.setY(diemBatDau.getY());
                }
                break;
            case 2:
                diemBatDau.setX(dsChamTronDieuKhien[0].getCenterX());
                diemBatDau.setY(dsChamTronDieuKhien[0].getCenterY());
                if (ex > diemBatDau.getX()) {
                    khungBao.setX(diemBatDau.getX());
                    khungBao.setWidth(Math.abs(ex - diemBatDau.getX()));
                    tam.setX(ex);
                }
                if (ey > diemBatDau.getY()) {
                    khungBao.setY(diemBatDau.getY());
                    khungBao.setHeight(Math.abs(ey - diemBatDau.getY()));
                    tam.setY(ey);
                }
                break;
            case 3:
                diemBatDau.setX(dsChamTronDieuKhien[1].getCenterX());
                diemBatDau.setY(dsChamTronDieuKhien[1].getCenterY());
                if (ex < diemBatDau.getX()) {
                    khungBao.setX(ex);
                    khungBao.setWidth(Math.abs(ex - diemBatDau.getX()));
                    tam.setX(diemBatDau.getX());
                }
                if (ey > diemBatDau.getY()) {
                    khungBao.setY(diemBatDau.getY());
                    khungBao.setHeight(Math.abs(ey - diemBatDau.getY()));
                    tam.setY(ey);
                }
                break;
            case 4:
                khungBao.setX(dsChamTronDieuKhien[3].getCenterX());
                tam.setX(dsChamTronDieuKhien[2].getCenterX());
                tam.setY(dsChamTronDieuKhien[2].getCenterY());
                khungBao.setWidth(Math.abs(dsChamTronDieuKhien[2].getCenterX() - dsChamTronDieuKhien[3].getCenterX()));
                if (ey < dsChamTronDieuKhien[3].getCenterY()) {
                    khungBao.setY(ey);
                    khungBao.setHeight(Math.abs(ey - dsChamTronDieuKhien[3].getCenterY()));
                }
                break;
            case 5:
                khungBao.setX(dsChamTronDieuKhien[0].getCenterX());
                khungBao.setY(dsChamTronDieuKhien[0].getCenterY());
                khungBao.setHeight(Math.abs(dsChamTronDieuKhien[3].getCenterY() - dsChamTronDieuKhien[0].getCenterY()));
                tam.setY(dsChamTronDieuKhien[2].getCenterY());
                if (ex > dsChamTronDieuKhien[0].getCenterX()) {
                    khungBao.setWidth(Math.abs(ex - dsChamTronDieuKhien[0].getCenterX()));
                    tam.setX(ex);
                }
                break;
            case 6:
                khungBao.setX(dsChamTronDieuKhien[0].getCenterX());
                khungBao.setY(dsChamTronDieuKhien[0].getCenterY());
                tam.setX(dsChamTronDieuKhien[1].getCenterX());
                khungBao.setWidth(Math.abs(dsChamTronDieuKhien[1].getCenterX() - dsChamTronDieuKhien[0].getCenterX()));
                if (ey > dsChamTronDieuKhien[0].getCenterY()) {
                    khungBao.setHeight(Math.abs(ey - dsChamTronDieuKhien[0].getCenterY()));
                    tam.setY(ey);
                }
                break;
            case 7:
                khungBao.setY(dsChamTronDieuKhien[1].getCenterY());
                tam.setX(dsChamTronDieuKhien[2].getCenterX());
                tam.setY(dsChamTronDieuKhien[2].getCenterY());
                khungBao.setHeight(Math.abs(dsChamTronDieuKhien[2].getCenterY() - dsChamTronDieuKhien[1].getCenterY()));
                if (ex < dsChamTronDieuKhien[1].getCenterX()) {
                    khungBao.setX(ex);
                    khungBao.setWidth(Math.abs(ex - dsChamTronDieuKhien[1].getCenterX()));
                }
                break;
        }

        this.capNhatChamTron(new Diem(khungBao.getX(), khungBao.getY()), tam);
    }

    /*Cap nhat cham tron*/
    @Override
    public void capNhatChamTron(Diem p1, Diem p2) {
        dsChamTronDieuKhien[0].setCenterX(p1.getX());
        dsChamTronDieuKhien[0].setCenterY(p1.getY());
        dsChamTronDieuKhien[1].setCenterX(p2.getX());
        dsChamTronDieuKhien[1].setCenterY(p1.getY());
        dsChamTronDieuKhien[2].setCenterX(p2.getX());
        dsChamTronDieuKhien[2].setCenterY(p2.getY());
        dsChamTronDieuKhien[3].setCenterX(p1.getX());
        dsChamTronDieuKhien[3].setCenterY(p2.getY());
        dsChamTronDieuKhien[4].setCenterX(((p2.getX() - p1.getX()) / 2 + p1.getX()));
        dsChamTronDieuKhien[4].setCenterY(p1.getY());
        dsChamTronDieuKhien[5].setCenterX(p2.getX());
        dsChamTronDieuKhien[5].setCenterY(((p2.getY() - p1.getY()) / 2 + p1.getY()));
        dsChamTronDieuKhien[6].setCenterX(((p2.getX() - p1.getX()) / 2 + p1.getX()));
        dsChamTronDieuKhien[6].setCenterY(p2.getY());
        dsChamTronDieuKhien[7].setCenterX(p1.getX());
        dsChamTronDieuKhien[7].setCenterY(((p2.getY() - p1.getY()) / 2 + p1.getY()));
        for (int i = 0; i < soDiemDieuKhien; i++) {
            dsChamTronDieuKhien[i].setRadius(7);
        }
    }

    /* Cap nhat thong so khung dieu khien va cham tron dieu khien*/
    @Override
    public void capNhatThongSoKhung(double ex, double ey) {
        if (diemBatDau.getX() <= ex) {
            if (diemBatDau.getY() <= ey) {
                khungBao.setX(diemBatDau.getX());
                khungBao.setY(diemBatDau.getY());
                tam = new Diem(ex, ey);
            } else {
                khungBao.setX(diemBatDau.getX());
                khungBao.setY(ey);
                tam = new Diem(ex, diemBatDau.getY());
            }
        } else {
            if (diemBatDau.getY() <= ey) {
                khungBao.setX(ex);
                khungBao.setY(diemBatDau.getY());
                tam = new Diem(diemBatDau.getX(), ey);
            } else {
                khungBao.setX(ex);
                khungBao.setY(ey);
                tam = new Diem(diemBatDau.getX(), diemBatDau.getY());
            }

        }
        this.capNhatChamTron(new Diem(khungBao.getX(), khungBao.getY()), tam);
        khungBao.setWidth(Math.abs(ex - diemBatDau.getX()));
        khungBao.setHeight(Math.abs(ey - diemBatDau.getY()));
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

            System.out.println("duoc thay doi kich thuoc!");
            /*kiem tra co duoc di chuyen*/
        } else if (ktDiemCoTrongVungDieuKhien(new Diem(dsChamTronDieuKhien[0].getCenterX(), dsChamTronDieuKhien[0].getCenterY()),
                new Diem(dsChamTronDieuKhien[2].getCenterX(), dsChamTronDieuKhien[2].getCenterY()),
                new Diem(dsChamTronDieuKhien[6].getCenterX(), dsChamTronDieuKhien[6].getCenterY()),
                diemChuotNhan) != -1
                && traVeViTriDiemDoiTuong(diemChuotNhan, dsChamTronDieuKhien) == -1) {
            diChuyen = true;
            h = diemChuotNhan.getY() - khungBao.getY();
            w = diemChuotNhan.getX() - khungBao.getX();
        } else {
            viTriChuot = -1;
        }
    }

    /* Su kien keo chuot khi tao hinh ve lan dau*/
    @Override
    public void suKienKeoChuotVeHinh(MouseEvent e) {
        this.veKhung();
        capNhatThongSoKhung(e.getX(), e.getY());
    }

    /*Su kien keo chuot de chinh sua*/
    @Override
    public void suKienKeoChuot(MouseEvent e) {
        if (thayDoiKichThuoc == true) {
            /*thay doi kich thuoc hinh ve*/
            thayDoiKichThuoc(e.getX(), e.getY(), viTriDiemDieuKhien);
        } else if (diChuyen == true) {
            /*di chuyen hinh ve*/
            this.khungBao.setX(e.getX() - w);
            this.khungBao.setY(e.getY() - h);
            this.capNhatChamTron(new Diem(khungBao.getX(), khungBao.getY()), new Diem(khungBao.getX() + khungBao.getWidth(), khungBao.getY() + khungBao.getHeight()));
        }
    }

    /*Su kien khi tha chuot ra*/
    @Override
    public void suKienThaChuot(MouseEvent e) {
        diemKetThuc = new Diem(e.getX(), e.getY());
        thayDoiKichThuoc = false;
        diChuyen = false;
    }

}
