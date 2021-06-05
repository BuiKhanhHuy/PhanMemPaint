package com.mycompany.paintphake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class App extends Application {

    private int lanDau = 1;
    private static Scene scene;
    private PaintingFrame pane;
    private final BorderPane mainGreen;
    private final DieuKhien dieuKhien;
    private GiaTri giaTri;
    private final ScrollPane scrollPane;
    private int maHinh;
    private ArrayList<HinhVe> listHinhVe;
    private ArrayList<HinhVe> listHinhVeChuaTam;
    private Color mauDangChon;
    private Diem tam;
    private int k = 0;
    private HinhVe shape;
    private HinhVe hinhVe = new HinhChuNhat();

    {
        maHinh = 0;
        listHinhVe = new ArrayList<HinhVe>();
        listHinhVeChuaTam = new ArrayList<HinhVe>();
        scrollPane = new ScrollPane();
        scrollPane.setId("scrollPane");
        mainGreen = new BorderPane();
        dieuKhien = new DieuKhien();
        dieuKhien.topDieuKhien();
        dieuKhien.leftDieuKhien();
        dieuKhien.bottomDieuKhien();
        dieuKhien.nutLui.setDisable(true);
        dieuKhien.nutTien.setDisable(true);
        giaTri = new GiaTri();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        mainGreen.setCenter(scrollPane);
        mainGreen.setTop(dieuKhien.header);
        mainGreen.setLeft(dieuKhien.left);
        mainGreen.setBottom(dieuKhien.footer);
        mauDangChon = Color.BLACK;
    }

    @Override
    public void start(Stage stage) throws IOException {
        pane = new PaintingFrame();
        pane.setStyle("-fx-background-color: White;-fx-border-color: #0101DF;");
        BorderPane.setAlignment(pane, Pos.TOP_LEFT);
        scrollPane.setContent(pane);
        pane.setMaxSize(dieuKhien.chinhChieuRong.getValue(), dieuKhien.chinhChieuCao.getValue());
        pane.setMinSize(dieuKhien.chinhChieuRong.getValue(), dieuKhien.chinhChieuCao.getValue());
        dieuKhien.kichThuocKhungVe.setText(String.format("%s%.1f%s%.1f", "W: ",
                pane.getMaxWidth(), "  H: ", pane.getMaxHeight()));

        /*Chon hinh de ve*/
        chonShape();
        /*thanh dieu khien ben trai*/
        congCuDieuKhienBenTrai();
        /*thanh trang thai*/
        thanhTrangThai(stage);
        /*thanh cong cu phia tren*/
        thanhCongCuPhiaTren(stage);
        /*thanh cong cu phia duoi*/
        thanhCongCuPhiaDuoi();
        /*thanh footer*/
        thanhFooter();

        /*==================================================*/
        scene = new Scene(mainGreen, 1000, 700);
        scene.getStylesheets().addAll("/Style/StyleShape.css", "/Style/StyleDieuKhien.css");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/paint.png"));
        stage.setTitle("PAINT");
        stage.show();
        /*==================================================*/
    }

    public static void main(String[] args) {
        launch();
    }

    /*Ve hinh*/
    public void ve() {
        switch (maHinh) {
            case 0:
                pane.getChildren().add(((Pencil) shape).hinh);
                break;
            case 1:

//                pane.getChildren().add(((DuongThang) shape).hinh);
//                pane.getChildren().add(((DuongThang) shape).polygon);
//                pane.getChildren().addAll(shape.dsChamTronDieuKhien);
                break;
            /*case 2:
                pane.getChildren().add(shape2);
                pane.getChildren().add(shape2.khungBao);
                pane.getChildren().addAll(shape2.dsChamTronDieuKhien);
                break;
            case 3:
                pane.getChildren().add(shape4);
                pane.getChildren().add(shape4.khungBao);
                pane.getChildren().addAll(shape4.dsChamTronDieuKhien);
                break;
            case 19:
                break;
            default:
                pane.getChildren().add(shape5);
                pane.getChildren().add(shape5.khungBao);
                pane.getChildren().addAll(shape5.dsChamTronDieuKhien);
                break;*/
        }
        switch (maHinh) {
            /*to mau cho nen shap*/
            case -1:
                pane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        System.out.println("Click pane");
                        for (int i = 0; i < listHinhVe.size(); i++) {
                            if (listHinhVe.get(i).isTaoMoi == true && HinhVe.isDoiMauNen == true) {
                                hinhVe = new HinhChuNhat();
                                hinhVe = listHinhVe.get(i);
                                listHinhVe.add(hinhVe);
                                System.out.println(listHinhVe.size());
                                pane.getChildren().add(hinhVe.hinh);
                                listHinhVe.get(i).isTaoMoi = false;
                                break;
                            }
                        }
                    }
                });
                break;
            case 0:
                /*hinh Pencil*/
                pane.setOnMouseDragged(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        if (dieuKhien.mauNenHinh.isSelected() != true && dieuKhien.mauNetVe.isSelected() != true) {
                            capNhatToaDoChuotHienThi(e.getX(), e.getY());
                            if (shape.viTriChuot == -1) {
                                shape.viTriChuot = 1;
                                if (dieuKhien.tay.isSelected()) {
                                    shape = new Pencil(6, Color.WHITE);

                                } else {
                                    shape = new Pencil(giaTri.netVe, giaTri.mauNetVe);
                                }
                                pane.getChildren().add(((Pencil) shape).hinh);
                            }
                            shape.suKienKeoChuot(e);
                        }
                    }
                }
                );
                pane.setOnMouseClicked(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        listHinhVe.add(shape);
                        batTatUndoRedo();
                        System.out.println("\nAdd Shape vào list: " + listHinhVe.size());
                        shape.suKienThaChuot(e);
                    }

                }
                );
                break;
            case 1:
                /*hinh duong thang*/
                pane.setOnMousePressed(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        if (dieuKhien.mauNenHinh.isSelected() != true && dieuKhien.mauNetVe.isSelected() != true) {
                            shape.suKienClickChuot(e);
                            if (shape.viTriChuot == -1) {
                                lanDau = 1;
                                tatCacNutDieuKhien();
                                dieuKhien.nutXoa.setDisable(true);
                            }
                        }
                    }
                }
                );
                pane.setOnMouseDragged(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        capNhatToaDoChuotHienThi(e.getX(), e.getY());
                        if (lanDau == 1) {
                            if (shape.viTriChuot == -1) {
                                shape.viTriChuot = 1;
                                tam = new Diem(shape.diemBatDau);
                                pane.getChildren().remove(((DuongThang) shape).polygon);
                                shape = new DuongThang(giaTri.netVe, giaTri.mauNetVe);
                                shape.diemBatDau = new Diem(tam);
                                listHinhVe.add(shape);
                                batTatUndoRedo();
                                System.out.println("\nAdd Shape vào list: " + listHinhVe.size());
                                pane.getChildren().add(((DuongThang) shape).hinh);
                                pane.getChildren().addAll(shape.dsChamTronDieuKhien);

                            }
                            shape.suKienKeoChuotVeHinh(e);
                            dieuKhien.nutXoa.setDisable(false);
                        } else {
                            shape.suKienKeoChuot(e);
                        }
                    }
                }
                );
                pane.setOnMouseClicked(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        lanDau = 0;
                        shape.suKienThaChuot(e);
                    }
                }
                );
                break;
            case 2:
                /*hinh chu nhat*/
                pane.setOnMousePressed(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (dieuKhien.mauNenHinh.isSelected() != true && dieuKhien.mauNetVe.isSelected() != true) {
                            shape.suKienClickChuot(e);
                            if (shape.viTriChuot == -1) {
                                lanDau = 1;
                                tatCacNutDieuKhien();
                                dieuKhien.nutXoa.setDisable(true);
                            }
                        }
                    }
                }
                );
                pane.setOnMouseDragged(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        capNhatToaDoChuotHienThi(e.getX(), e.getY());
                        if (lanDau == 1) {
                            if (shape.viTriChuot == -1) {
                                shape.viTriChuot = 1;
                                tam = new Diem(shape.diemBatDau);
                                shape = new HinhChuNhat(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                shape.diemBatDau = new Diem(tam);
                                listHinhVe.add(shape);
                                batTatUndoRedo();
                                System.out.println("\nAdd Shape vào list: " + listHinhVe.size());
                                pane.getChildren().add(shape.hinh);
                                pane.getChildren().add(shape.khungBao);
                                pane.getChildren().addAll(shape.dsChamTronDieuKhien);

                            }
                            shape.suKienKeoChuotVeHinh(e);
                            dieuKhien.nutXoa.setDisable(false);
                        } else {
                            shape.suKienKeoChuot(e);
                        }
                    }
                }
                );
                pane.setOnMouseClicked(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        lanDau = 0;
                        shape.suKienThaChuot(e);
                    }
                }
                );
                break;
            case 3:
                /*hinh ellipse*/
                pane.setOnMousePressed(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        if (dieuKhien.mauNenHinh.isSelected() != true && dieuKhien.mauNetVe.isSelected() != true) {
                            shape.suKienClickChuot(e);
                            if (shape.viTriChuot == -1) {
                                lanDau = 1;
                                tatCacNutDieuKhien();
                                dieuKhien.nutXoa.setDisable(true);
                            }
                        }
                    }
                }
                );
                pane.setOnMouseDragged(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        capNhatToaDoChuotHienThi(e.getX(), e.getY());
                        if (lanDau == 1) {
                            if (shape.viTriChuot == -1) {
                                shape.viTriChuot = 1;
                                tam = new Diem(shape.diemBatDau);
                                shape = new Ellip(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                shape.diemBatDau = new Diem(tam);
                                listHinhVe.add(shape);
                                batTatUndoRedo();
                                System.out.println("\nAdd Shape vào list: " + listHinhVe.size());
                                pane.getChildren().add(shape.hinh);
                                pane.getChildren().add(shape.khungBao);
                                pane.getChildren().addAll(shape.dsChamTronDieuKhien);

                            }
                            shape.suKienKeoChuotVeHinh(e);
                            dieuKhien.nutXoa.setDisable(false);
                        } else {
                            shape.suKienKeoChuot(e);
                        }
                    }
                }
                );
                pane.setOnMouseClicked(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        lanDau = 0;
                        shape.suKienThaChuot(e);
                    }
                }
                );
                break;
            case 19:
                /*van ban*/
                pane.setOnMousePressed(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        if (dieuKhien.mauNenHinh.isSelected() != true && dieuKhien.mauNetVe.isSelected() != true) {
                            shape.suKienClickChuot(e);
                            if (shape.viTriChuot == -1) {
                                lanDau = 1;
                                tatCacNutDieuKhien();
                                ((VanBan) shape).textArea.setDisable(true);
                                dieuKhien.nutXoa.setDisable(true);
                            }
                        }
                    }
                }
                );
                pane.setOnMouseDragged(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        capNhatToaDoChuotHienThi(e.getX(), e.getY());
                        if (lanDau == 1) {
                            if (shape.viTriChuot == -1) {
                                shape.viTriChuot = 1;
                                // Vi toa do click chuot nam o shape2 cu
                                tam = new Diem(shape.diemBatDau);
                                shape = new VanBan(Font.font(dieuKhien.fontChu.getValue(),
                                        dieuKhien.inDam.isSelected() ? FontWeight.BOLD : null,
                                        dieuKhien.inDam.isSelected() ? FontPosture.ITALIC : null,
                                        dieuKhien.coChu.getValue()));
                                shape.diemBatDau = new Diem(tam);
                                listHinhVe.add(((VanBan) shape));
                                batTatUndoRedo();
                                System.out.println("\nAdd Shape vào list: " + listHinhVe.size());
                                pane.getChildren().add(shape.khungBao);
                                pane.getChildren().addAll(shape.dsChamTronDieuKhien);
                                pane.getChildren().add(((VanBan) shape).textArea);

                            }
                            shape.suKienKeoChuotVeHinh(e);
                            dieuKhien.nutXoa.setDisable(false);
                            dieuKhien.BatVanBan();
                        } else {
                            shape.suKienKeoChuot(e);
                        }
                    }
                }
                );
                pane.setOnMouseClicked(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        lanDau = 0;
                        shape.suKienThaChuot(e);
                    }
                }
                );
                break;
            case 20:
                /*hinh chon vung*/
                pane.setOnMousePressed(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (dieuKhien.mauNenHinh.isSelected() != true && dieuKhien.mauNetVe.isSelected() != true) {
                            shape.suKienClickChuot(e);
                            if (shape.viTriChuot == -1) {
                                lanDau = 1;
                                dieuKhien.nutXoa.setDisable(true);
                            }
                        }
                    }
                }
                );
                pane.setOnMouseDragged(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        capNhatToaDoChuotHienThi(e.getX(), e.getY());
                        if (lanDau == 1) {
                            if (shape.viTriChuot == -1) {
                                shape.viTriChuot = 1;
                                tam = new Diem(shape.diemBatDau);
                                pane.getChildren().remove(shape.khungBao);
                                pane.getChildren().removeAll(shape.dsChamTronDieuKhien);
                                shape = new ChonVung();
                                shape.diemBatDau = new Diem(tam);
                                batTatUndoRedo();
                                System.out.println("\nAdd Shape vào list: " + listHinhVe.size());
                                pane.getChildren().add(shape.khungBao);
                                pane.getChildren().addAll(shape.dsChamTronDieuKhien);

                            }
                            shape.suKienKeoChuotVeHinh(e);
                            dieuKhien.nutXoa.setDisable(false);
                        } else {
                            shape.suKienKeoChuot(e);
                        }
                    }
                }
                );
                pane.setOnMouseClicked(
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e
                    ) {
                        lanDau = 0;
                        shape.suKienThaChuot(e);
                    }
                }
                );
                break;
            default: {
                /*cac hinh con lai*/
                pane.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (dieuKhien.mauNenHinh.isSelected() != true && dieuKhien.mauNetVe.isSelected() != true) {
                            shape.suKienClickChuot(e);
                            if (shape.viTriChuot == -1) {
                                lanDau = 1;
                                tatCacNutDieuKhien();
                                dieuKhien.nutXoa.setDisable(true);
                            }
                        }
                    }
                });
                pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        capNhatToaDoChuotHienThi(e.getX(), e.getY());
                        if (lanDau == 1) {
                            if (shape.viTriChuot == -1) {
                                shape.viTriChuot = 1;
                                tam = new Diem(shape.diemBatDau);
                                switch (maHinh) {
                                    case 4:
                                        shape = new HinhThoi(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 5:
                                        shape = new BinhHanh(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 6:
                                        shape = new ChuThap(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 7:
                                        shape = new HinhThangCan(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 8:
                                        shape = new NguGiac(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 9:
                                        shape = new LucGiac(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 10:
                                        shape = new MuiTenLen(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 11:
                                        shape = new MuiTenXuong(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 12:
                                        shape = new MuiTenTrai(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 13:
                                        shape = new MuiTenPhai(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 14:
                                        shape = new TamGiac(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 15:
                                        shape = new TamGiacVuong(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 16:
                                        shape = new Star4(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 17:
                                        shape = new Star5(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;
                                    case 18:
                                        shape = new Star6(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                                        break;

                                }
                                shape.diemBatDau = new Diem(tam);
                                listHinhVe.add(shape);
                                batTatUndoRedo();
                                System.out.println("\nAdd Shape vào list: " + listHinhVe.size());
                                pane.getChildren().add(shape.hinh);
                                pane.getChildren().add(shape.khungBao);
                                pane.getChildren().addAll(shape.dsChamTronDieuKhien);

                            }
                            shape.suKienKeoChuotVeHinh(e);
                            dieuKhien.nutXoa.setDisable(false);
                        } else {
                            shape.suKienKeoChuot(e);
                        }
                    }
                });
                pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        lanDau = 0;
                        shape.suKienThaChuot(e);
                    }
                });
                break;
            }

        }
    }

    /*Chon shape de ve*/
    public void chonShape() {
        dieuKhien.pencil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(new ImageCursor(new Image("/images/cursorPencil.png")));
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 0;
                shape = new Pencil(giaTri.netVe, giaTri.mauNetVe);
                ve();
            }
        });
        /*Chon Hinh chu nhat*/
        dieuKhien.hinhChuNhat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 2;
                shape = new HinhChuNhat(giaTri.netVe, giaTri.mauNetVe, Color.rgb(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.duongThang.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 1;
                shape = new DuongThang(giaTri.netVe, giaTri.mauNetVe);
                ve();
            }
        });
        dieuKhien.hinhEllipse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 3;
                shape = new Ellip(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhThoi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 4;
                shape = new HinhThoi(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhBinhHanh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 5;
                shape = new BinhHanh(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhChuThap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 6;
                shape = new ChuThap(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhThangCan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 7;
                shape = new HinhThangCan(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhNguGiac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 8;
                shape = new NguGiac(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhLucGiac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 9;
                shape = new LucGiac(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhMuiTenLen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 10;
                shape = new MuiTenLen(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhMuiTenXuong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 11;
                shape = new MuiTenXuong(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhMuiTenTrai.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 12;
                shape = new MuiTenTrai(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhMuiTenPhai.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 13;
                shape = new MuiTenPhai(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhTamGiac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 14;
                shape = new TamGiac(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhTamGiacVuong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 15;
                shape = new TamGiacVuong(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhSao4Canh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 16;
                shape = new Star4(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhSao5Canh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 17;
                shape = new Star5(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        dieuKhien.hinhSao6Canh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 18;
                shape = new Star6(giaTri.netVe, giaTri.mauNetVe, Color.color(0, 0, 0, 0));
                ve();
            }
        });
        /*chon Van ban*/
        dieuKhien.vanBan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.TEXT);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 19;
                shape = new VanBan(Font.font(dieuKhien.fontChu.getValue(),
                        dieuKhien.inDam.isSelected() ? FontWeight.BOLD : null,
                        dieuKhien.inDam.isSelected() ? FontPosture.ITALIC : null,
                        dieuKhien.coChu.getValue()));
                ve();
            }
        });
        /*nut selected*/
        dieuKhien.nutChonSelect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                pane.setCursor(Cursor.CROSSHAIR);
                /* neu truoc do khong phai la hinh ve*/
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                maHinh = 20;
                shape = new ChonVung();
                ve();
            }
        });
    }

    /*Thanh cong cu ben trai*/
    public void congCuDieuKhienBenTrai() {
        dieuKhien.netVe1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                giaTri.netVe = 1;
                capNhatDoDayNetVe();
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
            }
        });
        dieuKhien.netVe2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                giaTri.netVe = 2;
                capNhatDoDayNetVe();
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
            }
        });
        dieuKhien.netVe3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                giaTri.netVe = 5;
                capNhatDoDayNetVe();
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
            }
        });
        dieuKhien.netVe4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
                giaTri.netVe = 6;

                capNhatDoDayNetVe();
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
            }
        });
        /*mau nen hinh*/
        dieuKhien.mauNenHinh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(new ImageCursor(new Image("/images/cursorPaint.png"), 5, 5));
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                if (maHinh != -1) {

                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.mauNenHinh.setSelected(true);
                maHinh = -1;
                System.out.println(maHinh);
                for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                    listHinhVe.get(i).mauNen = mauDangChon;
                }
                HinhVe.isDoiMauNen = true;
                HinhVe.isDoiMauNetVe = false;
                dieuKhien.congCuChinhAnh.setVisible(false);
            }
        });
        /*mau net ve*/
        dieuKhien.mauNetVe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(new ImageCursor(new Image("/images/cursorNetVe.png")));
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                if (maHinh != -1) {
                    tatCacNutDieuKhien();
                }
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.mauNetVe.setSelected(true);
                maHinh = -1;
                System.out.println(maHinh);
                for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                    listHinhVe.get(i).mauNetVe = mauDangChon;
                }
                HinhVe.isDoiMauNetVe = true;
                HinhVe.isDoiMauNen = false;
            }
        });
        /*chon cuc Tay*/
        dieuKhien.tay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(new ImageCursor(new Image("/images/cursoEeraser.png")));
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;

                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                tatCacNutDieuKhien();
                maHinh = 0;
                shape = new Pencil(6, Color.WHITE);
                ve();
            }
        });
        /*Can chinh kich thuoc chieu rong image*/
        dieuKhien.chinhChieuRongImage.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                pane.setCursor(Cursor.DEFAULT);
                ((HinhAnh) shape).imageView.setFitWidth((double) newValue * ((HinhAnh) shape).image.getWidth());
                if (((HinhAnh) shape).imageView.getFitWidth() >= pane.getMinWidth()) {
                    dieuKhien.chinhChieuRong.setValue(((HinhAnh) shape).imageView.getFitWidth() + 2);
                }
            }
        });
        /*Can chinh kich thuoc chieu cao image*/
        dieuKhien.chinhChieuCaoImage.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                ((HinhAnh) shape).imageView.setFitHeight((double) newValue * ((HinhAnh) shape).image.getHeight());
                pane.setCursor(Cursor.DEFAULT);
                if (((HinhAnh) shape).imageView.getFitHeight() >= pane.getMinHeight()) {
                    dieuKhien.chinhChieuCao.setValue(((HinhAnh) shape).imageView.getFitHeight() + 2);
                }

            }
        });
        /*Can chinh kich thuoc hai chieu image*/
        dieuKhien.chinhHaiChieuImage.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                pane.setCursor(Cursor.DEFAULT);
                ((HinhAnh) shape).imageView.setFitWidth((double) newValue * ((HinhAnh) shape).image.getWidth());
                ((HinhAnh) shape).imageView.setFitHeight((double) newValue * ((HinhAnh) shape).image.getHeight());
                if (((HinhAnh) shape).imageView.getFitWidth() >= pane.getMinWidth()) {
                    dieuKhien.chinhChieuRong.setValue(((HinhAnh) shape).imageView.getFitWidth() + 2);
                }
                if (((HinhAnh) shape).imageView.getFitHeight() >= pane.getMinHeight()) {
                    dieuKhien.chinhChieuCao.setValue(((HinhAnh) shape).imageView.getFitHeight() + 2);
                }
            }
        });
    }

    /*Thanh trang thai*/
    public void thanhTrangThai(Stage stage) {
        /*new file*/
        dieuKhien.newFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                showHopThoaiCoSaveKhong(stage, 1);
                dieuKhien.congCuChinhAnh.setVisible(false);
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
            }
        });
        /*open file*/
        dieuKhien.openFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                maHinh = 21;
                shape = new HinhAnh();
                showHopThoaiCoSaveKhong(stage, 0);
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
            }
        });
        /*save file*/
        dieuKhien.saveFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                try {
                    saveFile(stage, pane);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        /*Exit*/
        dieuKhien.exitFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                stage.close();
                dieuKhien.congCuChinhAnh.setVisible(false);
            }
        });
        /*fullScreen*/
        dieuKhien.fullGreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                stage.setFullScreen(true);
            }
        });
        /*exitfullscreen*/
        dieuKhien.ExitFullGreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                stage.setFullScreen(false);
            }
        });
        /*show*/
        dieuKhien.show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
            }
        });
        /*hide*/
        dieuKhien.hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
            }
        });
        /*about*/
        dieuKhien.about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                showMe();
            }
        });
    }

    /*thanh cong cu phia tren*/
    public void thanhCongCuPhiaTren(Stage stage) {
        /*new file*/
        dieuKhien.nutNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                showHopThoaiCoSaveKhong(stage, 1);
            }
        });
        /*save file*/
        dieuKhien.nutSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                try {
                    saveFile(stage, pane);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        /*nut delete*/
        dieuKhien.nutXoa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                pane.setCursor(Cursor.DEFAULT);
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                if (maHinh == 20 && dieuKhien.nutChonSelect.isSelected() != false) {
                    shape.khungBao.setFill(Color.WHITE);
                    listHinhVe.add(shape);
                    shape.khungBao.setStrokeWidth(0);
                    pane.getChildren().removeAll(shape.dsChamTronDieuKhien);
                    shape = new ChonVung();
                    listHinhVeChuaTam.clear();
                } else if (listHinhVe.size() > 0) {
                    listHinhVeChuaTam.add(0, listHinhVe.get(listHinhVe.size() - 1));
                    switch (listHinhVe.get(listHinhVe.size() - 1).maHinh) {
                        case -1:
                            break;
                        case 0:
                            pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).hinh);
                            break;
                        case 1:
                            pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).hinh);
                            pane.getChildren().remove((((DuongThang) listHinhVe.get(listHinhVe.size() - 1)).polygon));
                            pane.getChildren().removeAll(listHinhVe.get(listHinhVe.size() - 1).dsChamTronDieuKhien);
                            break;
                        case 19:
                            pane.getChildren().remove((((VanBan) listHinhVe.get(listHinhVe.size() - 1)).textArea));
                            pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).khungBao);
                            pane.getChildren().removeAll(listHinhVe.get(listHinhVe.size() - 1).dsChamTronDieuKhien);
                            break;
                        default:
                            pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).hinh);
                            pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).khungBao);
                            pane.getChildren().removeAll(listHinhVe.get(listHinhVe.size() - 1).dsChamTronDieuKhien);
                            break;
                    }
                    listHinhVe.remove(listHinhVe.size() - 1);
                }
                batTatUndoRedo();
                dieuKhien.nutXoa.setDisable(true);
                dieuKhien.congCuChinhAnh.setVisible(false);
                System.out.println("Con lai: " + listHinhVe.size());
            }
        }
        );

        /*khi undo*/
        dieuKhien.nutLui.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e
            ) {
                pane.setCursor(Cursor.DEFAULT);
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                listHinhVeChuaTam.add(0, listHinhVe.get(listHinhVe.size() - 1));
                switch (listHinhVe.get(listHinhVe.size() - 1).maHinh) {
                    case -1:
                        break;
                    case 0:
                        pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).hinh);
                        break;
                    case 1:
                        pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).hinh);
                        pane.getChildren().remove((((DuongThang) listHinhVe.get(listHinhVe.size() - 1)).polygon));
                        pane.getChildren().removeAll(listHinhVe.get(listHinhVe.size() - 1).dsChamTronDieuKhien);
                        break;
                    case 19:
                        pane.getChildren().remove((((VanBan) listHinhVe.get(listHinhVe.size() - 1)).textArea));
                        pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).khungBao);
                        pane.getChildren().removeAll(listHinhVe.get(listHinhVe.size() - 1).dsChamTronDieuKhien);
                        break;
                    case 20:
                        pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).khungBao);
                        pane.getChildren().removeAll(listHinhVe.get(listHinhVe.size() - 1).dsChamTronDieuKhien);
                        break;
                    case 21:
                        System.out.println("mahinh " + maHinh);
                        pane.getChildren().remove(((HinhAnh) listHinhVe.get(listHinhVe.size() - 1)).imageView);
                        break;
                    default:
                        pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).hinh);
                        pane.getChildren().remove(listHinhVe.get(listHinhVe.size() - 1).khungBao);
                        pane.getChildren().removeAll(listHinhVe.get(listHinhVe.size() - 1).dsChamTronDieuKhien);
                        break;
                }
                tatCacNutDieuKhien();
                listHinhVe.remove(listHinhVe.size() - 1);
                batTatUndoRedo();
                System.out.println("Con lai: " + listHinhVe.size());
                dieuKhien.congCuChinhAnh.setVisible(false);
            }
        }
        );
        /*khi redo*/
        dieuKhien.nutTien.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e
            ) {
                pane.setCursor(Cursor.DEFAULT);
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                listHinhVe.add(listHinhVeChuaTam.get(0));
                switch (listHinhVeChuaTam.get(0).maHinh) {
                    case -1:
                        break;
                    case 0:
                        pane.getChildren().add(listHinhVeChuaTam.get(0).hinh);
                        break;
                    case 1:
                        pane.getChildren().add(listHinhVeChuaTam.get(0).hinh);
                        break;
                    case 19:
                        pane.getChildren().add((((VanBan) listHinhVeChuaTam.get(0)).textArea));
                        break;
                    case 20:
                        pane.getChildren().add(listHinhVeChuaTam.get(0).khungBao);
                        break;
                    case 21:
                        pane.getChildren().add(((HinhAnh) listHinhVe.get(listHinhVe.size() - 1)).imageView);
                        break;
                    default:
                        pane.getChildren().add(listHinhVeChuaTam.get(0).hinh);
                        break;
                }
                tatCacNutDieuKhien();
                listHinhVeChuaTam.remove(0);
                batTatUndoRedo();
                System.out.println("Con lai: " + listHinhVe.size());
                dieuKhien.congCuChinhAnh.setVisible(false);
            }
        }
        );
    }

    /*thanh cong cu phia duoi*/
    public void thanhCongCuPhiaDuoi() {
        /*in dam*/
        dieuKhien.inDam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dieuKhien.congCuChinhAnh.setVisible(false);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setFont(Font.font(dieuKhien.fontChu.getValue(), dieuKhien.inDam.isSelected() ? FontWeight.BOLD : null,
                            dieuKhien.inNghieng.isSelected() ? FontPosture.ITALIC : null, dieuKhien.coChu.getValue()));
                }
            }
        });
        /*in nghieng*/
        dieuKhien.inNghieng.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dieuKhien.congCuChinhAnh.setVisible(false);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setFont(Font.font(dieuKhien.fontChu.getValue(), dieuKhien.inDam.isSelected() ? FontWeight.BOLD : null,
                            dieuKhien.inNghieng.isSelected() ? FontPosture.ITALIC : null, dieuKhien.coChu.getValue()));
                }
            }
        });
        /*gach chan*/
        dieuKhien.gachChan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dieuKhien.congCuChinhAnh.setVisible(false);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                if (maHinh == 19) {
                }
            }
        });
        /*font chu*/
        dieuKhien.fontChu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dieuKhien.congCuChinhAnh.setVisible(false);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setFont(Font.font(dieuKhien.fontChu.getValue(), dieuKhien.inDam.isSelected() ? FontWeight.BOLD : null,
                            dieuKhien.inNghieng.isSelected() ? FontPosture.ITALIC : null, dieuKhien.coChu.getValue()));
                }
            }
        });
        /*co chu*/
        dieuKhien.coChu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dieuKhien.congCuChinhAnh.setVisible(false);
                listHinhVeChuaTam.clear();
                batTatUndoRedo();
                HinhVe.isDoiMauNen = false;
                HinhVe.isDoiMauNetVe = false;
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setFont(Font.font(dieuKhien.fontChu.getValue(), dieuKhien.inDam.isSelected() ? FontWeight.BOLD : null,
                            dieuKhien.inDam.isSelected() ? FontPosture.ITALIC : null, dieuKhien.coChu.getValue()));
                }
            }
        });

        dieuKhien.colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = dieuKhien.colorPicker.getValue();
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = dieuKhien.colorPicker.getValue();
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
        dieuKhien.mauDen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = Color.BLACK;
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = Color.BLACK;
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
        dieuKhien.mauTrang.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = Color.WHITE;
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = Color.WHITE;
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
        dieuKhien.mauDo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = Color.RED;
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = Color.RED;
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
        dieuKhien.mauCam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = Color.ORANGE;
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = Color.ORANGE;
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
        dieuKhien.mauVang.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = Color.YELLOW;
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = Color.YELLOW;
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
        dieuKhien.mauXanhLa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = Color.GREEN;
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = Color.GREEN;
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
        dieuKhien.mauXanhDuong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = Color.BLUE;
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = Color.BLUE;
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
        dieuKhien.mauHong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = Color.PINK;
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = Color.PINK;
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
        dieuKhien.mauTim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (maHinh == 19) {
                    ((VanBan) shape).textArea.setDisable(true);
                    dieuKhien.tatVanBan();
                }
                dieuKhien.congCuChinhAnh.setVisible(false);
                mauDangChon = Color.PURPLE;
                dieuKhien.mauChon.setFill(mauDangChon);
                giaTri.mauNetVe = Color.PURPLE;
                if (dieuKhien.mauNenHinh.isSelected() == false) {
                    HinhVe.isDoiMauNen = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNen = mauDangChon;
                    }
                }
                if (dieuKhien.mauNetVe.isSelected() == false) {
                    HinhVe.isDoiMauNetVe = false;
                } else {
                    for (int i = listHinhVe.size() - 1; i >= 0; i--) {
                        listHinhVe.get(i).mauNetVe = mauDangChon;
                    }
                }
                capNhatMauNetVe();
            }
        });
    }

    /*thanh footer*/
    public void thanhFooter() {
        /*toa do chuot*/
        pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                dieuKhien.toaDoChuot.setText(String.format("%.1f, %.1f", e.getX(), e.getY()));
            }
        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                capNhatToaDoChuotHienThi(e.getX(), e.getY());
            }
        });

        pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                dieuKhien.toaDoChuot.setText("");
            }
        });
        /*Can chinh kich thuoc chieu rong bang ve*/
        dieuKhien.chinhChieuRong.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                pane.setMaxWidth((double) newValue);
                pane.setMinWidth((double) newValue);
                dieuKhien.kichThuocKhungVe.setText(String.format("%s%.1f%s%.1f", "W: ",
                        pane.getMaxWidth(), "  H: ", pane.getMaxHeight()));
            }
        });
        /*Can chinh kich thuoc chieu cao bang ve*/
        dieuKhien.chinhChieuCao.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                pane.setMaxHeight((double) newValue);
                pane.setMinHeight((double) newValue);
                dieuKhien.kichThuocKhungVe.setText(String.format("%s%.1f%s%.1f", "W: ",
                        pane.getMaxWidth(), "  H: ", pane.getMaxHeight()));
            }
        });
    }

    /*Tat nut dieu khien*/
    public void tatCacNutDieuKhien() {
        switch (maHinh) {
            case 1:
                pane.getChildren().removeAll(shape.dsChamTronDieuKhien);
                break;
            case 2:
                pane.getChildren().remove(shape.khungBao);
                pane.getChildren().removeAll(shape.dsChamTronDieuKhien);
                break;
            case 3:
                pane.getChildren().remove(shape.khungBao);
                pane.getChildren().removeAll(shape.dsChamTronDieuKhien);
                break;
            case 19:
                pane.getChildren().remove(shape.khungBao);
                pane.getChildren().removeAll(shape.dsChamTronDieuKhien);
                break;
            case 20:
                pane.getChildren().remove(shape.khungBao);
                pane.getChildren().removeAll(shape.dsChamTronDieuKhien);
                break;
            default:
                if (maHinh != 0 && maHinh != -1 && maHinh != 21) {
                    pane.getChildren().remove(shape.khungBao);
                    pane.getChildren().removeAll(shape.dsChamTronDieuKhien);
                }
                break;
        }
    }

    /*Cap nhat mau net ve*/
    public void capNhatMauNetVe() {
        switch (maHinh) {
            case 1:
                shape.mauNetVe = giaTri.mauNetVe;
                shape.capNhat();
                break;
            case 2:
                shape.mauNetVe = giaTri.mauNetVe;
                shape.capNhat();
                break;
            case 3:
                shape.mauNetVe = giaTri.mauNetVe;
                shape.capNhat();
                break;
            default:
                if (maHinh != 0 && maHinh != 19 && maHinh != -1) {
                    shape.mauNetVe = giaTri.mauNetVe;
                    shape.capNhat();
                    break;
                }
        }
    }

    /*Cap nhat do day net ve*/
    public void capNhatDoDayNetVe() {
        switch (maHinh) {
            case 1:
                shape.netVe = giaTri.netVe;
                shape.capNhat();
                break;
            case 2:
                shape.netVe = giaTri.netVe;
                shape.capNhat();
                break;
            case 3:
                shape.netVe = giaTri.netVe;
                shape.capNhat();
                break;
            default:
                if (maHinh != 0 && maHinh != 19 && maHinh != -1) {
                    shape.netVe = giaTri.netVe;
                    shape.capNhat();
                    break;
                }
        }
    }

    /*Cap nhat mau nen*/
    public void capNhatMauNen() {
        if (maHinh != 0 && maHinh != 1 && maHinh != 19) {
            shape.mauNen = giaTri.mauNen;
            shape.capNhat();
        }
    }

    /*cap nhap toa do chuot*/
    public void capNhatToaDoChuotHienThi(double x, double y) {
        if (x >= 0 && y >= 0 && x <= pane.getWidth() && y <= pane.getHeight()) {
            dieuKhien.toaDoChuot.setText(String.format("%.1f, %.1f", x, y));
        }
    }

    /*bat tat nut undo, redo*/
    public void batTatUndoRedo() {
        if (listHinhVe.isEmpty()) {
            dieuKhien.nutLui.setDisable(true);
        } else {
            dieuKhien.nutLui.setDisable(false);
        }
        if (listHinhVeChuaTam.isEmpty()) {
            dieuKhien.nutTien.setDisable(true);
        } else {
            dieuKhien.nutTien.setDisable(false);
        }

    }

    /*tao pane moi khi new file*/
    public void newPane() {
        dieuKhien.chinhChieuCao.setValue(800);
        dieuKhien.chinhChieuRong.setValue(800);
        pane = new PaintingFrame();
        pane.setStyle("-fx-background-color: White;-fx-border-color: #0101DF;");
        BorderPane.setAlignment(pane, Pos.TOP_LEFT);
        scrollPane.setContent(pane);
        pane.setMaxSize(dieuKhien.chinhChieuRong.getValue(), dieuKhien.chinhChieuCao.getValue());
        pane.setMinSize(dieuKhien.chinhChieuRong.getValue(), dieuKhien.chinhChieuCao.getValue());
        dieuKhien.kichThuocKhungVe.setText(String.format("%s%.1f%s%.1f", "W: ",
                pane.getMaxWidth(), "  H: ", pane.getMaxHeight()));
    }


    /*hien hop thoai thong bao co save hay khong*/
    public void showHopThoaiCoSaveKhong(Stage stage, int i) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("PAINT PHAKE");
        alert.setHeaderText("Do you want to save?");
        ButtonType save = new ButtonType("Save");
        ButtonType khongSave = new ButtonType("Don't save");

        // Loại bỏ các ButtonType mặc định
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(save, khongSave, ButtonType.CANCEL);

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {
        } else if (option.get() == save) {
            System.out.println("SAVE DE SAU)");
        } else if (option.get() == khongSave) {
            if (i == 1) {// new file
                newPane();
                listHinhVe.clear();
            } else {// open file
                newPane();
                listHinhVe.clear();
                openFile(stage, shape);
            }
        }
    }

    /*hien thi thong tin ung dung*/
    public void showMe() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About Paint");
        alert.setGraphic(new ImageView(new Image("/images/paint.png")));
        alert.setHeaderText("Paint\nVersion 1.0\nProgrammer: Bui Khanh Huy");
        GridPane thongTin = new GridPane();
        thongTin.setVgap(5);
        thongTin.add(new ImageView(new Image("/images/me.jpg")), 0, 0, 2, 1);

        thongTin.add(new Text("Any contributions about problems, software\nreviews here:"), 0, 1, 2, 1);

        thongTin.add(new ImageView(new Image("/images/gmail.png")), 0, 2);
        thongTin.add(new ImageView(new Image("/images/facebook.png")), 0, 3);
        Hyperlink linkGamail = new Hyperlink("Gmail");
        linkGamail.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument("https://mail.google.com/mail/u/1/#inbox");
            }
        });
        thongTin.add(linkGamail, 1, 2);
        Hyperlink linkfacebook = new Hyperlink("Facebook");
        linkfacebook.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument("https://www.facebook.com/khanhhuy2702/");
            }
        });
        thongTin.add(linkfacebook, 1, 3);

        alert.getDialogPane().setContent(thongTin);
        alert.showAndWait();
    }

    /*open file*/
    public void openFile(Stage stage, HinhVe shape) {
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"));
            File file = fc.showOpenDialog(stage);
            if (file != null) {
                String s = file.toString().trim().replace('\\', '/');

                File f = new File(s);
                String localUrl = f.toURI().toURL().toString();

                ((HinhAnh) shape).image = new Image(localUrl);

                ((HinhAnh) shape).imageView = new ImageView(((HinhAnh) shape).image);
                ((HinhAnh) shape).imageView.setFitWidth(((HinhAnh) shape).image.getWidth() * 0.5);
                ((HinhAnh) shape).imageView.setFitHeight(((HinhAnh) shape).image.getHeight() * 0.5);
                pane.getChildren().add(((HinhAnh) shape).imageView);
                listHinhVe.add(shape);
                batTatUndoRedo();
                System.out.println("List hinh ve size: " + listHinhVe.size());
                dieuKhien.chinhChieuCao.setValue(((HinhAnh) shape).imageView.getFitHeight() + 2);
                dieuKhien.chinhChieuRong.setValue(((HinhAnh) shape).imageView.getFitWidth() + 2);
                dieuKhien.congCuChinhAnh.setVisible(true);
            }
        } catch (MalformedURLException ex) {
        }
    }

    /*cac thao tac save file*/
    public void saveFile(Stage stage, PaintingFrame pane) throws FileNotFoundException, MalformedURLException, IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = fc.showSaveDialog(stage);
        if (file != null) {
            String s = file.toString().trim().replace('\\', '/');
            System.out.println(s);

            FileInputStream fin = new FileInputStream(new File("C:\\Users\\khuy2\\Desktop\\22.jpg"));
            FileOutputStream fout = new FileOutputStream(new File(s));

            int count;
            byte[] buffer = new byte[8192]; // or more if you like
            while ((count = fin.read(buffer)) != -1) {
                fout.write(buffer, 0, count);
            }

        }
    }
}
