module com.fin.easyfin {
    requires javafx.controls;
    requires javafx.fxml;
    requires YahooFinanceAPI;
    requires java.net.http;
    requires com.google.gson;
    requires java.desktop;
    requires lombok;


    opens com.easyfin to javafx.fxml;
    exports com.easyfin;
    exports com.easyfin.controllers;
    opens com.easyfin.controllers to javafx.fxml;
    exports com.easyfin.constructs;
    opens com.easyfin.constructs to javafx.fxml;
    exports com.easyfin.helpers;
    opens com.easyfin.helpers to javafx.fxml;
}