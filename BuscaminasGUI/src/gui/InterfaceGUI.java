package gui;

import motorBM.Buscaminas;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterfaceGUI extends JFrame implements Runnable {

    private Thread thread;
    private JLabel[][] tablero;
    private Buscaminas bm;
    private int numBanderas;
    private boolean bothMousePressed;
    private boolean cronoIsRunning;
    private boolean principiante, intermedio, experto, custom;
    private JTextField txtFieldFilas;
    private JTextField txtFieldColumnas;
    private JTextField txtFieldMinas;
    private JLabel btnMain;
    private JLabel imgContMinas0;
    private JLabel imgContMinas1;
    private JLabel imgContMinas2;
    private JLabel imgTimer0;
    private JLabel imgTimer1;
    private JLabel imgTimer2;
    private JLabel imgTopLeft;
    private JLabel imgTop;
    private JLabel imgTopRight;
    private JLabel imgLeft;
    private JLabel imgRight;
    private JLabel imgBotLeft;
    private JLabel imgBot;
    private JLabel imgBotRight;
    private JPanel main;
    private JRadioButtonMenuItem rbtnCustom;
    private JRadioButtonMenuItem rbtnExperto;
    private JRadioButtonMenuItem rbtnIntermedio;
    private JRadioButtonMenuItem rbtnPrincipiante;

    public InterfaceGUI() {
        this.numBanderas = 0;
        initComponents();
        bm = new Buscaminas(getParms());
        initOtherComponents();
        reiniciarCronometro();
        inicializarTablero();
        thread = new Thread(this);
    }

    private void initOtherComponents() {
        char[][] tableroChars = bm.getTablero();
        setSize((tableroChars[0].length * 32 + 40) + 16, (tableroChars.length * 32 + 124) + 61);
        main.setPreferredSize(new Dimension((tableroChars[0].length * 32 + 40), (tableroChars.length * 32 + 124)));

        imgTopLeft = new JLabel();
        imgTopLeft.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/tablero/topLeft.png"))));
        imgTopLeft.setBorder(null);
        imgTopLeft.setVisible(true);
        main.add(imgTopLeft, new AbsoluteConstraints(0, 0));

        imgTop = new JLabel();
        imgTop.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/tablero/top.png"))).getImage().getScaledInstance((tableroChars[0].length * 32), 104, 0)));
        imgTop.setBorder(null);
        imgTop.setVisible(true);
        main.add(imgTop, new AbsoluteConstraints(20, 0));

        imgTopRight = new JLabel();
        imgTopRight.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/tablero/topRight.png"))));
        imgTopRight.setBorder(null);
        imgTopRight.setVisible(true);
        main.add(imgTopRight, new AbsoluteConstraints(tableroChars[0].length * 32 + 20, 0));

        imgLeft = new JLabel();
        imgLeft.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/tablero/left.png"))).getImage().getScaledInstance(20, (tableroChars.length * 32), 0)));
        imgLeft.setBorder(null);
        imgLeft.setVisible(true);
        main.add(imgLeft, new AbsoluteConstraints(0, 104, 20, tableroChars.length * 32));

        imgRight = new JLabel();
        imgRight.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/tablero/right.png"))).getImage().getScaledInstance(20, (tableroChars.length * 32), 0)));
        imgRight.setBorder(null);
        imgRight.setVisible(true);
        main.add(imgRight, new AbsoluteConstraints(tableroChars[0].length * 32 + 20, 104));

        imgBotLeft = new JLabel();
        imgBotLeft.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/tablero/botLeft.png"))));
        imgBotLeft.setBorder(null);
        imgBotLeft.setVisible(true);
        main.add(imgBotLeft, new AbsoluteConstraints(0, tableroChars.length * 32 + 104));

        imgBot = new JLabel();
        imgBot.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/tablero/bot.png"))).getImage().getScaledInstance((tableroChars[0].length * 32), 20, 0)));
        imgBot.setBorder(null);
        imgBot.setVisible(true);
        main.add(imgBot, new AbsoluteConstraints(20, tableroChars.length * 32 + 104));

        imgBotRight = new JLabel();
        imgBotRight.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/tablero/botRight.png"))));
        imgBotRight.setBorder(null);
        imgBotRight.setVisible(true);
        main.add(imgBotRight, new AbsoluteConstraints(tableroChars[0].length * 32 + 20, tableroChars.length * 32 + 104, 20, 20));

        imgContMinas0 = new JLabel();
        imgContMinas0.setBorder(null);
        main.add(imgContMinas0, new AbsoluteConstraints(32, 28));
        imgContMinas0.setVisible(true);
        main.setComponentZOrder(imgContMinas0, 1);

        imgContMinas1 = new JLabel();
        imgContMinas1.setBorder(null);
        imgContMinas1.setVisible(true);
        main.add(imgContMinas1, new AbsoluteConstraints(58, 28));
        main.setComponentZOrder(imgContMinas1, 1);

        imgContMinas2 = new JLabel();
        imgContMinas2.setBorder(null);
        imgContMinas2.setVisible(true);
        main.add(imgContMinas2, new AbsoluteConstraints(84, 28));
        main.setComponentZOrder(imgContMinas2, 1);

        btnMain = new JLabel();
        btnMain.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/cara/cara1.png"))));
        btnMain.setBorder(null);
        btnMain.setVisible(true);
        main.add(btnMain, new AbsoluteConstraints((tableroChars[0].length * 32 + 40) / 2 - 26, 26));
        main.setComponentZOrder(btnMain, 1);
        btnMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                btnMainMousePressed(evt);
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                btnMainMouseReleased(evt);
            }
        });

        imgTimer0 = new JLabel();
        imgTimer0.setBorder(null);
        imgTimer0.setVisible(true);
        main.add(imgTimer0, new AbsoluteConstraints((tableroChars[0].length * 32 + 40) - 110, 28));
        main.setComponentZOrder(imgTimer0, 1);

        imgTimer1 = new JLabel();
        imgTimer1.setBorder(null);
        imgTimer1.setVisible(true);
        main.add(imgTimer1, new AbsoluteConstraints((tableroChars[0].length * 32 + 40) - 110 + 26, 28));
        main.setComponentZOrder(imgTimer1, 1);

        imgTimer2 = new JLabel();
        imgTimer2.setBorder(null);
        imgTimer2.setVisible(true);
        main.add(imgTimer2, new AbsoluteConstraints((tableroChars[0].length * 32 + 40) - 110 + 52, 28));
        main.setComponentZOrder(imgTimer2, 1);

        repaint();
    }

    private void initComponents() {

        ButtonGroup rbtngrpGameOptions = new ButtonGroup();
        main = new JPanel();
        JMenuBar jMenuBar1 = new JMenuBar();
        // Variables declaration
        JMenu jMenu1 = new JMenu();
        JMenuItem menuNewGame = new JMenuItem();
        JPopupMenu.Separator jSeparator1 = new JPopupMenu.Separator();
        JMenuItem jMenuItem2 = new JMenuItem();
        rbtnPrincipiante = new JRadioButtonMenuItem();
        rbtnIntermedio = new JRadioButtonMenuItem();
        rbtnExperto = new JRadioButtonMenuItem();
        rbtnCustom = new JRadioButtonMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(100, 100));
        getContentPane().setLayout(new AbsoluteLayout());

        main.setLayout(new AbsoluteLayout());
        getContentPane().add(main, new AbsoluteConstraints(0, 0, -1, -1));

        jMenu1.setText("Juego");

        menuNewGame.setText("Nuevo Juego");
        menuNewGame.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                menuNewGameMouseReleased(evt);
            }
        });
        jMenu1.add(menuNewGame);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("                           Alto     Ancho     Minas");
        jMenu1.add(jMenuItem2);

        rbtngrpGameOptions.add(rbtnPrincipiante);
        rbtnPrincipiante.setSelected(true);
        rbtnPrincipiante.setText("Principiante:     9          9              10");
        rbtnPrincipiante.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                rbtnPrincipianteMouseReleased(evt);
            }
        });
        jMenu1.add(rbtnPrincipiante);

        rbtngrpGameOptions.add(rbtnIntermedio);
        rbtnIntermedio.setText("Intermedio:      16        16             40");
        rbtnIntermedio.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                rbtnIntermedioMouseReleased(evt);
            }
        });
        jMenu1.add(rbtnIntermedio);

        rbtngrpGameOptions.add(rbtnExperto);
        rbtnExperto.setText("Experto:            16         30            99");
        rbtnExperto.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                rbtnExpertoMouseReleased(evt);
            }
        });
        jMenu1.add(rbtnExperto);

        rbtngrpGameOptions.add(rbtnCustom);
        rbtnCustom.setText("Personalizado");
        rbtnCustom.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                rbtnCustomMousePressed(evt);
            }

            public void mouseReleased(MouseEvent evt) {
                rbtnCustomMouseReleased(evt);
            }
        });
        jMenu1.add(rbtnCustom);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);
    }

    private void rbtnPrincipianteMouseReleased(MouseEvent ignoredEvt) {
        rbtnCustom.setText("Personalizado");
        nuevoJuego();
    }

    private void rbtnIntermedioMouseReleased(MouseEvent ignoredEvt) {
        rbtnCustom.setText("Personalizado");
        nuevoJuego();
    }

    private void rbtnExpertoMouseReleased(MouseEvent ignoredEvt) {
        rbtnCustom.setText("Personalizado");
        nuevoJuego();
    }

    private void rbtnCustomMouseReleased(MouseEvent ignoredEvt) {

        txtFieldFilas = new JTextField();
        txtFieldColumnas = new JTextField();
        txtFieldMinas = new JTextField();

        Object[] campos = {"Filas: ", txtFieldFilas, "Columnas: ", txtFieldColumnas, "Minas: ", txtFieldMinas};

        int opcion = JOptionPane.showConfirmDialog(rbtnCustom, campos, "Ingrese los datos", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            nuevoJuego();
            int numFilas = bm.getTablero().length;
            int numColumnas = bm.getTablero()[0].length;
            rbtnCustom.setText("Personalizado: " + numFilas + (numFilas > 9 ? "" : " ") + "         " + (numColumnas > 9 ? numColumnas : numColumnas + " ") + "             " + bm.getCANTIDAD_MINAS());
        } else {
            rbtnPrincipiante.setSelected(this.principiante);
            rbtnIntermedio.setSelected(this.intermedio);
            rbtnExperto.setSelected(this.experto);
            rbtnCustom.setSelected(this.custom);
        }
    }

    private void menuNewGameMouseReleased(MouseEvent ignoredEvt) {
        nuevoJuego();
    }

    private void rbtnCustomMousePressed(MouseEvent ignoredEvt) {
        this.principiante = rbtnPrincipiante.isSelected();
        this.intermedio = rbtnIntermedio.isSelected();
        this.experto = rbtnExperto.isSelected();
        this.custom = rbtnCustom.isSelected();
    }

    private void btnMainMousePressed(MouseEvent ignoredEvt) {
        btnMain.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/cara/cara2.png"))));
    }

    private void btnMainMouseReleased(MouseEvent ignoredEvt) {
        btnMain.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/cara/cara1.png"))));
        nuevoJuego();
    }

    private void casillaPressed(MouseEvent evt) {
        int[] casilla = new int[]{(evt.getComponent().getY() - 104) / 32, (evt.getComponent().getX() - 20) / 32};
        if (!bm.esFinPartida()) {
            if ((evt.getModifiersEx() == (MouseEvent.BUTTON1_DOWN_MASK | MouseEvent.BUTTON3_DOWN_MASK)) || (SwingUtilities.isMiddleMouseButton(evt))) {
                bothMousePressed = true;
                for (int i = Math.max(0, casilla[0] - 1); i < Math.min(casilla[0] + 2, tablero.length); i++) {
                    for (int j = Math.max(0, casilla[1] - 1); j < Math.min(casilla[1] + 2, tablero[i].length); j++) {
                        if (!bm.tieneBandera(new int[]{i, j}) && !bm.esVisible(new int[]{i, j})) {
                            tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/0.png"))));
                        }
                    }
                }
                repaint();
            } else if (SwingUtilities.isLeftMouseButton(evt) && (!bm.tieneBandera(casilla) && !bm.esVisible(casilla))) {
                tablero[(evt.getComponent().getY() - 104) / 32][(evt.getComponent().getX() - 20) / 32].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/0.png"))));
                btnMain.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/cara/cara3.png"))));
            }
        }
    }

    private void casillaReleased(MouseEvent evt) {

        int[] casilla = new int[]{(evt.getComponent().getY() - 104) / 32, (evt.getComponent().getX() - 20) / 32};
        if (!bm.esFinPartida()) {
            btnMain.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/cara/cara1.png"))));
            tablero[casilla[0]][casilla[1]].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/casilla.png"))));

            if (bothMousePressed && bm.partidaEmpezada()) {
                if (!bm.tieneBandera(casilla)) {
                    bothMousePressed = false;
                    for (int i = Math.max(0, casilla[0] - 1); i < Math.min(casilla[0] + 2, tablero.length); i++) {
                        for (int j = Math.max(0, casilla[1] - 1); j < Math.min(casilla[1] + 2, tablero[i].length); j++) {
                            if (!bm.tieneBandera(new int[]{i, j}) && !bm.esVisible(new int[]{i, j})) {
                                tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/casilla.png"))));
                            }
                        }
                    }
                    repaint();
                    bm.hacerJugada(casilla);
                }
            } else if ((SwingUtilities.isLeftMouseButton(evt)) && (!bm.tieneBandera(casilla)) && (!bm.esVisible(casilla))) {
                if (!bm.partidaEmpezada()) {
                    bm.primeraJugada(casilla);
                    this.cronoIsRunning = true;
                    thread.start();
                } else {
                    bm.hacerJugada(casilla);
                }
            } else if (SwingUtilities.isRightMouseButton(evt) && !bm.esVisible(casilla)) {
                numBanderas = (bm.tieneBandera(casilla) ? numBanderas - 1 : numBanderas + 1);
                bm.cambiarBandera(casilla);
            }
        }
        contadorMinas();
        actualizarCara();
        actualizarTablero();
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        for (int i = 0; i < 10 && cronoIsRunning && !bm.esFinPartida(); i++) {
            imgTimer0.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/contador/" + i + ".png"))));
            for (int j = 0; j < 10 && cronoIsRunning && !bm.esFinPartida(); j++) {
                imgTimer1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/contador/" + j + ".png"))));
                for (int k = 0; k < 10 && cronoIsRunning && !bm.esFinPartida(); k++) {
                    if (i == 0 && j == 0 && k == 0) {
                        k++;
                    }
                    imgTimer2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/contador/" + k + ".png"))));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }

    private void nuevoJuego() {
        thread.interrupt();
        this.cronoIsRunning = false;
        thread = new Thread(this);
        limpiarPantalla();
        this.numBanderas = 0;
        bm = new Buscaminas(getParms());
        initOtherComponents();
        inicializarTablero();
        reiniciarCronometro();
        actualizarTablero();
        repaint();
    }

    private void reiniciarCronometro() {
        imgTimer0.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/contador/0.png"))));
        imgTimer1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/contador/0.png"))));
        imgTimer2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/contador/0.png"))));
    }

    private int[] getParms() {
        if (rbtnPrincipiante.isSelected()) {
            return new int[]{9, 9, 10};
        } else if (rbtnIntermedio.isSelected()) {
            return new int[]{16, 16, 40};
        } else if (rbtnExperto.isSelected()) {
            return new int[]{16, 30, 99};
        } else {
            int filas, columnas, minas;

            try {
                filas = Integer.parseInt(txtFieldFilas.getText());
            } catch (NumberFormatException ex) {
                filas = 16;
            }

            try {
                columnas = Integer.parseInt(txtFieldColumnas.getText());
            } catch (NumberFormatException ex) {
                columnas = 16;
            }

            try {
                minas = Integer.parseInt(txtFieldMinas.getText());
            } catch (NumberFormatException ex) {
                minas = 40;
            }

            return new int[]{filas, columnas, minas};
        }
    }

    private void limpiarPantalla() {
        imgTopLeft.setVisible(false);
        main.remove(imgTopLeft);
        imgTop.setVisible(false);
        main.remove(imgTop);
        imgTopRight.setVisible(false);
        main.remove(imgTopRight);
        imgLeft.setVisible(false);
        main.remove(imgLeft);
        imgRight.setVisible(false);
        main.remove(imgRight);
        imgBotLeft.setVisible(false);
        main.remove(imgBotLeft);
        imgBot.setVisible(false);
        main.remove(imgBot);
        imgBotRight.setVisible(false);
        main.remove(imgBotRight);

        imgContMinas0.setVisible(false);
        main.remove(imgContMinas0);
        imgContMinas1.setVisible(false);
        main.remove(imgContMinas1);
        imgContMinas2.setVisible(false);
        main.remove(imgContMinas2);

        btnMain.setVisible(false);
        main.remove(btnMain);

        imgTimer0.setVisible(false);
        main.remove(imgTimer0);
        imgTimer1.setVisible(false);
        main.remove(imgTimer1);
        imgTimer2.setVisible(false);
        main.remove(imgTimer2);

        limpiarTablero();
    }

    private void limpiarTablero() {
        for (JLabel[] fila : tablero) {
            for (JLabel casilla : fila) {
                casilla.setVisible(false);
                main.remove(casilla);
            }
        }
    }

    private void inicializarTablero() {
        contadorMinas();

        char[][] tableroChar = bm.getTablero();

        int filas = tableroChar.length;
        int columnas = tableroChar[0].length;

        tablero = new JLabel[filas][columnas];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = new JLabel();
                tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/casilla.png"))));
                main.add(tablero[i][j], new AbsoluteConstraints((20 + 32 * j), (104 + 32 * i), 32, 32));
                tablero[i][j].setBorder(null);
                tablero[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent evt) {
                        casillaPressed(evt);
                    }

                    @Override
                    public void mouseReleased(MouseEvent evt) {
                        casillaReleased(evt);
                    }
                });
            }
        }
        repaint();
    }

    private void actualizarTablero() {
        char[][] tableroChars = bm.getTablero();

        for (int i = 0; i < tableroChars.length; i++) {
            for (int j = 0; j < tableroChars[0].length; j++) {
                switch (tableroChars[i][j]) {
                    case 'F' ->
                            tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/bandera.png"))));
                    case '#' ->
                            tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/minaExplotada.png"))));
                    case '*' ->
                            tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/mina.png"))));
                    case 'X' ->
                            tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/notMina.png"))));
                    case ' ' ->
                            tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/0.png"))));
                    case '_' ->
                            tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/casilla.png"))));
                    default ->
                            tablero[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/casilla/" + tableroChars[i][j] + ".png"))));
                }
            }
        }
        repaint();
    }

    private void actualizarCara() {
        if (bm.esFinPartida()) {
            if (bm.esVictoria()) {
                btnMain.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/cara/cara5.png"))));
            } else {
                btnMain.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/cara/cara4.png"))));
            }
        }
    }

    private void contadorMinas() {
        int numMinas = bm.getCANTIDAD_MINAS() - numBanderas;
        String contadorMinas;
        if (bm.esVictoria()) {
            contadorMinas = "000";
        } else if (numMinas > 999) {
            contadorMinas = "999";
        } else {
            contadorMinas = String.format("%03d", numMinas);
        }

        imgContMinas0.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/contador/" + contadorMinas.charAt(contadorMinas.length() - 3) + ".png"))));
        imgContMinas1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/contador/" + contadorMinas.charAt(contadorMinas.length() - 2) + ".png"))));
        imgContMinas2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/images/contador/" + contadorMinas.charAt(contadorMinas.length() - 1) + ".png"))));

    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(InterfaceGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new InterfaceGUI().setVisible(true));
    }
}
