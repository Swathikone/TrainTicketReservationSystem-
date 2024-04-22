

// Train.java

public abstract class Train {
    protected String name;
    protected Seat[] seats;
    protected TrainState currentState;

    protected Train(String name, int numSeats) {
        this.name = name;
        this.seats = new Seat[numSeats];
        for (int i = 0; i < numSeats; i++) {
            seats[i] = new Seat(i + 1);
        }
        this.currentState = new AvailableState(this); // Initial state is available
    }

    public void setState(TrainState state) {
        this.currentState = state;
    }

    public String getAvailableSeats() {
        return currentState.getAvailableSeats();
    }

    public String bookSeat(int seatNumber) {
        return currentState.bookSeat(seatNumber);
    }

    public String cancelBooking(int seatNumber) {
        return currentState.cancelBooking(seatNumber);
    }

    public String getName() {
        return name;
    }
}

// TrainState.java

public interface TrainState {
    String getAvailableSeats();
    String bookSeat(int seatNumber);
    String cancelBooking(int seatNumber);
}

// AvailableState.java

public class AvailableState implements TrainState {
    private Train train;

    public AvailableState(Train train) {
        this.train = train;
    }

    @Override
    public String getAvailableSeats() {
        StringBuilder availableSeats = new StringBuilder();
        for (Seat seat : train.seats) {
            if (!seat.isBooked()) {
                availableSeats.append("Seat ").append(seat.getNumber()).append("\n");
            }
        }
        return availableSeats.toString();
    }

    @Override
    public String bookSeat(int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= train.seats.length) {
            Seat seat = train.seats[seatNumber - 1];
            if (!seat.isBooked()) {
                seat.book();
                train.setState(new BookedState(train));
                return "Seat " + seatNumber + " booked successfully.";
            } else {
                return "Seat " + seatNumber + " is already booked.";
            }
        } else {
            return "Invalid seat number.";
        }
    }

    @Override
    public String cancelBooking(int seatNumber) {
        return "No booking to cancel.";
    }
}

// BookedState.java

public class BookedState implements TrainState {
    private Train train;

    public BookedState(Train train) {
        this.train = train;
    }

    @Override
    public String getAvailableSeats() {
        return "No available seats.";
    }

    @Override
    public String bookSeat(int seatNumber) {
        return "All seats are booked.";
    }

    @Override
    public String cancelBooking(int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= train.seats.length) {
            Seat seat = train.seats[seatNumber - 1];
            if (seat.isBooked()) {
                seat.cancel();
                train.setState(new AvailableState(train));
                return "Booking for seat " + seatNumber + " cancelled successfully.";
            } else {
                return "Seat " + seatNumber + " is not booked.";
            }
        } else {
            return "Invalid seat number.";
        }
    }
}
