package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import camp.nextstep.edu.missionutils.test.NsTest;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    @DisplayName("게임 생성 시 입력한 이름으로 자동차들 생성")
    void createGameWithCarNames() {
        List<String> carNames = List.of("pobi", "woni", "jun");

        RacingGame game = new RacingGame(carNames);

        assertThat(game.getWinner()).hasSize(3);
    }

    @Test
    @DisplayName("단독 우승자를 찾기")
    void findSingleWinner() {
        assertRandomNumberInRangeTest(
                () -> {
                    RacingGame game = new RacingGame(List.of("pobi", "woni"));
                    game.run();

                    List<String> winners = game.getWinner();

                    assertThat(winners).containsExactly("pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("공동 우승자를 찾기")
    void findMultipleWinners() {
        assertRandomNumberInRangeTest(
                () -> {
                    RacingGame game = new RacingGame(List.of("pobi", "woni", "jun"));
                    game.run(); // pobi=4, woni=4, jun=3

                    List<String> winners = game.getWinner();

                    assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
                },
                MOVING_FORWARD, MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("모두 같은 위치면 전원 우승자")
    void allWinnersWhenSamePosition() {
        assertRandomNumberInRangeTest(
                () -> {
                    RacingGame game = new RacingGame(List.of("pobi", "woni", "jun"));
                    game.run();

                    List<String> winners = game.getWinner();

                    assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
                },
                STOP, STOP, STOP
        );
    }

    @Test
    @DisplayName("여러 라운드 후 최종 우승자 찾기")
    void findWinnerAfterMultipleRounds() {
        assertRandomNumberInRangeTest(
                () -> {
                    RacingGame game = new RacingGame(List.of("pobi", "woni"));
                    game.run();
                    game.run();
                    game.run();

                    List<String> winners = game.getWinner();

                    assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD, MOVING_FORWARD, STOP, MOVING_FORWARD
        );
    }

    @Override
    public void runMain() {
        racingcar.Application.main(new String[]{});
    }
}