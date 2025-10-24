package racingcar;

public enum ErrorMessage {
    CAR_NAME_NULL_OR_EMPTY("자동차 이름은 빈 값일 수 없습니다."),
    CAR_NAME_BLANK("자동차 이름은 공백으로만 구성될 수 있습니다."),
    CAR_NAME_TOO_LONG("자동차 이름은 5글자를 초과할 수 없습니다."),

    CAR_NAMES_INPUT_NULL_OR_EMPTY("자동차 이름 입력은 빈 값일 수 없습니다."),
    CAR_NAMES_INPUT_BLANK("자동차 이름 입력은 공백만으로 구성될 수 없습니다."),

    ROUND_COUNT_EMPTY("시도 횟수는 빈 값일 수 없습니다."),
    ROUND_COUNT_INVALID_FORMAT("시도 횟수는 올바른 숫자 형식이어야 합니다."),
    ROUND_COUNT_NOT_POSITIVE("시도 횟수는 양수여야 합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
