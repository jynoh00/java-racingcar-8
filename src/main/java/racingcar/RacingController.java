package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingController {
    private static final int NAME_LENGTH_THRESHOLD = 5;
    private static final String SEPARATOR = ",";

    private final InputView inputView;
    private final OutputView outputView;
    private RacingGame racingGame; // RacingCar 클래스 사용, 1 Round 처리

    public RacingController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        try {
            // 검증 및 파싱된 입력값
            List<String> parsedCarNamesList = parseCarNames(inputView.readCarNames());
            int roundCount = inputView.readRoundCount();

            racingGame = new RacingGame(parsedCarNamesList);

            loopGameRunner(roundCount);

            outputView.displayResult(racingGame.getWinner());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            Console.close();
        }
    }

    private static List<String> parseCarNames(String input) {
        validateInputCarNameEmptyOrBlank(input);

        if (input.contains(SEPARATOR)) {
            List<String> carNames = Arrays.stream(input.split(SEPARATOR))
                    .map(String::trim)
                    .collect(Collectors.toList());
            validateSplitNamesCheck(carNames);

            return carNames;
        }

        return List.of(input);
    }

    // 기본 상태 입력 문자열 검증
    private static void validateInputCarNameEmptyOrBlank(String carNames) {
        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAMES_INPUT_NULL_OR_EMPTY.getMessage());
        }
        if (carNames.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAMES_INPUT_BLANK.getMessage());
        }
    }

    // 스플릿한 문자열 개별 검증
    private static void validateSplitNamesCheck(List<String> carNames) {
        for (String carName : carNames) {
            if (carName == null || carName.isEmpty()) {
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_NULL_OR_EMPTY.getMessage());
            }
            if (carName.isBlank()) {
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_BLANK.getMessage());
            }
            if (carName.length() > NAME_LENGTH_THRESHOLD) {
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_TOO_LONG.getMessage());
            }
        }
    }

    private void loopGameRunner(int roundCount) {
        for (int i = 0; i < roundCount; i++) {
            if (i == 0) outputView.printInitOutputMessage();
            racingGame.run(); // 전체 차량 이동
        }
    }
}
