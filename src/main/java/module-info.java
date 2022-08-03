module com.fin.easyfin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.fin.easyfin to javafx.fxml;
    exports com.fin.easyfin;
}