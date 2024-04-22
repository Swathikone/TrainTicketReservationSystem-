

// TrainFactory.java
public class TrainFactory {
    public static Train createTrain(String type, String name, int numSeats) {
        if (type.equalsIgnoreCase("Local")) {
            return new LocalTrain(name, numSeats);
        } else if (type.equalsIgnoreCase("Express")) {
            return new ExpressTrain(name, numSeats);
        } else {
            throw new IllegalArgumentException("Invalid train type: " + type);
        }
    }
}
