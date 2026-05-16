module ghosthunterinc {
    requires javafx.controls;
    requires javafx.fxml;


    opens ghosthunterinc to javafx.fxml;
    exports ghosthunterinc;
    exports ghosthunterinc.ui;
    opens ghosthunterinc.ui to javafx.fxml;
}