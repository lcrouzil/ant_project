module com.example.antwar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.antwar to javafx.fxml;
    exports com.example.antwar;
}