package com.mycompany.paintphake;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HinhAnh extends HinhVe {

    protected Image image;
    protected ImageView imageView;
    {
        maHinh = 21;
    }
    public HinhAnh() {

    }

    public HinhAnh(Image image, ImageView imageView) {
        this.image = image;
        this.imageView = imageView;
    }
    
}
