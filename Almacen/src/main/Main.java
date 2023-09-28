package main;

import core.Core;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JFrame {
    private final Font FONT;
    private Core core;
    private JPanel main;
    private JLabel[] productos;

    private Main() {
        try {
            this.core = new Core();
        } catch (Exception e) {
            this.showErrorMessage(e, true);
        }

        this.FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        initComponents();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    private void initComponents() {
        JLabel btnTraer = new JLabel();
        JLabel btnCamion = new JLabel();
        JLabel btnConfig = new JLabel();
        JLabel var_bg = new JLabel();

        setTitle("Almacén");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(850, 650));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new AbsoluteLayout());
        main.setLayout(new AbsoluteLayout());
        getContentPane().add(main, new AbsoluteConstraints(0, 0));
        getContentPane().setComponentZOrder(main, 0);
        main.setBackground(new Color(228, 233, 247));

        var_bg.setBackground(new Color(29, 21, 96));
        var_bg.setOpaque(true);
        main.add(var_bg, new AbsoluteConstraints(0, 462, 850, 150));

        try {
            this.productos = new JLabel[core.getInventory().size()];
            int rows = productos.length / 3 + productos.length % 3;
            int cols = 3;

            JPanel panel = new JPanel(new GridLayout(rows, cols));
            panel.setOpaque(true);
            panel.setBackground(new Color(228, 233, 247));

            for (int i = 0; i < productos.length; i++) {
                productos[i] = new JLabel();
                productos[i].setFont(this.FONT);
                panel.setComponentZOrder(productos[i], 0);
            }
            main.add(panel, new AbsoluteConstraints(15, 0, 850, 463));
            main.setComponentZOrder(panel, 1);

        } catch (Exception e) {
            showErrorMessage(e, true);
        }

        cargarInventario();

        btnTraer.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/carro.png"))));
        main.add(btnTraer, new AbsoluteConstraints(15, 475, 128, 128));
        btnTraer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                traer();
            }
        });

        btnCamion.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/camion.png"))));
        main.add(btnCamion, new AbsoluteConstraints(175, 475, 128, 128));
        btnCamion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                anhadir();
            }
        });

        btnConfig.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/config.png"))));
        main.add(btnConfig, new AbsoluteConstraints(700, 482, 128, 128));
        btnConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                config();
            }
        });

        main.setComponentZOrder(btnConfig, 0);
        main.setComponentZOrder(btnCamion, 0);
        main.setComponentZOrder(btnTraer, 0);
        main.setComponentZOrder(var_bg, 3);
    }

    private void cargarInventario() {
        Map<String, Integer> inventory;

        try {
            inventory = core.getInventory();
            int size = inventory.size();

            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(inventory.entrySet());
            Map<String, Integer> limites = core.getLimites();
            for (int i = 0; i < size; i++) {
                Map.Entry<String, Integer> entry = entryList.get(size - 1 - i);
                productos[i].setText("   " + entry.getKey() + ": " + entry.getValue() + " uds");
                setWarning(productos[i], (entry.getValue() <= limites.getOrDefault(entry.getKey(), -1)));
            }

        } catch (Exception e) {
            this.showErrorMessage(e, true);
        }
    }

    private void traer() {
        try {
            Map<String, Integer> inventory = core.getInventory();
            int rows = inventory.size();
            JPanel panel = new JPanel(new GridLayout(rows, 2));

            Map<String, JSpinner> spinnerMap = new HashMap<>();

            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                JLabel label = new JLabel(entry.getKey() + " (" + entry.getValue() + " uds)");
                label.setFont(this.FONT);

                JSpinner spinner = new JSpinner();
                spinner.setFont(this.FONT);
                spinner.addChangeListener(e -> {
                    if ((int)spinner.getValue() < 0) {
                        spinner.setValue(0);
                    } else if ((int)spinner.getValue() > entry.getValue()) {
                        spinner.setValue(entry.getValue());
                    }
                });
                // TODO: adsasdasd

                spinner.getEditor().setPreferredSize(new Dimension(0, 50));

                panel.add(label);
                panel.add(spinner);

                spinnerMap.put(entry.getKey(), spinner);
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(450, 350));

            int option = JOptionPane.showOptionDialog(null, scrollPane, "Traer del almacén", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_OPTION) {
                Map<String, Integer> newInventory = new HashMap<>();
                for (Map.Entry<String, JSpinner> entry : spinnerMap.entrySet()) {
                    if ((int) entry.getValue().getValue() > 0) {
                        newInventory.put(entry.getKey(), (int) entry.getValue().getValue());
                    }
                    if ((int) entry.getValue().getValue() > inventory.get(entry.getKey())) {
                        showErrorMessage(new Exception("No hay suficiente stock de " + entry.getKey()), false);
                    }
                }
                core.traer(newInventory);
            }

        } catch (Exception e) {
            showErrorMessage(e, true);
        }
        cargarInventario();
    }

    private void anhadir() {
        try {
            Map<String, Integer> inventory = core.getInventory();
            int rows = inventory.size();
            JPanel panel = new JPanel(new GridLayout(rows, 2));

            Map<String, JSpinner> spinnerMap = new HashMap<>();

            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                JLabel label = new JLabel(entry.getKey());
                label.setFont(this.FONT);

                JSpinner spinner = new JSpinner();
                spinner.setFont(this.FONT);

                spinner.getEditor().setPreferredSize(new Dimension(0, 50));

                panel.add(label);
                panel.add(spinner);

                spinnerMap.put(entry.getKey(), spinner);
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(450, 350));

            int option = JOptionPane.showOptionDialog(null, scrollPane, "Añadir al almacén", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_OPTION) {
                Map<String, Integer> newInventory = new HashMap<>();
                for (Map.Entry<String, JSpinner> entry : spinnerMap.entrySet()) {
                    if ((int) entry.getValue().getValue() > 0) {
                        newInventory.put(entry.getKey(), (int) entry.getValue().getValue());
                    }
                }
                if (newInventory.containsKey("Agua grande")) {
                    newInventory.put("Agua grande", newInventory.get("Agua grande") * 84);
                }
                core.anhadir(newInventory);
            }

        } catch (Exception e) {
            showErrorMessage(e, true);
        }
        cargarInventario();
    }

    private void config() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JCheckBox checkBox1 = new JCheckBox();
        checkBox1.setText("Editar inventario");
        JCheckBox checkBox2 = new JCheckBox();
        checkBox2.setText("Ajustar límites");
        panel.add(checkBox1);
        panel.add(checkBox2);

        int option = JOptionPane.showOptionDialog(null, panel, "Configuración", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (option != JOptionPane.OK_OPTION) {
            return;
        }

        if (checkBox1.isSelected()) {
            configInventario();
        }

        if (checkBox2.isSelected()) {
            configLimites();
        }
    }

    private void configInventario() {
        try {
            Map<String, Integer> inventory = core.getInventory();
            int rows = inventory.size();
            JPanel panel = new JPanel(new GridLayout(rows, 2));

            Map<String, JSpinner> spinnerMap = new HashMap<>();

            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                JLabel label = new JLabel(entry.getKey() + " (" + entry.getValue() + " uds)");
                label.setFont(this.FONT);

                JSpinner spinner = new JSpinner();
                spinner.setValue(entry.getValue());
                spinner.setFont(this.FONT);

                spinner.getEditor().setPreferredSize(new Dimension(0, 50));

                panel.add(label);
                panel.add(spinner);

                spinnerMap.put(entry.getKey(), spinner);
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(450, 350));

            int option = JOptionPane.showOptionDialog(null, scrollPane, "Ajustar inventario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_OPTION) {
                Map<String, Integer> newInventory = new HashMap<>();
                for (Map.Entry<String, JSpinner> entry : spinnerMap.entrySet()) {
                    if (inventory.get(entry.getKey()) != (int) entry.getValue().getValue()) {
                        newInventory.put(entry.getKey(), (int) entry.getValue().getValue());
                    }
                }
                core.updateInventory(newInventory);
            }

        } catch (Exception e) {
            showErrorMessage(e, true);
        }
        cargarInventario();
    }

    private void configLimites() {
        try {
            Map<String, Integer> inventory = core.getInventory();
            int rows = inventory.size();
            JPanel panel = new JPanel(new GridLayout(rows, 2));

            Map<String, JSpinner> spinnerMap = new HashMap<>();
            Map<String, Integer> limites = core.getLimites();

            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                JLabel label = new JLabel(entry.getKey());
                label.setFont(this.FONT);

                JSpinner spinner = new JSpinner();
                spinner.setValue(limites.get(entry.getKey()));
                spinner.setFont(this.FONT);

                spinner.getEditor().setPreferredSize(new Dimension(0, 50));

                panel.add(label);
                panel.add(spinner);

                spinnerMap.put(entry.getKey(), spinner);
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(450, 350));

            int option = JOptionPane.showOptionDialog(null, scrollPane, "Ajustar límites", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_OPTION) {
                Map<String, Integer> newLimits = new HashMap<>();
                for (Map.Entry<String, JSpinner> entry : spinnerMap.entrySet()) {
                    if (limites.get(entry.getKey()) != (int) entry.getValue().getValue()) {
                        newLimits.put(entry.getKey(), (int) entry.getValue().getValue());
                    }
                }
                core.setLimites(newLimits);
            }

        } catch (Exception e) {
            showErrorMessage(e, true);
        }
        cargarInventario();
    }

    private void setWarning(JLabel label, boolean warning) {
        label.setIcon(warning ? new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/wrng.png"))) : null);
    }

    private void showErrorMessage(Exception e, boolean closeApp) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        if (closeApp) {
            System.exit(1);
        }
    }
}
