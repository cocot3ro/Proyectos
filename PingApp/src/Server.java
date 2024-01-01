import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame implements Runnable {
    private final JTextArea txtMessage;
    private final JSpinner spnPort;
    private final JButton btnStart;
    private final JButton btnStop;
    private final JButton btnClear;
    private boolean running = false;

    private Server() {
        super("Servidor");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        txtMessage = new JTextArea();
        spnPort = new JSpinner(new SpinnerNumberModel(27015, 0, 65535, 1));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnClear = new JButton("Clear");

        btnStop.setEnabled(false);

        btnStart.addActionListener(e -> {
            running = true;
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
            new Thread(this).start();
        });

        btnStop.addActionListener(e -> {
            running = false;
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        });

        btnClear.addActionListener(e -> txtMessage.setText(""));

        JPanel toolbar = new JPanel(new FlowLayout());

        toolbar.add(new JLabel("Puerto"));
        toolbar.add(spnPort);
        toolbar.add(btnStart);
        toolbar.add(btnStop);
        toolbar.add(btnClear);

        txtMessage.setEditable(false);

        add(new JScrollPane(txtMessage), BorderLayout.CENTER);
        add(toolbar, BorderLayout.PAGE_START);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Server::new);
    }

    @Override
    public void run() {
        final int PORT = (Integer) spnPort.getValue();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            txtMessage.append("Servidor escuchando en el puerto " + PORT + System.lineSeparator());

            while (running) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter socketOut = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    txtMessage.append("Cliente conectado desde " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                    // Recibir datos del cliente
                    String inputLine = socketIn.readLine();
                    txtMessage.append(" Mensaje recibido: " + inputLine + System.lineSeparator());

                    // Enviar respuesta al cliente
                    socketOut.println("¡Mensaje recibido correctamente! " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " -> " + inputLine);


                } catch (IOException e) {
                    System.err.println("Error al conectar con el cliente");
                    e.printStackTrace();
                }
            }

            txtMessage.append("==========================================" + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor");
            e.printStackTrace();
        }
    }
}
