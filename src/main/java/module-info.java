module com.calculatorfx.calculatorjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.calculatorfx.calculatorjavafx to javafx.fxml;
    exports com.calculatorfx.calculatorjavafx;
}

