package com.mycompany.paintphake;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DieuKhien {

    protected VBox header;
    protected MenuItem newFile;
    protected MenuItem openFile;
    protected MenuItem saveFile;
    protected MenuItem saveAsFile;
    protected MenuItem exitFile;
    protected MenuItem fullGreen;
    protected MenuItem ExitFullGreen;
    protected RadioMenuItem hide;
    protected RadioMenuItem show;
    protected MenuItem about;
    protected ToggleButton nutNew;
    protected ToggleButton nutSave;
    protected ToggleButton nutChonSelect;
    protected ToggleButton nutXoa;
    protected ToggleButton nutLui;
    protected ToggleButton nutTien;
    protected ComboBox<Integer> coChu;
    protected ComboBox<String> fontChu;
    protected ToggleButton inDam;
    protected ToggleButton inNghieng;
    protected ToggleButton gachChan;
    protected VBox left;
    protected ToggleButton pencil;
    protected ToggleButton hinhChuNhat;
    protected ToggleButton duongThang;
    protected ToggleButton hinhTron;
    protected ToggleButton hinhTamGiac;
    protected ToggleButton hinhNguGiac;
    protected ToggleButton hinhLucGiac;
    protected ToggleButton hinhEllipse;
    protected ToggleButton hinhTamGiacVuong;
    protected ToggleButton hinhBinhHanh;
    protected ToggleButton hinhChuThap;
    protected ToggleButton hinhThangCan;
    protected ToggleButton hinhThoi;
    protected ToggleButton hinhMuiTenLen;
    protected ToggleButton hinhMuiTenXuong;
    protected ToggleButton hinhMuiTenTrai;
    protected ToggleButton hinhMuiTenPhai;
    protected ToggleButton hinhSao4Canh;
    protected ToggleButton hinhSao5Canh;
    protected ToggleButton hinhSao6Canh;

    protected MenuItem netVe1;
    protected MenuItem netVe2;
    protected MenuItem netVe3;
    protected MenuItem netVe4;
    protected ToggleButton mauNenHinh;
    protected ToggleButton mauNetVe;
    protected ToggleButton tay;
    protected ToggleButton vanBan;
    protected Rectangle mauChon;
    protected ColorPicker colorPicker;
    protected Button mauDen;
    protected Button mauTrang;
    protected Button mauDo;
    protected Button mauCam;
    protected Button mauVang;
    protected Button mauXanhLa;
    protected Button mauXanhDuong;
    protected Button mauHong;
    protected Button mauTim;
    protected ScrollPane main;
    protected GridPane footer;
    protected Label toaDoChuot;
    protected Label kichThuocKhungVe;
    protected Slider chinhChieuRongImage;
    protected Slider chinhChieuCaoImage;
    protected Slider chinhHaiChieuImage;
    protected Slider chinhChieuRong;
    protected Slider chinhChieuCao;
    protected VBox congCuChinhAnh;
    private ToggleGroup group2;

    {
        group2 = new ToggleGroup();
    }

    public DieuKhien() {
        header = new VBox();
        header.setId("IDHeader-DieuKhien");
        left = new VBox();
        left.setId("IDLeft-DieuKhien");
        main = new ScrollPane();
        main.setId("IDMain-DieuKhien");
        footer = new GridPane();
    }

    public void topDieuKhien() {

        //--1------------ thanh tieu de dau
        MenuBar menuBar = new MenuBar();
        menuBar.setId("menuBar-DieuKhienTop");

        Menu file = new Menu("File");
        file.getStyleClass().add("menu");
        newFile = new MenuItem("New", new ImageView(new Image("/images/newFile.png")));
        newFile.getStyleClass().add("menuItem");
        openFile = new MenuItem("Open", new ImageView(new Image("/images/openFile.png")));
        openFile.getStyleClass().add("menuItem");
        saveFile = new MenuItem("Save", new ImageView(new Image("/images/saveFile.png")));
        saveFile.getStyleClass().add("menuItem");
        saveAsFile = new MenuItem("Save as", new ImageView(new Image("/images/saveAs.png")));
        saveAsFile.getStyleClass().add("menuItem");
        exitFile = new MenuItem("Exit", new ImageView(new Image("/images/exit.png")));
        exitFile.getStyleClass().add("menuItem");
        file.getItems().addAll(newFile, openFile, saveFile, saveAsFile, exitFile);

        Menu view = new Menu("View");
        view.getStyleClass().add("menu");

        fullGreen = new MenuItem("Full Screen", new ImageView(new Image("/images/fullScreen.png")));
        fullGreen.getStyleClass().add("menuItem");

        ExitFullGreen = new MenuItem("Exit Full Screen", new ImageView(new Image("/images/exitFullScreen.png")));
        ExitFullGreen.getStyleClass().add("menuItem");

        view.getItems().addAll(fullGreen, ExitFullGreen);

        Menu property = new Menu("Property");
        property.getStyleClass().add("menu");
        ToggleGroup group = new ToggleGroup();
        hide = new RadioMenuItem("Hide", new ImageView(new Image("/images/hide.png")));
        hide.getStyleClass().add("menuItem");
        show = new RadioMenuItem("Show", new ImageView(new Image("/images/show.png")));
        show.getStyleClass().add("menuItem");
        hide.setToggleGroup(group);
        show.setToggleGroup(group);
        show.setSelected(true);
        property.getItems().addAll(show, hide);

        Menu help = new Menu("Help");
        help.getStyleClass().add("menu");
        about = new MenuItem("About", new ImageView(new Image("/images/about.png")));
        about.getStyleClass().add("menuItem");
        help.getItems().add(about);
        menuBar.getMenus().addAll(file, property, view, help);
        //-----------------------------------------------------------
        // --------------2--cong cu
        HBox congCu = new HBox();
        congCu.setId("congCu-DieuKhienTop");
        congCu.setPadding(new Insets(2));

        ToggleGroup group1 = new ToggleGroup();
        nutNew = new ToggleButton();
        nutNew.setToggleGroup(group1);
        nutNew.setGraphic(new ImageView(new Image("/images/newFile.png")));
        nutNew.setTooltip(new Tooltip("New file (Ctrl + N)"));
        nutNew.getStyleClass().addAll("toggleButton", "toggleButtonCongCuTren");

        nutSave = new ToggleButton();
        nutSave.setToggleGroup(group1);
        nutSave.setGraphic(new ImageView(new Image("/images/saveFile.png")));
        nutSave.setTooltip(new Tooltip("Save file (Ctrl + S)"));
        nutSave.getStyleClass().addAll("toggleButton", "toggleButtonCongCuTren");

        nutChonSelect = new ToggleButton();
        nutChonSelect.setToggleGroup(group2);
        nutChonSelect.setGraphic(new ImageView(new Image("/images/select.png")));
        nutChonSelect.setTooltip(new Tooltip("Selection"));
        nutChonSelect.getStyleClass().add("toggleButton");

        nutXoa = new ToggleButton();
        nutXoa.setDisable(true);
        nutXoa.setToggleGroup(group1);
        nutXoa.setGraphic(new ImageView(new Image("/images/delete.png")));
        nutXoa.setTooltip(new Tooltip("Delete"));
        nutXoa.getStyleClass().addAll("toggleButton", "toggleButtonCongCuTren");

        nutLui = new ToggleButton();
        nutLui.setToggleGroup(group1);
        nutLui.setGraphic(new ImageView(new Image("/images/undo.png")));
        nutLui.setTooltip(new Tooltip("Undo (Ctrl + Z)\nUndo last action"));
        nutLui.getStyleClass().addAll("toggleButton", "toggleButtonCongCuTren");

        nutTien = new ToggleButton();
        nutTien.setToggleGroup(group1);
        nutTien.setGraphic(new ImageView(new Image("/images/next.png")));
        nutTien.setTooltip(new Tooltip("Redo (Ctrl + Y)\nRedo last action"));
        nutTien.getStyleClass().addAll("toggleButton", "toggleButtonCongCuTren");
        congCu.getChildren().addAll(nutNew, nutSave, nutChonSelect, nutXoa, nutLui, nutTien);
        //------------------------------------------------------------------
        //------------
        HBox congCuVanBan = new HBox();
        congCuVanBan.setId("congCuVanBan-DieuKhienTop");
        congCuVanBan.setPadding(new Insets(2));
        // font chu
        ObservableList<String> f = FXCollections.observableArrayList("Arial");
        fontChu = new ComboBox<String>();
        fontChu.setValue("Arial");
        fontChu.setItems(f);
        fontChu.setMaxWidth(150);
        fontChu.setMinWidth(150);
        fontChu.setDisable(true);
        fontChu.getStyleClass().add("font");
        // co chu

        ObservableList<Integer> items = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33,
                34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
                70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91,
                92, 93, 94, 95, 96, 97, 98, 99, 100, 110, 120, 130, 140, 170, 200, 250, 300, 350, 400, 500, 600, 700,
                800, 900, 1000);
        coChu = new ComboBox<Integer>();
        coChu.setDisable(true);
        coChu.setValue(13);
        coChu.setItems(items);
        coChu.setMinHeight(20);
        coChu.setMaxWidth(70);
        coChu.getStyleClass().add("font");
        // in dam
        inDam = new ToggleButton("", new ImageView(new Image("/images/Bold.png")));
        inDam.setTooltip(new Tooltip("Bold (Ctrl + B)\nMake your text bold"));
        inDam.getStyleClass().add("toggleButton");
        inDam.setDisable(true);
        // in nghieng
        inNghieng = new ToggleButton("", new ImageView(new Image("/images/italic.png")));
        inNghieng.setTooltip(new Tooltip("Italic (Ctrl + I)\nItalicize your text"));
        inNghieng.getStyleClass().add("toggleButton");
        inNghieng.setDisable(true);
        // gach chan
        gachChan = new ToggleButton("", new ImageView(new Image("/images/underline.png")));
        gachChan.setTooltip(new Tooltip("Underline (Ctrl + U)\nUnderline your text"));
        gachChan.getStyleClass().add("toggleButton");
        gachChan.setDisable(true);
        // chon mau
        colorPicker = new ColorPicker();
        colorPicker.setId("colorPicker-TopDieuKhien");
        colorPicker.setTooltip(new Tooltip("Choose color and opacity"));
        // chon mau
        Group group3 = new Group();
        mauDen = new Button();
        mauDen.setMinHeight(20);
        mauDen.setMinWidth(30);
        mauDen.setTooltip(new Tooltip("Black"));
        mauDen.setId("mauDen");

        mauTrang = new Button();
        mauTrang.setMinHeight(20);
        mauTrang.setMinWidth(30);
        mauTrang.setTooltip(new Tooltip("White"));
        mauTrang.setId("mauTrang");

        mauDo = new Button();
        mauDo.setMinHeight(20);
        mauDo.setMinWidth(30);
        mauDo.setTooltip(new Tooltip("Red"));
        mauDo.setId("mauDo");

        mauCam = new Button();
        mauCam.setMinHeight(20);
        mauCam.setMinWidth(30);
        mauCam.setTooltip(new Tooltip("Orange"));
        mauCam.setId("mauCam");

        mauVang = new Button();
        mauVang.setMinHeight(20);
        mauVang.setMinWidth(30);
        mauVang.setTooltip(new Tooltip("Yellow"));
        mauVang.setId("mauVang");

        mauXanhLa = new Button();
        mauXanhLa.setMinHeight(20);
        mauXanhLa.setMinWidth(30);
        mauXanhLa.setTooltip(new Tooltip("Green"));
        mauXanhLa.setId("mauXanhLa");

        mauXanhDuong = new Button();
        mauXanhDuong.setMinHeight(20);
        mauXanhDuong.setMinWidth(30);
        mauXanhDuong.setTooltip(new Tooltip("Blue"));
        mauXanhDuong.setId("mauXanhDuong");

        mauHong = new Button();
        mauHong.setMinHeight(20);
        mauHong.setMinWidth(30);
        mauHong.setTooltip(new Tooltip("Pink"));
        mauHong.setId("mauHong");

        mauTim = new Button();
        mauTim.setMinHeight(20);
        mauTim.setMinWidth(30);
        mauTim.setTooltip(new Tooltip("Purple"));
        mauTim.setId("mauTim");

        group3.getChildren().addAll(mauDen, mauTrang, mauDo, mauCam, mauVang, mauXanhLa, mauXanhDuong, mauHong, mauTim);
        congCuVanBan.getChildren().addAll(fontChu, coChu, inDam, inNghieng, gachChan, colorPicker, mauDen, mauTrang, mauDo,
                mauCam, mauVang, mauXanhLa, mauXanhDuong, mauHong, mauTim);

        header.getChildren().addAll(menuBar, congCu, congCuVanBan);
    }

    public void leftDieuKhien() {

        Label nhanDan = new Label("  COLOR");
        nhanDan.setAlignment(Pos.BOTTOM_CENTER);
        nhanDan.setMinSize(53, 20);
        mauChon = new Rectangle(20, 210, 65, 30);
        mauChon.setArcHeight(10);
        mauChon.setArcWidth(10);

        //chon viet chi
        pencil = new ToggleButton();
        pencil.setToggleGroup(group2);
        pencil.setId("pencil-LeftDieuKhien");
        pencil.setMaxWidth(200);
        pencil.setGraphic(new ImageView(new Image("/images/pencil.png")));
        pencil.setTooltip(new Tooltip("Pencil"));
        pencil.getStyleClass().add("toggleButton");

        // chon hinh de ve
        hinhChuNhat = new ToggleButton("", new ImageView(new Image("/images/rectangle.png")));
        hinhChuNhat.setToggleGroup(group2);
        hinhChuNhat.setTooltip(new Tooltip("Rectangle"));
        hinhChuNhat.getStyleClass().add("toggleButton");
        duongThang = new ToggleButton("", new ImageView(new Image("/images/line.png")));
        duongThang.setToggleGroup(group2);
        duongThang.setTooltip(new Tooltip("Line"));
        duongThang.getStyleClass().add("toggleButton");
        hinhEllipse = new ToggleButton("", new ImageView(new Image("/images/circle.png")));
        hinhEllipse.setToggleGroup(group2);
        hinhEllipse.setTooltip(new Tooltip("Oval"));
        hinhEllipse.getStyleClass().add("toggleButton");
        hinhTamGiac = new ToggleButton("", new ImageView(new Image("/images/tamGiac.png")));
        hinhTamGiac.setToggleGroup(group2);
        hinhTamGiac.setTooltip(new Tooltip("Triangle"));
        hinhTamGiac.getStyleClass().add("toggleButton");
        hinhTamGiacVuong = new ToggleButton("", new ImageView(new Image("/images/tamGiacVuong.png")));
        hinhTamGiacVuong.setToggleGroup(group2);
        hinhTamGiacVuong.setTooltip(new Tooltip("Right triangle"));
        hinhTamGiacVuong.getStyleClass().add("toggleButton");
        hinhNguGiac = new ToggleButton("", new ImageView(new Image("/images/nguGiac.png")));
        hinhNguGiac.setToggleGroup(group2);
        hinhNguGiac.setTooltip(new Tooltip("Pentagon"));
        hinhNguGiac.getStyleClass().add("toggleButton");
        hinhLucGiac = new ToggleButton("", new ImageView(new Image("/images/lucGiac.png")));
        hinhLucGiac.setToggleGroup(group2);
        hinhLucGiac.setTooltip(new Tooltip("Hexagon"));
        hinhLucGiac.getStyleClass().add("toggleButton");
        hinhBinhHanh = new ToggleButton("", new ImageView(new Image("/images/hinhBinhHanh.png")));
        hinhBinhHanh.setToggleGroup(group2);
        hinhBinhHanh.setTooltip(new Tooltip("Parallelogram"));
        hinhBinhHanh.getStyleClass().add("toggleButton");
        hinhChuThap = new ToggleButton("", new ImageView(new Image("/images/hinhChuThap.png")));
        hinhChuThap.setToggleGroup(group2);
        hinhChuThap.setTooltip(new Tooltip("Cross"));
        hinhChuThap.getStyleClass().add("toggleButton");
        hinhThangCan = new ToggleButton("", new ImageView(new Image("/images/hinhThangCan.png")));
        hinhThangCan.setToggleGroup(group2);
        hinhThangCan.setTooltip(new Tooltip("Isosceles trapezoid"));
        hinhThangCan.getStyleClass().add("toggleButton");
        hinhThoi = new ToggleButton("", new ImageView(new Image("/images/hinhThoi.png")));
        hinhThoi.setToggleGroup(group2);
        hinhThoi.setTooltip(new Tooltip("Diamond"));
        hinhThoi.getStyleClass().add("toggleButton");
        hinhMuiTenLen = new ToggleButton("", new ImageView(new Image("/images/muiTenLen.png")));
        hinhMuiTenLen.setToggleGroup(group2);
        hinhMuiTenLen.setTooltip(new Tooltip("Up arrow"));
        hinhMuiTenLen.getStyleClass().add("toggleButton");
        hinhMuiTenXuong = new ToggleButton("", new ImageView(new Image("/images/muiTenXuong.png")));
        hinhMuiTenXuong.setToggleGroup(group2);
        hinhMuiTenXuong.setTooltip(new Tooltip("Down arrow"));
        hinhMuiTenXuong.getStyleClass().add("toggleButton");
        hinhMuiTenTrai = new ToggleButton("", new ImageView(new Image("/images/muiTenTrai.png")));
        hinhMuiTenTrai.setToggleGroup(group2);
        hinhMuiTenTrai.setTooltip(new Tooltip("Left arrow"));
        hinhMuiTenTrai.getStyleClass().add("toggleButton");
        hinhMuiTenPhai = new ToggleButton("", new ImageView(new Image("/images/muiTenPhai.png")));
        hinhMuiTenPhai.setToggleGroup(group2);
        hinhMuiTenPhai.setTooltip(new Tooltip("Right arrow"));
        hinhMuiTenPhai.getStyleClass().add("toggleButton");
        hinhSao4Canh = new ToggleButton("", new ImageView(new Image("/images/hinhStar4.png")));
        hinhSao4Canh.setToggleGroup(group2);
        hinhSao4Canh.setTooltip(new Tooltip("Four-point star"));
        hinhSao4Canh.getStyleClass().add("toggleButton");
        hinhSao5Canh = new ToggleButton("", new ImageView(new Image("/images/hinhStar5.png")));
        hinhSao5Canh.setToggleGroup(group2);
        hinhSao5Canh.setTooltip(new Tooltip("Five-point star"));
        hinhSao5Canh.getStyleClass().add("toggleButton");
        hinhSao6Canh = new ToggleButton("", new ImageView(new Image("/images/hinhStar6.png")));
        hinhSao6Canh.setToggleGroup(group2);
        hinhSao6Canh.setTooltip(new Tooltip("Six-point star"));
        hinhSao6Canh.getStyleClass().add("toggleButton");

        ScrollPane scrollMenuHinh = new ScrollPane();
        scrollMenuHinh.setId("scrollMenuHinhLeftChonHinh");
        scrollMenuHinh.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollMenuHinh.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollMenuHinh.setPadding(new Insets(0));

        TilePane paneChuaHinh = new TilePane(hinhChuNhat, duongThang, hinhEllipse, hinhTamGiac, hinhTamGiacVuong,
                hinhNguGiac, hinhLucGiac, hinhBinhHanh, hinhChuThap, hinhThangCan, hinhThoi, hinhMuiTenLen,
                hinhMuiTenXuong, hinhMuiTenTrai, hinhMuiTenPhai, hinhSao4Canh, hinhSao5Canh, hinhSao6Canh);
        paneChuaHinh.setId("paneChuaHinh-LeftDieuKhien");
        scrollMenuHinh.setContent(paneChuaHinh);
        MenuItem menuHinh = new MenuItem("", scrollMenuHinh);
        menuHinh.setId("menuHinhLeftDieuKhien");

        MenuButton chonHinh = new MenuButton("", new ImageView(new Image("/images/shape.png")), menuHinh);
        chonHinh.setTooltip(new Tooltip("Choose shape"));
        chonHinh.setId("chonHinh-LeftDieuKhien");
        //chon ne ve
        netVe1 = new MenuItem("1px", new ImageView(new Image("/images/netVe1.png")));
        netVe1.setId("netVe1Left");
        netVe2 = new MenuItem("2px", new ImageView(new Image("/images/netVe2.png")));
        netVe2.setId("netVe2Left");
        netVe3 = new MenuItem("5px", new ImageView(new Image("/images/netVe3.png")));
        netVe3.setId("netVe3Left");
        netVe4 = new MenuItem("6px", new ImageView(new Image("/images/netVe4.png")));
        netVe4.setId("netVe4Left");
        netVe4.getStyleClass().add("netVe");
        MenuButton menuNetVe = new MenuButton("", new ImageView(new Image("/images/netVe.png")), netVe1, netVe2, netVe3, netVe4);
        menuNetVe.setId("chonNet-LeftDieuKhien");
        menuNetVe.setTooltip(new Tooltip("Sharpness"));
        // mau net ve
        mauNetVe = new ToggleButton("", new ImageView(new Image("/images/mauNetVe.png")));
        mauNetVe.setToggleGroup(group2);
        mauNetVe.setMaxWidth(200);
        mauNetVe.setTooltip(new Tooltip("Choose the border color of the shape"));
        mauNetVe.getStyleClass().add("toggleButton");
        // mau nen hinh
        mauNenHinh = new ToggleButton("", new ImageView(new Image("/images/mauHinh.png")));
        mauNenHinh.setToggleGroup(group2);
        mauNenHinh.setMaxWidth(200);
        mauNenHinh.setTooltip(new Tooltip("Choose the background color of the shape"));
        mauNenHinh.getStyleClass().add("toggleButton");
        // gom tay
        tay = new ToggleButton("", new ImageView(new Image("/images/gomTay.png")));
        tay.setToggleGroup(group2);
        tay.setMaxWidth(200);
        tay.setMaxHeight(150);
        tay.setTooltip(new Tooltip("Eraser"));
        tay.getStyleClass().add("toggleButton");
        // van ban
        vanBan = new ToggleButton("", new ImageView(new Image("/images/vanBan.png")));
        vanBan.setToggleGroup(group2);
        vanBan.setMaxWidth(200);
        vanBan.setTooltip(new Tooltip("Text"));
        vanBan.getStyleClass().add("toggleButton");
        // chinh kich thuoc image
        Text imageWidth = new Text("  Image width");
        imageWidth.setFont(Font.font("Arial", 10));
        chinhChieuRongImage = new Slider(0.05, 2, 0.5);
        chinhChieuRongImage.getStyleClass().add("slider-kichthuoc");
        chinhChieuRongImage.setMinWidth(65);
        chinhChieuRongImage.setMaxWidth(65);
        chinhChieuRongImage.setShowTickLabels(true);

        Text imageHeight = new Text(" Image height");
        imageHeight.setFont(Font.font("Arial", 10));
        chinhChieuCaoImage = new Slider(0.05, 2, 0.5);
        chinhChieuCaoImage.getStyleClass().add("slider-kichthuoc");
        chinhChieuCaoImage.setMinWidth(65);
        chinhChieuCaoImage.setMaxWidth(65);
        chinhChieuCaoImage.setShowTickLabels(true);

        Text imageWidthHeight = new Text("  Image W&H");
        imageWidthHeight.setFont(Font.font("Arial", 10));
        chinhHaiChieuImage = new Slider(0.05, 2, 0.5);
        chinhHaiChieuImage.getStyleClass().add("slider-kichthuoc");
        chinhHaiChieuImage.setMinWidth(65);
        chinhHaiChieuImage.setMaxWidth(65);
        chinhHaiChieuImage.setShowTickLabels(true);

        congCuChinhAnh = new VBox();
        congCuChinhAnh.getChildren().addAll(imageWidth, chinhChieuRongImage,
                imageHeight, chinhChieuCaoImage, imageWidthHeight, chinhHaiChieuImage);
        congCuChinhAnh.setVisible(false);
        left.getChildren().addAll(pencil, chonHinh, menuNetVe, mauNetVe, mauNenHinh, tay, vanBan, nhanDan, mauChon, congCuChinhAnh);
        left.setPadding(new Insets(2));
        left.setSpacing(5);
    }

    public void mainDieuKhien() {

    }

    public void bottomDieuKhien() {
        footer = new GridPane();
        footer.setId("IDFooter-DieuKhien");

        toaDoChuot = new Label("");
        toaDoChuot.setGraphic(new ImageView(new Image("/images/toaDoChuot.png")));
        toaDoChuot.setMinWidth(150);

        kichThuocKhungVe = new Label();
        kichThuocKhungVe.setGraphic(new ImageView(new Image("/images/kichThuoc.png")));
        kichThuocKhungVe.setMinWidth(135);
        footer.add(toaDoChuot, 0, 0);
        footer.add(kichThuocKhungVe, 2, 0);

        Label lb1 = new Label("Width");
        lb1.setMinWidth(100);
        lb1.setAlignment(Pos.CENTER_RIGHT);
        chinhChieuRong = new Slider(20, 2000, 800);
        chinhChieuRong.getStyleClass().add("slider-kichthuoc");
        chinhChieuRong.setMinWidth(200);
        footer.add(lb1, 3, 0);
        footer.add(chinhChieuRong, 4, 0);

        Label lb2 = new Label("Height");
        lb2.setMinWidth(50);
        lb2.setAlignment(Pos.CENTER_RIGHT);
        chinhChieuCao = new Slider(20, 2000, 800);
        chinhChieuCao.getStyleClass().add("slider-kichthuoc");
        chinhChieuCao.setMinWidth(200);
        footer.add(lb2, 5, 0);
        footer.add(chinhChieuCao, 6, 0);
    }

    public void tatVanBan() {
        inDam.setDisable(true);
        inNghieng.setDisable(true);
        fontChu.setDisable(true);
        coChu.setDisable(true);
        gachChan.setDisable(true);
    }

    public void BatVanBan() {
        inDam.setDisable(false);
        inNghieng.setDisable(false);
        fontChu.setDisable(false);
        coChu.setDisable(false);
        gachChan.setDisable(false);
    }
}
