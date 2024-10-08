import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends JFrame {

    private final JTextField txtIp;
    private final JSpinner spnPort;
    private final JTextField txtMessage;
    private final JButton btnClear;
    private final JTextArea txtResponse;
    private final JButton btnPing;

    private Cliente() {
        super("Cliente");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel toolbar = new JPanel(new FlowLayout());

        toolbar.add(new JLabel("Dirección IP"));

        txtIp = new JTextField();
        txtIp.setPreferredSize(new Dimension(100, 20));
        toolbar.add(txtIp);

        toolbar.add(new JLabel("Puerto"));

        spnPort = new JSpinner(new SpinnerNumberModel(27015, 0, 65535, 1));
        toolbar.add(spnPort);

        toolbar.add(new JLabel("Mensaje"));

        txtMessage = new JTextField();
        txtMessage.setPreferredSize(new Dimension(100, 20));
        toolbar.add(txtMessage);

        btnPing = new JButton("Ping");
        toolbar.add(btnPing);

        txtResponse = new JTextArea();
        txtResponse.setEditable(false);

        btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> txtResponse.setText(""));


        // use a socket to ping the server
        btnPing.addActionListener(e -> {

            if (txtIp.getText().isBlank()) {
                txtResponse.append("Dirección IP requerida" + System.lineSeparator());
                return;
            }

            if (txtMessage.getText().isBlank()) {
                txtResponse.append("Mensaje requerido" + System.lineSeparator());
                return;
            }

            String ipAddress = txtIp.getText();
            int portNumber = (Integer) spnPort.getValue();
            String message = txtMessage.getText();

            ping(ipAddress, portNumber, message);
        });

        toolbar.add(btnClear);

        add(new JScrollPane(txtResponse), BorderLayout.CENTER);
        add(toolbar, BorderLayout.PAGE_START);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cliente::new);
    }

    private void ping(String ip, int port, String message) {
        new Thread(() -> {
            txtResponse.append("Intentando conectar con " + ip + ":" + port + System.lineSeparator());
            try (Socket socket = new Socket(ip, port);
                 PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                txtResponse.append("Conexión establecida con " + ip + ":" + port + System.lineSeparator());

                // Enviar el mensaje al servidor
                long start = System.currentTimeMillis();
                socketOut.println(message);

                // Recibir la respuesta del servidor
                String response = socketIn.readLine();
                long end = System.currentTimeMillis();
                txtResponse.append("Respuesta del servidor: \"" + response + "\" " + (end - start) + "ms." + System.lineSeparator());

            } catch (IOException e) {
                txtResponse.append("Error al conectar al servidor en " + ip + ":" + port + System.lineSeparator());

                System.err.println("Error al conectar al servidor");
                e.printStackTrace();
            }

            txtResponse.append("==========================================" + System.lineSeparator());
        }).start();
    }
}
