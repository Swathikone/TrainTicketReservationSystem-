

// TrainTicketReservationSystem.java (Singleton)
public class TrainTicketReservationSystem {
    private static TrainTicketReservationSystem instance; // Singleton instance
    private TrainTicketReservationSystemView view;
    private Train train;

    // Private constructor to prevent instantiation from other classes
    private TrainTicketReservationSystem() {
        view = new TrainTicketReservationSystemView();
        train = TrainFactory.createTrain("Local", "Local Train", 10); // Create a local train

        // Controller setup
        TrainTicketReservationSystemController controller = new TrainTicketReservationSystemController(view, train);
        view.setController(controller);
    }

    // Static method to get the single instance of TrainTicketReservationSystem
    public static TrainTicketReservationSystem getInstance() {
        if (instance == null) {
            instance = new TrainTicketReservationSystem();
        }
        return instance;
    }

    public static void main(String[] args) {
        TrainTicketReservationSystem system = TrainTicketReservationSystem.getInstance(); // Get instance
    }
}

// TrainTicketReservationSystemView.java
import javax.swing.*;

public class TrainTicketReservationSystemView {
    private JFrame frame;
    private JTextArea textArea;
    private JButton viewSeatsButton;
    private JButton bookSeatButton;
    private JButton cancelBookingButton;
    private JButton exitButton;
    private TrainTicketReservationSystemController controller;

    public TrainTicketReservationSystemView() {
        frame = new JFrame("Train Ticket Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        // Create and set background image
        JLabel background = new JLabel(new ImageIcon("https://www.google.com/url?sa=i&url=https%3A%2F%2Fblog.bankbazaar.com%2Firctc-to-mandate-the-use-of-aadhaar-card-for-railway-ticket-booking%2F&psig=AOvVaw1MyRA4J8yovFtzmuwJK40y&ust=1713808297185000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCIDD3cnv04UDFQAAAAAdAAAAABAJ"));
        frame.setContentPane(background);
        frame.setLayout(new BorderLayout());

        // Create dashboard panel
        JPanel dashboardPanel = new JPanel(new GridLayout(4, 1));
        viewSeatsButton = new JButton("View available seats");
        bookSeatButton = new JButton("Book a seat");
        cancelBookingButton = new JButton("Cancel booking");
        exitButton = new JButton("Exit");

        dashboardPanel.add(viewSeatsButton);
        dashboardPanel.add(bookSeatButton);
        dashboardPanel.add(cancelBookingButton);
        dashboardPanel.add(exitButton);

        frame.add(dashboardPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void setController(TrainTicketReservationSystemController controller) {
        this.controller = controller;
        viewSeatsButton.addActionListener(controller);
        bookSeatButton.addActionListener(controller);
        cancelBookingButton.addActionListener(controller);
        exitButton.addActionListener(controller);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}

// TrainTicketReservationSystemController.java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainTicketReservationSystemController implements ActionListener {
    private TrainTicketReservationSystemView view;
    private Train train;

    public TrainTicketReservationSystemController(TrainTicketReservationSystemView view, Train train) {
        this.view = view;
        this.train = train;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getViewSeatsButton()) {
            view.getTextArea().setText(train.getAvailableSeats());
        } else if (e.getSource() == view.getBookSeatButton()) {
            String seatNumberStr = JOptionPane.showInputDialog(view.getFrame(), "Enter seat number to book:");
            try {
                int seatNumber = Integer.parseInt(seatNumberStr);
                String message = train.bookSeat(seatNumber);
                view.getTextArea().setText(message);
            } catch (NumberFormatException ex) {
                view.getTextArea().setText("Invalid seat number. Please enter a valid integer.");
            }
        } else if (e.getSource() == view.getCancelBookingButton()) {
            String seatNumberStr = JOptionPane.showInputDialog(view.getFrame(), "Enter seat number to cancel booking:");
            try {
                int seatNumber = Integer.parseInt(seatNumberStr);
                String message = train.cancelBooking(seatNumber);
                view.getTextArea().setText(message);
            } catch (NumberFormatException ex) {
                view.getTextArea().setText("Invalid seat number. Please enter a valid integer.");
            }
        } else if (e.getSource() == view.getExitButton()) {
            view.getFrame().dispose();
        }
    }
}
