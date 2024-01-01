import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Cliente extends JFrame {

    private final JTextField txtIp;
    private final JTextField txtPort;
    private final JTextField txtMessage;
    private final JTextArea txtResponse;
    private final JButton btnPing;

    private Cliente() {
        super("Cliente");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel toolbar = new JPanel(new FlowLayout());

        toolbar.add(new JLabel("IP Address"));

        txtIp = new JTextField();
        txtIp.setPreferredSize(new Dimension(100, 20));
        toolbar.add(txtIp);

        toolbar.add(new JLabel("Port"));

        txtPort = new JTextField();
        txtPort.setPreferredSize(new Dimension(100, 20));
        toolbar.add(txtPort);

        toolbar.add(new JLabel("Message"));

        txtMessage = new JTextField();
        txtMessage.setPreferredSize(new Dimension(100, 20));
        toolbar.add(txtMessage);

        btnPing = new JButton("Ping");
        toolbar.add(btnPing);

        add(toolbar, BorderLayout.PAGE_START);

        txtResponse = new JTextArea();
        txtResponse.setPreferredSize(new Dimension(500, 300));
        txtResponse.setEditable(false);
        add(txtResponse, BorderLayout.CENTER);

        // use a socket to ping the server
        btnPing.addActionListener(e -> {
            String ipAddress = txtIp.getText();
            int portNumber = Integer.parseInt("0" + txtPort.getText());
            String message = txtMessage.getText();

            if (ipAddress == null || ipAddress.isEmpty() || portNumber == 0 || message == null || message.isEmpty()) {
                System.err.println("Invalid input: '" + ipAddress + "':'" + portNumber + "' '" + message + "'");
                return;
            }

            ping(ipAddress, portNumber, message);
        });

    }

    private void ping(String ip, int port, String message) {
        try (Socket socket = new Socket(ip, port);
             PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            txtResponse.append("Conectado al servidor en " + ip + ":" + port + System.lineSeparator());

            // Enviar el mensaje al servidor
            socketOut.println(message);

            // Recibir la respuesta del servidor
            String response = socketIn.readLine();
            txtResponse.append("Respuesta del servidor: " + response + System.lineSeparator());
            txtResponse.append("==========================================" + System.lineSeparator());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cliente::new);
    }
}