package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {
    private static final int MAXIMUM_RANDOM_NUM = 9;
    private final OutputView outputView;
    private final List<RacingCar> racingCars;

    public RacingGame(List<String> carNames) {
        this.outputView = new OutputView();
        this.racingCars = new ArrayList<>();

        for (String carName : carNames) {
            this.racingCars.add(new RacingCar(carName));
        }
    }

    public void run() {
        for (RacingCar racingCar : this.racingCars) {
            int randomValue = Randoms.pickNumberInRange(0, MAXIMUM_RANDOM_NUM);
            racingCar.move(randomValue);

            outputView.displayOutput(racingCar.getName(), racingCar.getPosition());
        }

        outputView.printNewLine();
    }

    public List<String> getWinner() {
        int winnerPosition = this.racingCars.getFirst().getPosition();

        // 공동 우승자 고려 List<String>으로 작성
        List<String> winnerNames = new ArrayList<>();
        winnerNames.add(this.racingCars.getFirst().getName());

        for (int i = 1; i < this.racingCars.size(); i++) {
            RacingCar racingCar = this.racingCars.get(i);
            int tmpPosition = racingCar.getPosition();

            if (tmpPosition == winnerPosition) winnerNames.add(racingCar.getName());
            if (tmpPosition > winnerPosition) {
                winnerPosition = tmpPosition;
                winnerNames.clear();
                winnerNames.add(racingCar.getName());
            }
        }

        return winnerNames;
    }
}
