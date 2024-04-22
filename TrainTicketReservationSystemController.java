// TrainTicketReservationSystemView.java
import javax.swing.*;

public class TrainTicketReservationSystemView {
    private JFrame frame;
    private JTextArea textArea;
    private JButton viewSeatsButton;
    private JButton bookSeatButton;
    private JButton cancelBookingButton;
    private JButton exitButton;

    public TrainTicketReservationSystemView() {
        frame = new JFrame("Train Ticket Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        textArea = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        viewSeatsButton = new JButton("View available seats");
        bookSeatButton = new JButton("Book a seat");
        cancelBookingButton = new JButton("Cancel booking");
        exitButton = new JButton("Exit");

        buttonPanel.add(viewSeatsButton);
        buttonPanel.add(bookSeatButton);
        buttonPanel.add(cancelBookingButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getViewSeatsButton() {
        return viewSeatsButton;
    }

    public JButton getBookSeatButton() {
        return bookSeatButton;
    }

    public JButton getCancelBookingButton() {
        return cancelBookingButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}

// TrainTicketReservationSystemController.java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainTicketReservationSystemController {
    private TrainTicketReservationSystemView view;
    private Train train;

    public TrainTicketReservationSystemController(TrainTicketReservationSystemView view, Train train) {
        this.view = view;
        this.train = train;

        view.getViewSeatsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTextArea().setText(train.getAvailableSeats());
            }
        });

        view.getBookSeatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seatNumberStr = JOptionPane.showInputDialog(view.getFrame(), "Enter seat number to book:");
                try {
                    int seatNumber = Integer.parseInt(seatNumberStr);
                    String message = train.bookSeat(seatNumber);
                    view.getTextArea().setText(message);
                } catch (NumberFormatException ex) {
                    view.getTextArea().setText("Invalid seat number. Please enter a valid integer.");
                }
            }
        });

        view.getCancelBookingButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seatNumberStr = JOptionPane.showInputDialog(view.getFrame(), "Enter seat number to cancel booking:");
                try {
                    int seatNumber = Integer.parseInt(seatNumberStr);
                    String message = train.cancelBooking(seatNumber);
                    view.getTextArea().setText(message);
                } catch (NumberFormatException ex) {
                    view.getTextArea().setText("Invalid seat number. Please enter a valid integer.");
                }
            }
        });

        view.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Train train = new LocalTrain("Local Train", 10); // Create a local train
        TrainTicketReservationSystemView view = new TrainTicketReservationSystemView();
        TrainTicketReservationSystemController controller = new TrainTicketReservationSystemController(view, train);
    }
}
