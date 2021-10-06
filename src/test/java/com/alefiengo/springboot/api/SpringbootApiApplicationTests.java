package com.alefiengo.springboot.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

class SpringbootApiApplicationTests {

    Calculator calculator = new Calculator();

    @Test
    @DisplayName("Sum of valA and valB")
    void sumValues() {
        //given
        int valA = 2;
        int valB = 3;

        //when
        int expected = calculator.sum(valA, valB);

        //then
        int result = 5;
        assertThat(expected).isEqualTo(result);
    }

    static class Calculator {
        int sum(int val_a, int val_b) {
            return val_a + val_b;
        }
    }

}
