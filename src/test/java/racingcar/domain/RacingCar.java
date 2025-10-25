package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;
    private static final int INITIAL_POSITION = 0;

    @Test
    @DisplayName("자동차 생성 시 초기 위치는 0")
    void createCarWithInitialPosition() {
        RacingCar car = new RacingCar("pobi");

        assertThat(car.getPosition()).isEqualTo(INITIAL_POSITION);
    }

    @Test
    @DisplayName("랜덤 값이 4 이상이면 자동차가 전진")
    void moveForwardWhenRandomValueIsGreaterThanOrEqualTo4() {
        RacingCar car = new RacingCar("pobi");

        car.move(MOVING_FORWARD);

        assertThat(car.getPosition()).isEqualTo(INITIAL_POSITION + 1);
    }

    @Test
    @DisplayName("랜덤 값이 4 미만이면 자동차가 정지")
    void stopWhenRandomValueIsLessThan4() {
        RacingCar car = new RacingCar("pobi");

        car.move(STOP);

        assertThat(car.getPosition()).isEqualTo(INITIAL_POSITION);
    }

    @Test
    @DisplayName("여러 번 이동 후 위치가 누적")
    void accumulatePositionAfterMultipleMoves() {
        RacingCar car = new RacingCar("pobi");

        car.move(MOVING_FORWARD);
        car.move(MOVING_FORWARD);
        car.move(MOVING_FORWARD);
        car.move(MOVING_FORWARD);

        assertThat(car.getPosition()).isEqualTo(INITIAL_POSITION + 4);
    }
}