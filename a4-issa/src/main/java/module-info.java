module com.example.a4issa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.a4issa to javafx.fxml;
    exports com.example.a4issa;
}