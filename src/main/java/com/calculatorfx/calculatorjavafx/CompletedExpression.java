package com.calculatorfx.calculatorjavafx;

public class CompletedExpression {


    private String firstNumber;

    private String secondNumber;

    private String operator;

    private String answer;

    CompletedExpression(String firstNumber, String secondNumber, String operator, String answer)
    {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
        this.answer = answer;
    }

    public String getFirstNumber()
    {
        return firstNumber;
    }
    public String getSecondNumber()
    {
        return secondNumber;
    }

    public String getOperator(){
        return operator;
    }

    public String getAnswer(){
        return answer;
    }




}
