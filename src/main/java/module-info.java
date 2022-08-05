module com.fin.easyfin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.easyfin to javafx.fxml;
    exports com.easyfin;
    exports com.easyfin.controllers;
    opens com.easyfin.controllers to javafx.fxml;
    exports com.easyfin.constructs;
    opens com.easyfin.constructs to javafx.fxml;
}