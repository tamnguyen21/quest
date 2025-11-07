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

    @Test
    void shouldReturn0WhenEmpty() {
        // given
        String value = "";

        // when
        int result = this.calc.add(value);


        // then
        Assertions.assertEquals(0, result);
    }

    @Test
    void shouldReturn0WhenNull() {
        // given
        String value = null;

        // when
        int result = this.calc.add(value);


        // then
        Assertions.assertEquals(0, result);
    }

    @Test
    void shouldReturn2When2() {
        // given
        String value = "2";

        // when
        int result = this.calc.add(value);


        // then
        Assertions.assertEquals(2, result);
    }

    @Test
    void shouldReturn36When12And5And19() {
        // given
        String value = "12,5,19";

        // when
        int result = this.calc.add(value);


        // then
        Assertions.assertEquals(36, result);
    }

    @Test
    void shouldReturn34When10And5And19() {
        // given
        String value = "10,5,19";

        // when
        int result = this.calc.add(value);


        // then
        Assertions.assertEquals(34, result);
    }

    @Test
    void shouldThrowNegativeNotAllowedExceptionWhenNegative() {
        // given
        String value = "10,-5,19";

        // when & then
        Assertions.assertThrows(
            NegativeNotAllowedException.class,
            () -> this.calc.add(value)
        );
    }

    @Test
    void shouldReturn34When10And5And19WithSemiColon() {
        // given
        String value = "10;5;19";

        // when
        int result = this.calc.add(value);


        // then
        Assertions.assertEquals(34, result);
    }

    @Test
    void shouldReturn34When10And5And19WithNewLine() {
        // given
        String value = """
                10
                5
                19
                """;

        // when
        int result = this.calc.add(value);


        // then
        Assertions.assertEquals(34, result);
    }

    @Test
    void shouldReturn34When10And5And19WithMixin() {
        // given
        String value = """
                10
                5,19
                4;2
                """;

        // when
        int result = this.calc.add(value);


        // then
        Assertions.assertEquals(40, result);
    }
}
