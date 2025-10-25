package racingcar.view;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest extends NsTest {

    @Test
    @DisplayName("시도 횟수가 빈 값이면 예외 처리")
    void throwExceptionWhenRoundCountIsEmpty() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi", " "))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @DisplayName("시도 횟수가 숫자가 아니면 예외 처리")
    @ValueSource(strings = {"abc", "1.5", "one", "!@#"})
    void throwExceptionWhenRoundCountIsNotNumber(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi", input))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @DisplayName("시도 횟수가 0 이하면 예외 처리")
    @ValueSource(strings = {"0", "-1", "-10"})
    void throwExceptionWhenRoundCountIsNotPositive(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi", input))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        racingcar.Application.main(new String[]{});
    }
}