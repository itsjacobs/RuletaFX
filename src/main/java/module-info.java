module org.example.ruletafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires java.desktop;

    opens org.example.ruletafx.ui to javafx.fxml;
    exports org.example.ruletafx.ui;
}