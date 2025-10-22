package racingcar;

public class RacingCar {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int FORWARD_THRESHOLD = 4;

    private final String name;
    private int position;

    public RacingCar(String name) {
        validateName(name);

        this.name = name;
        this.position = 0;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("자동차 이름 빈 값 불가");
        if (name.isBlank()) throw new IllegalArgumentException("자동차 이름 공백 문자 불가");
        if (name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("자동차 이름 길이 초과");
        if (name.contains(",")) throw new IllegalArgumentException("자동차 이름 쉼표 불가");
    }

    public void move(int randomValue){
        if (randomValue >= FORWARD_THRESHOLD) position++;
    }

    public String getName(){ return this.name; }
    public int getPosition(){ return this.position; }
}
