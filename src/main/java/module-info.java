module com.empresa.hito_2_3t_programacion_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires com.google.gson;
    requires java.net.http;
    requires java.logging;
    requires org.json;

    opens com.empresa.hito_2_3t_programacion_fx to javafx.fxml;
    exports com.empresa.hito_2_3t_programacion_fx;
    exports com.empresa.hito_2_3t_programacion_fx.Controllers;
    opens com.empresa.hito_2_3t_programacion_fx.Controllers to javafx.fxml;
    opens com.empresa.hito_2_3t_programacion_fx.DTO to javafx.base;
}