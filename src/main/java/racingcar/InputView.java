package racingcar;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String CAR_NAME_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ROUND_COUNT_PROMPT = "시도할 횟수는 몇 회인가요?";

    public String readCarNames() {
        System.out.println(CAR_NAME_PROMPT);
        return Console.readLine(); // 입력값 검증 -> RacingController
    }

    public int readRoundCount() {
        System.out.println(ROUND_COUNT_PROMPT);
        return validateInputRoundCountFormat(Console.readLine());
    }

    private static int validateInputRoundCountFormat(String roundCount) {
        // 기본 입력 포맷 검증 로직 (숫자 포맷 일치 여부, 음이 아닌 정수)
        if (roundCount.isEmpty()) throw new IllegalArgumentException("시행 횟수 - 빈 값 입력 불가");

        try {
            int num = Integer.parseInt(roundCount);
            checkPositive(num);

            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시행 횟수 - 올바른 숫자 형식 필요");
        }
    }

    private static void checkPositive(int roundCount) {
        if (roundCount <= 0) throw new IllegalArgumentException("시행 횟수 - 양수 값만 입력 가능");
    }
}

