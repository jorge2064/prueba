package com.empresa.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CalcTest {

    @Test
    void suma_funciona() {
        Calc calc = new Calc();
        assertEquals(7, calc.suma(3, 4));
    }

    @Test
    void resta_funciona() {
        Calc calc = new Calc();
        assertEquals(1, calc.resta(5, 4));
    }
}
