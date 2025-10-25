package racingcar.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RacingControllerTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    @DisplayName("정상적인 실행")
    void runNormalRacingGame() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("단일 자동차 경주 진행")
    void runWithSingleCar() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi", "2");
                    assertThat(output()).contains(
                            "pobi : --",
                            "최종 우승자 : pobi"
                    );
                },
                MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    @DisplayName("여러 라운드 경주 진행")
    void runMultipleRounds() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "3");
                    assertThat(output()).contains(
                            "실행 결과",
                            "최종 우승자"
                    );
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD, MOVING_FORWARD, STOP, MOVING_FORWARD
        );
    }

    @Test
    @DisplayName("공동 우승자를 쉼표로 구분 출력")
    void displayMultipleWinners() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "1");
                    assertThat(output()).contains("최종 우승자 : pobi, woni");
                },
                MOVING_FORWARD, MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("자동차 이름이 빈 값이면 예외 처리")
    void throwExceptionWhenCarNameIsEmpty() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("자동차 이름이 공백이면 예외 처리")
    void throwExceptionWhenCarNameIsBlank() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("   ", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @DisplayName("자동차 이름이 5자를 초과하면 예외 처리")
    @ValueSource(strings = {"pobi,javaji", "abcdef", "pobi,woni,toolong"})
    void throwExceptionWhenCarNameIsTooLong(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input, "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("쉼표 뒤에 값이 없으면 예외 처리")
    void throwExceptionWhenEmptyValueAfterComma() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("쉼표 사이에 공백만 있으면 예외 처리")
    void throwExceptionWhenOnlySpaceBetweenCommas() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi, , jun", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("중복된 자동차 이름은 하나로 처리")
    void handleDuplicateCarNames() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,pobi,woni", "1");
                    assertThat(output()).contains("pobi", "woni");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("자동차 이름 앞뒤 공백 제거")
    void trimCarNames() {
        assertRandomNumberInRangeTest(
                () -> {
                    run(" pobi , woni ", "1");
                    assertThat(output()).contains("pobi : -", "woni : ");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Override
    public void runMain() {
        racingcar.Application.main(new String[]{});
    }
}