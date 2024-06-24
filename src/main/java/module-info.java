module org.example.articlesfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    opens org.example.articlesfx to javafx.fxml;
    opens Controller to java.compiler , javafx.fxml, javafx.graphics;
    opens Entity to javafx.fxml, java.compiler,javafx.base,javafx.graphics;
    opens Repository to javafx.fxml, javafx.base,javafx.graphics;
    exports Controller;
    exports Entity;
    exports Repository;
    exports org.example.articlesfx;
}