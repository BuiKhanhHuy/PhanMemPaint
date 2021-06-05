module com.mycompany.paintphake {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.paintphake to javafx.fxml;
    exports com.mycompany.paintphake;
    requires javafx.graphicsEmpty;
}
