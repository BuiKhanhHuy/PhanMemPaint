package com.mycompany.paintphake;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class MuiTenXuong extends HinhThoi {
    {
        maHinh = 11;
    }
    public MuiTenXuong() {
        super();
    }

   public MuiTenXuong(double netVe, Color mauNetVe, Color mauNen) {
        this.netVe = netVe;
        this.mauNetVe = mauNetVe;
        this.mauNen = mauNen;
    }

    public MuiTenXuong(double netVe, Color mauNetVe, Color mauNen,
            Image hinhNen, double doMoNen, double doMoNetVe,
            Diem diemBatDau, Diem diemKetThuc, Diem diemChuotNhan,
            int viTriChuot, int soDiemDieuKhien, Circle dsChamTronDieuKhien[],
            Rectangle khungBao, int maHinh, boolean diChuyen, boolean thayDoiKichThuoc, boolean xoayHinh) {
        super(netVe, mauNetVe, mauNen, hinhNen, doMoNen, doMoNetVe, diemBatDau,
                diemKetThuc, diemChuotNhan, viTriChuot, soDiemDieuKhien, dsChamTronDieuKhien, khungBao, maHinh, diChuyen, thayDoiKichThuoc, xoayHinh);
    }

    /*Cap nhat thong tin va ve tam giac*/
    @Override
    public void veHinh(double x, double y, double w, double h) {
         mau_Net_Ve();
         ((Polygon) (hinh)).getPoints().setAll(x + 0.5 * w, y + h, x , y + 0.5 * h,
                x + 0.25*w, y + 0.5*h,  x + 0.25*w, y,  x + 0.75*w, y,  x + 0.75*w, y + h*0.5, x + w, y + 0.5*h);
    }

}
