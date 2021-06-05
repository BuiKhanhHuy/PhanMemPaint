package com.mycompany.paintphake;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class HinhVe {

    protected double netVe;
    protected Color mauNetVe;
    protected Color mauNen;
    protected Image hinhNen;
    protected double doMoNen;
    protected double doMoNetVe;
    protected Diem diemBatDau;
    protected Diem diemKetThuc;
    protected Diem diemChuotNhan;
    protected int viTriChuot = 1;
    protected int viTriDiemDieuKhien;
    protected int soDiemDieuKhien = 8;
    protected Circle dsChamTronDieuKhien[];

    protected Rectangle khungBao;
    protected int maHinh;
    protected boolean diChuyen;
    protected boolean thayDoiKichThuoc;
    protected boolean xoayHinh;
    protected boolean xoaHinh;
    protected Shape hinh;
    // bien tam cuc bo
    protected Diem tam;
    protected double w;
    protected double h;
    protected static boolean isDoiMauNen = false;
    protected static boolean isDoiMauNetVe = false;
    protected boolean isTaoMoi = false;

    public HinhVe() {
    }

    public HinhVe(double netVe, Color mauNetVe, Color mauNen) {
        this.netVe = netVe;
        this.mauNetVe = mauNetVe;
        this.mauNen = mauNen;
    }

    public void capNhat() {
    }

    public HinhVe(double netVe, Color mauNetVe, Color mauNen,
            Image hinhNen, double doMoNen, double doMoNetVe,
            Diem diemBatDau, Diem diemKetThuc, Diem diemChuotNhan,
            int viTriChuot, int soDiemDieuKhien, Circle dsChamTronDieuKhien[],
            Rectangle khungBao, int maHinh, boolean diChuyen, boolean thayDoiKichThuoc, boolean xoayHinh) {
        this.netVe = netVe;
        this.mauNetVe = mauNetVe;
        this.mauNen = mauNen;
        this.hinhNen = hinhNen;
        this.doMoNen = doMoNen;
        this.doMoNetVe = doMoNetVe;
        this.diemBatDau = diemBatDau;
        this.diemKetThuc = diemKetThuc;
        this.diemChuotNhan = diemChuotNhan;
        this.viTriChuot = viTriChuot;
        this.soDiemDieuKhien = soDiemDieuKhien;
        this.dsChamTronDieuKhien = dsChamTronDieuKhien;
        this.khungBao = khungBao;
        this.maHinh = maHinh;
        this.diChuyen = diChuyen;
        this.thayDoiKichThuoc = thayDoiKichThuoc;
        this.xoayHinh = xoayHinh;
    }

    /*Cap nhat thong tin va ve hinh chu nhat*/
    public void veHinh(double x, double y, double w, double h) {
    }

    /* Ve khung bao*/
    public void veKhung() {
    }

    /* khoang cach hai diem*/
    public double khoangCachHaiDiem(Diem p1, Diem p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    /* khoang cach hai diem*/
    public double khoangCachHaiDiem(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /* kiem tra mot diem co thuoc duong tron*/
    public boolean kiemTraDiemCoThuocDoiTuong(Circle circle, Diem p) {
        if (khoangCachHaiDiem(circle.getCenterX(), circle.getCenterY(), p.getX(), p.getY()) <= circle.getRadius()) {
            return true;
        } else {
            return false;
        }
    }

    /* tra ve duong tron ma chuot duoc click*/
    public int traVeViTriDiemDoiTuong(Diem diemChuot, Circle[] cir) {
        for (int i = 0; i < cir.length; i++) {
            if (kiemTraDiemCoThuocDoiTuong(cir[i], diemChuot) == true) {
                return i;
            }
        }
        return -1;
    }

    /*kiem tra chuot duoc click ra khoi vung dieu khien*/
    public int ktDiemCoTrongVungDieuKhien(Diem p0, Diem p1, Diem p3, Diem p) {
        if (p.getX() >= p0.getX() && p.getX() <= p1.getX() && p.getY() >= p0.getY() && p.getY() <= p3.getY()) {
            return 1;
        }
        return -1;
    }

    /*Thay doi kich thuoc khung bao*/
    public void thayDoiKichThuoc(double ex, double ey, int viTriDiemDieuKhien) {
    }

    /*Cap nhat cham tron*/
    public void capNhatChamTron(Diem p1, Diem p2) {
    }

    /* Cap nhat thong so khung dieu khien va cham tron dieu khien*/
    public void capNhatThongSoKhung(double ex, double ey) {
    }


    /* Su kien khi chuot duoc click*/
    public void suKienClickChuot(MouseEvent e) {
    }

    /* Su kien keo chuot khi tao hinh ve lan dau*/
    public void suKienKeoChuotVeHinh(MouseEvent e) {
    }

    /*Su kien keo chuot de chinh sua*/
    public void suKienKeoChuot(MouseEvent e) {

    }

    /*Su kien khi tha chuot ra*/
    public void suKienThaChuot(MouseEvent e) {
    }

    public boolean clickChuotDoiMauNen(MouseEvent e) {
        return false;
    }

}
