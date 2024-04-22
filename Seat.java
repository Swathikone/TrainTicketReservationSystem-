

import java.util.Iterator;

public class Seat implements Iterable<Seat> {
    private int number;
    private boolean booked;

    public Seat(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return booked;
    }

    public void book() {
        booked = true;
    }

    public void cancel() {
        booked = false;
    }

    @Override
    public Iterator<Seat> iterator() {
        return new SeatIterator(this);
    }

    // Inner class implementing Iterator interface
    private class SeatIterator implements Iterator<Seat> {
        private Seat seat;

        public SeatIterator(Seat seat) {
            this.seat = seat;
        }

        @Override
        public boolean hasNext() {
            // In this example, assume the seat is always the last one
            return false;
        }

        @Override
        public Seat next() {
            return seat;
        }
    }
}
