package racingcar;

import java.util.List;

public class OutputView {
    private static final char MOVING_LINE = '-';

    public void printNewLine() {
        System.out.println();
    }

    public void printInitOutputMessage() {
        System.out.println("실행 결과");
    }

    public void displayOutput(String carName, int carPosition) {
        // 매 Round마다 호출될 결과 출력
        System.out.println(carName + " : " + String.valueOf(MOVING_LINE).repeat(carPosition));
    }

    public void displayResult(List<String> winnerNames) {
        // 최종 우승자 결과 출력
        System.out.print("최종 우승자 : " + String.join(", ", winnerNames));
    }
}
