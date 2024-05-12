module com.example.colorsattentiontest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.colorsattentiontest to javafx.fxml;
    exports com.example.colorsattentiontest;
}