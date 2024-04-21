module org.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jsapi;

    opens org.example.dictionary to javafx.fxml;
    exports org.example.dictionary;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Application;
    opens Application to javafx.fxml;
}