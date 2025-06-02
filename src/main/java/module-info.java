module com.example.tap2025 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;



    opens com.example.tap2025 to javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    exports com.example.tap2025;
    requires mysql.connector.j;
    requires java.sql;
    opens com.example.tap2025.Modelos;
    opens com.example.tap2025.Componentes;
    requires de.mkammerer.argon2.nolibs;
    requires itextpdf;
    requires org.jfree.jfreechart;
}