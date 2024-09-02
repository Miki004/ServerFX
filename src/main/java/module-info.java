module com.example.serverfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.management;
    requires jdk.compiler;


    opens com.example.serverfx to javafx.fxml;
    exports com.example.serverfx;
    exports com.example.serverfx.Controllers;
    opens com.example.serverfx.Controllers to javafx.fxml;
    exports com.example.serverfx.Server;
    opens com.example.serverfx.Server to javafx.fxml;
}