package fr.formation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalcTest {
    private Calc calc;

    @BeforeEach
    public void beforeEach() {
        this.calc = new Calc();
    }

    @Test
    void shouldReturn11When5And6() {
        // given / arrange
        String a = "5";
        String b = "6";

        // when / act
        int result = this.calc.add(a, b);

        // then / assert
        Assertions.assertEquals(11, result);
    }

    @Test
    void shouldReturn10When4And6() {
        // given / arrange
        String a = "4";
        String b = "6";

        // when / act
        int result = this.calc.add(a, b);

        // then / assert
        Assertions.assertEquals(10, result);
    }

    @Test
    void shouldReturn0WhenFirstArgIsNull() {
        // given / arrange
        String a = null;
        String b = "6";

        // when / act
        int result = this.calc.add(a, b);

        // then / assert
        Assertions.assertEquals(0, result);
    }

    @Test
    void shouldReturn15When10And5() {
        // given / arrange
        String a = "10";
        String b = "5";

        // when / act
        int result = this.calc.add(a, b);

        // then / assert
        Assertions.assertEquals(15, result);
    }

    @ParameterizedTest
    @CsvSource({
        "5,6,11",
        "4,6,10",
        ",6,0",
        "6,,0",
        "10,5,15",
    })
    // @CsvFileSource(files = "demotest.csv")
    void shouldReturnGoodResult(String a, String b, int expected) {
        // given / arrange

        // when / act
        int result = this.calc.add(a, b);

        // then / assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldThrowNumberFormatException() {
        // given / arrange
        String a = "abc";
        String b = "20";

        // when / act
        Assertions.assertThrows(
            NumberFormatException.class,
            () -> this.calc.add(a, b)
        );
    }
}
