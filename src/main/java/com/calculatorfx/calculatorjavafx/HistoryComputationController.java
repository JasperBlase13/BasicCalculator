package com.calculatorfx.calculatorjavafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HistoryComputationController implements Initializable {

    static ArrayList<CompletedExpression> computation = new ArrayList<>();


    @FXML
    private VBox pane1;

    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private Label lb3;
    @FXML
    private Label lb4;
    @FXML
    private Label lb5;
    @FXML
    private Label lb6;
    @FXML
    private Label lb7;
    @FXML
    private Label lb8;
    @FXML
    private Label lb9;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Label[] storeLabel = {lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9};
      for(int i = 0; i<computation.size(); i++)
      {

            String firstNum = computation.get(computation.size()-i -1).getFirstNumber();
            String secondNum = computation.get(computation.size()-i -1).getSecondNumber();
            String operator = computation.get(computation.size()-i -1).getOperator();
            String answer = computation.get(computation.size()-i -1).getAnswer();
            storeLabel[i].setText(firstNum + " " + operator + " " + secondNum + " = " + answer);


          //System.out.println(computation.get(i).getFirstNumber());

      }



    }

    public void BackButtonClicked(ActionEvent event)throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene1 = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Basic Calculator");
        stage.setScene(scene1);
        stage.show();
        stage.resizableProperty().setValue(false);

        Stage stage2 = (Stage) pane1.getScene().getWindow();
        stage2.close();

    }

    public void ClearButtonClicked(ActionEvent event)
    {
        Label[] storeLabel = {lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9};
        for(int i = 0; i<storeLabel.length; i++)
        {
            storeLabel[i].setText("");
        }
        int arrLength = computation.size();

        for(int i = 0; i<arrLength; i++)
        {
            computation.removeAll(computation);
        }
    }



}
