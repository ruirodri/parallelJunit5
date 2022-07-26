package com.everis;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
//import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
@Execution(ExecutionMode.CONCURRENT)
//@Execution(ExecutionMode.SAME_THREAD)
@Tag("back")
class BackendTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    @Order(1)
    void testeSoma(double p1, double p2, double soma) throws InterruptedException {
        Thread.sleep(3000);
        Date dt =new Date();
        System.out.println(String.format("%05.2f", p1)+" - "+dt.getTime());
        assertEquals( soma, p1+p2, 0.000001);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data2.csv", numLinesToSkip = 1)
    @Order(2)
    void testeSubtracao(double p1, double p2, double soma) throws InterruptedException {
        Thread.sleep(3000);
        Date dt =new Date();
        System.out.println(String.format("%05.2f", p1)+" - "+dt.getTime());
        assertEquals(soma,p1-p2, 0.000001);
    }

    @ParameterizedTest
    @MethodSource
    @Order(3)
    @Tag("soma")
    void testeSomaDois(double p1, double p2, double soma) throws InterruptedException {
        Thread.sleep(3000);
        Date dt =new Date();
        System.out.println(String.format("%05.2f", p1)+" - "+dt.getTime());
        assertEquals(soma,p1+p2, 0.000001);
    }

    private static Stream<Arguments> testeSomaDois() {
        return Stream.of(
                Arguments.of(104.0, 2.0, 106.0),
                Arguments.of(105.0, 2.0, 107.0),
                Arguments.of(106.0, 2.0, 108.0)
        );
    }


}