package com.calculatorfx.calculatorjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @FXML
    private Text tx1;

    @FXML
    private Text tx2;

    @FXML
    private VBox pane1;




    private String inputString = "";
    private long firstNumber = 0;
    private long secondNumber = 0;

    private double percentageComputed = 0.0;

    private char operatorGet;

    private String ansText = "";

    private Boolean isPercentage = false;

    private Boolean isFinished = false;


    private Boolean division = false;

    private  double dFirstNumber;
    private  double dSecondNumber;


    //For intializing the text object and display it to zero every time it starts,
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tx2.setText("0");
        tx2.setOnKeyTyped(this::keyTypedNumber);

        tx2.requestFocus();
    }



    @FXML
    private void keyTypedNumber(KeyEvent event)
    {
        char typed = event.getCharacter().charAt(0);
        String value;
       if(Character.isDigit(typed))
       {
           if(isFinished) {
               ansText = "";
               inputString = "";
               firstNumber = 0;
               secondNumber = 0;
               isFinished = false;
               value = Character.toString((typed));
               inputString += value;
               tx2.setText(inputString);
           }

           else
           {
               value = Character.toString((typed));
               inputString += value;
               inputString = formatNumberWithCommas(inputString=inputString.replace(",", ""));
               tx2.setText(inputString);
               tx2.requestFocus();
           }
       }
       else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("NON NUMERICAL INPUT DETECTED");
           alert.show();
       }
    }



    @FXML
    private void btn1Clicked(ActionEvent event)
    {
        if(isFinished)
        {
            ansText = "";
            inputString = "";
            firstNumber = 0;
            secondNumber = 0;
            isFinished = false;
            String value = ((Button)event.getSource()).getText();
            inputString += value;
            tx2.setText(inputString);
            tx2.requestFocus();
        }

        else
        {
            String value = ((Button)event.getSource()).getText();
            inputString += value;
            inputString = formatNumberWithCommas(inputString=inputString.replace(",", ""));
            tx2.setText(inputString);
            tx2.requestFocus();
        }


    }

    public static String formatNumberWithCommas(String inputString) {
        // Convert the number to a string
        String numberStr = inputString;
        numberStr = numberStr.replace(",", "");

        // Create a StringBuilder to build the formatted string
        StringBuilder formattedNumber = new StringBuilder();

        // Iterate through the characters in reverse order
        for (int i = numberStr.length() - 1, count = 0; i >= 0; i--, count++) {
            // Add a comma for every three digits
            if (count > 0 && count % 3 == 0) {
                formattedNumber.insert(0, ',');
            }
            // Append the current digit
            formattedNumber.insert(0, numberStr.charAt(i));
        }

        // Return the formatted string
        return formattedNumber.toString();
    }




    @FXML
    private void ClearBtnClicked(ActionEvent event)
    {
        inputString = "";
        tx2.setText("0");
        tx1.setText("");
        ansText = "";
        firstNumber = 0;
        secondNumber = 0;
        System.out.println(inputString);
        tx2.requestFocus();
    }

    @FXML
    private void DeleteSingleDigitClicked(ActionEvent event)
    {
        if(inputString.isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Empty Field");
            alert.show();
        }
        else
        {
            inputString = inputString.substring(0, inputString.length()-1);
            tx2.setText(inputString);
            if(inputString.isEmpty())
                tx2.setText("0");
        }
        tx2.requestFocus();



    }




    @FXML
    private void OperatorClicked(ActionEvent event)
    {
        if(inputString.isBlank() || inputString.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Value");
            alert.setHeaderText("No value given, please input a number");
            alert.show();
        }
        else
        {

            if(firstNumber >0)
            {
                    System.out.println("Dito Napunta");
                    inputString = inputString.replace(",", "");
                    secondNumber = Long.parseLong(inputString);
                    String getOperator = ((Button)event.getSource()).getText();
                    operatorGet = getOperator.charAt(0);
                    ArithmethicOperation(operatorGet);
                    tx1.setText(ansText + " " + operatorGet);
                    tx2.setText("0");
                    String getText = ansText.replace(",", "");
                    firstNumber = Long.parseLong(getText);
                    tx2.requestFocus();
            }

             else {

                 try{
                     inputString = inputString.replace(",", "");
                     long getNum = Long.parseLong(inputString);
                     System.out.println("It is not double");
                     division = false;
                 }catch(Exception e)
                 {
                     inputString = inputString.replace(",", "");
                     double getNum = Double.parseDouble(inputString);
                     System.out.println("It is double");
                     division = true;
                 }
                    if(division)
                    {
                        isFinished = false;
                        String getOperator = ((Button) event.getSource()).getText();
                        operatorGet = getOperator.charAt(0);
                        tx1.setText(inputString + " " + operatorGet);
                        dFirstNumber = Double.parseDouble(inputString);
                        inputString = "";
                        tx2.setText("0");
                        tx2.requestFocus();
                    }
                    else {
                        isFinished = false;
                        division = false;
                        String getOperator = ((Button) event.getSource()).getText();
                        operatorGet = getOperator.charAt(0);
                        tx1.setText(inputString + " " + operatorGet);
                        inputString = inputString.replace(",", "");
                        firstNumber = Long.parseLong(inputString);
                        System.out.println("Value is: " + firstNumber);
                        System.out.println(firstNumber);
                        System.out.println(operatorGet);
                        inputString = "";
                        tx2.setText("0");
                        tx2.requestFocus();
                    }
            }
        }
    }

    @FXML
    private void equalButtonSelected(ActionEvent event)
    {
        System.out.println(operatorGet);
        if(inputString.isBlank() || inputString.isEmpty()) {
            secondNumber = 0;
            System.out.println("Empty");
            tx2.setText("0");
            tx2.requestFocus();
        }
        else if(division)
        {
            inputString = inputString.replace(",", "");
            dSecondNumber = Double.parseDouble(inputString);
        }
        else {
            inputString = inputString.replace(",", "");
            secondNumber = Long.parseLong(inputString);
        }

        if(isPercentage)
        {
            double ans = 0.0;
            dFirstNumber = (firstNumber>0) ? dFirstNumber = (double) firstNumber : dFirstNumber;

            switch(operatorGet)
            {
                case '+':
                    ans = (double) dFirstNumber + percentageComputed;
                    break;
                case '-':
                    ans = (double) dFirstNumber - percentageComputed;
                    break;
                case '*':
                    ans = (double) dFirstNumber * percentageComputed;
                    break;
                case '/':
                    ans = (double) dFirstNumber / percentageComputed;
                    break;
            }
            ansText = Double.toString(ans);
            tx2.setText(ansText);
            tx1.setText("");
            storeExpression();
            firstNumber = 0;
            secondNumber = 0;
            isPercentage = false;
            inputString = ansText;
            isFinished = true;

        }
        else
        {
                ArithmethicOperation(operatorGet);
                inputString = ansText;
                isFinished = true;
        }

    }

    public int getAnswer(long num1, long num2, String operator)
    {
        return switch (operator) {
            case "Addition" -> (int) (num1 + num2);
            case "Subtraction" -> (int) (num1 - num2);
            case "Multiplication" -> (int) (num1 * num2);
            default -> 0;
        };
    }

    public double getAnswerDivision(double num1, double num2, String operator)
    {
        return switch (operator) {
            case "Addition" -> num1 + num2;
            case "Subtraction" -> num1 - num2;
            case "Multiplication" -> num1 * num2;
            case "Division" -> num1 / num2;
            default -> 0;
        };

    }


    public void ArithmethicOperation(char operatorGetnow)
    {
        if(division)
        {
            double answer = 0.0;
            switch (operatorGetnow) {
                case '+':
                    answer = getAnswerDivision(dFirstNumber, dSecondNumber, "Addition");
                    break;
                case '-':
                    answer = getAnswerDivision(dFirstNumber, dSecondNumber, "Subtraction");
                    break;
                case '*':
                    answer = getAnswerDivision(dFirstNumber, dSecondNumber, "Multiplication");
                    break;
                case '/':
                    answer = getAnswerDivision(dFirstNumber, dSecondNumber, "Division");
                    break;
            }
            ansText = Double.toString(answer);
            division = true;
        }
        else {
            switch (operatorGetnow) {
                case '+':
                    ansText = Integer.toString(getAnswer(firstNumber, secondNumber, "Addition"));
                    break;
                case '-':
                    ansText = Integer.toString(getAnswer(firstNumber, secondNumber, "Subtraction"));
                    break;
                case '*':
                    ansText = Integer.toString(getAnswer(firstNumber, secondNumber, "Multiplication"));
                    break;
                case '/':
                    double answer = getAnswerDivision(firstNumber, secondNumber, "Division");
                    System.out.println(answer);
                    ansText = Double.toString(answer);
                    division = true;
                    break;

                default:
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("No operator selected");
                    alert.setTitle("Error");
                    alert.show();
            }
        }

        if(division)
        {
            tx2.setText(ansText);
        }
        else
        {
            ansText = formatNumberWithCommas(ansText);
            tx2.setText(ansText);
        }

        storeExpression();
        tx1.setText(" ");
        inputString = "";
        firstNumber = 0;
        secondNumber = 0;
        tx2.requestFocus();
    }


    public void storeExpression()
    {
        String firstNum = "";
        String secondNumberSTR;
        String answerText;
        if(division)
        {
            if(firstNumber == 0 && secondNumber == 0)
            {
                secondNumberSTR = Double.toString(dSecondNumber);
                firstNum = Double.toString(dFirstNumber);
            }
            else
            {
                firstNum = Long.toString(firstNumber);
                firstNum = formatNumberWithCommas(firstNum);
                secondNumberSTR = Long.toString(secondNumber);
                secondNumberSTR = formatNumberWithCommas(secondNumberSTR);
            }
            answerText = ansText;
            division = true;
        }
        else if(isPercentage)
        {
            answerText = ansText;
            secondNumberSTR = Double.toString(percentageComputed);
            isPercentage = false;
        }
        else
        {
            answerText = ansText;
            secondNumberSTR = Long.toString(secondNumber);
            answerText = formatNumberWithCommas(answerText);
            secondNumberSTR = formatNumberWithCommas(secondNumberSTR);
            firstNum = Long.toString(firstNumber);
            firstNum = formatNumberWithCommas(firstNum);

        }



        if(HistoryComputationController.computation.size() == 9)
        {
            HistoryComputationController.computation.remove(0);
            HistoryComputationController.computation.add(new CompletedExpression(firstNum,    secondNumberSTR, Character.toString(operatorGet), answerText));
        }
        else {
            HistoryComputationController.computation.add(new CompletedExpression(firstNum,    secondNumberSTR, Character.toString(operatorGet), answerText));
        }
    }

    public void percentageClicked(ActionEvent event)
    {

        System.out.println("Percentage clicked4");
        if(firstNumber>0 || dFirstNumber>0)
        {
            if(inputString.isBlank() || inputString.isEmpty())
            {
                System.out.println("Contents: " + inputString);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Value");
                alert.setHeaderText("No value given, please input a number");
                alert.show();
            }
            else {
                secondNumber = Long.parseLong(inputString);
                double ans;
                if(operatorGet == '*')
                {
                    ans = (double) secondNumber / 100.0;
                }
                else {
                    ans = (double) secondNumber / 100.0;
                    ans = (firstNumber>0) ?  ans * (double) firstNumber : ans * dFirstNumber;
                    System.out.println(ans);
                    System.out.println(dFirstNumber);
                }
                percentageComputed = ans;
                tx2.setText(Double.toString(ans));
                isPercentage = true;
            }

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Value on First Num");
            alert.setHeaderText("No value given, please input a number");
            alert.show();
        }

    }

    public void HistoryButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HistoryComputation.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("History Computation");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
        Stage stage2;
        stage2 = (Stage) pane1.getScene().getWindow();
        stage2.close();

    }









}