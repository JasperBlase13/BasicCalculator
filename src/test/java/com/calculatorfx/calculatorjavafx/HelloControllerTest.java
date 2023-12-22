package com.calculatorfx.calculatorjavafx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    @Test
    void getAnswerAddition() {
        var arithmetic = new HelloController();
        assertEquals(579,arithmetic.getAnswer(123, 456, "Addition"));
    }

    @Test
    void getAnswerSubtraction()
    {
        var arithmetic = new HelloController();
        assertEquals(400,arithmetic.getAnswer(700, 300, "Subtraction"));
    }

    @Test
    void getAnswerMultiplicaton()
    {
        var arithmetic = new HelloController();
        assertEquals(1000,arithmetic.getAnswer(10, 100, "Multiplication"));
    }



}