module org.example.masaybrasa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;

    opens org.example.masaybrasa to javafx.fxml;
    opens org.example.masaybrasa.controller to javafx.fxml;
    opens org.example.masaybrasa.model to javafx.base;

    exports org.example.masaybrasa;
    exports org.example.masaybrasa.controller;
    exports org.example.masaybrasa.model;
    exports org.example.masaybrasa.dao;
    exports org.example.masaybrasa.database;
}
