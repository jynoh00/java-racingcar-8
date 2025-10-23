package racingcar;

public class RacingCar {
    private static final int FORWARD_THRESHOLD = 4;

    private final String name;
    private int position;

    public RacingCar(String name) {
        this.name = name;
        this.position = 0;
    }

    public void move(int randomValue) {
        if (randomValue >= FORWARD_THRESHOLD) position++;
    }

    public String getName() { return this.name; }
    public int getPosition() { return this.position; }
}
