package com.mycompany.paintphake;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Star5 extends HinhThoi {
    {
        maHinh = 17;
        
    }

    public Star5() {
        super();
    }

   public Star5(double netVe, Color mauNetVe, Color mauNen) {
        this.netVe = netVe;
        this.mauNetVe = mauNetVe;
        this.mauNen = mauNen;
    }

    public Star5(double netVe, Color mauNetVe, Color mauNen,
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
          ((Polygon) (hinh)).getPoints().setAll(x + 0.2*w, y + h, x + 0.275*w, y + 0.625*h, 
               x, y + 0.4*h, x + 0.35*w, y + 0.375*h, x + 0.5*w, y, x + 0.65*w, y + 0.375*h,
               x + w, y + 0.4*h, x + 0.725*w, y + 0.625*h, x + 0.8*w, y + h, x + 0.5*w, y + 0.8*h);
    }

}
