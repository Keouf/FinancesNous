package com.example.pcportablevidjay.financesnous;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
   /* UN EXEMPLE CONCRÊT */
//    private MainActivity mCalculator;
//
//    @Before
//    public void setUp() {
//        mCalculator = new MainActivity();
//    }
//
//    @Test
//    public void addTwoNumbers() {
//        double resultAdd = mCalculator.add(1d, 1d);
//        assertThat(resultAdd, is(equalTo(2d)));
//    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}