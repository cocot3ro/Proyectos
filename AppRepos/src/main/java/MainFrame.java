import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MainFrame() {
        super("Repository control application");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setVisible(true);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
