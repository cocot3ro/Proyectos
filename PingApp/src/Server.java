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

    // TODO: campo para introducir el puerto, boton para iniciar el servidor, boton para detener el servidor

    private Server() {
        super("Servidor");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        txtMessage = new JTextArea();
        txtMessage.setEditable(false);
        add(txtMessage, BorderLayout.CENTER);

        new Thread(this).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Server::new);
    }

    @Override
    public void run() {
        final int PORT = 27015;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            txtMessage.append("Servidor escuchando en el puerto " + PORT + System.lineSeparator());

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter socketOut = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    txtMessage.append("Cliente conectado desde " + clientSocket.getInetAddress() + System.lineSeparator());

                    // Recibir datos del cliente
                    String inputLine = socketIn.readLine();
                    txtMessage.append("Mensaje recibido: " + inputLine + System.lineSeparator());

                    // Enviar respuesta al cliente
                    socketOut.println("¡Mensaje recibido correctamente! -> " + clientSocket.getInetAddress() + ": " + inputLine);

                    txtMessage.append("==========================================" + System.lineSeparator());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
