package UI;

import Model.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WestPanelManager {
    private Store store;
    private EmployeeManagement management;
    private EastPanelManager eastPanelManager;
    private JPanel westPanel;
    private CardLayout westLayout;

    public WestPanelManager(Store store, EmployeeManagement management) {
        this.store = store;
        this.management = management;
        this.westLayout = new CardLayout();
        this.westPanel = new JPanel(westLayout);
        westPanel.setPreferredSize(new Dimension(300, 600));
        management.add(westPanel, BorderLayout.WEST);
        initPanels();
        westLayout.show(westPanel, "Main Menu Panel");
    }

    private void initPanels() {
        initMainMenu();
    }

    public void show(String s) {
        westLayout.show(westPanel, s);
    }

    private void initMainMenu() {
        MainMenu mainMenu = new MainMenu();
        addPanel(mainMenu, "Main Menu Panel");
    }

    public void addPanel(JPanel responsibilityViewPanel, String r_view_panel) {
        westPanel.add(responsibilityViewPanel, r_view_panel);
    }

    private class MainMenu extends JPanel {

        public MainMenu() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.black);
            initPanel();
        }

        private void initPanel() {
            initStoreImage();
            initEmployeeButton();
            initOpenStore();
            initCloseStore();
            initIncHour();
            initAddEarnings();
            initLogOff();
        }

        private void initStoreImage() {
            ImageIcon image = new ImageIcon("./Data/Store.jpg");
            JLabel imagePanel = new JLabel();
            imagePanel.setIcon(image);
            imagePanel.setPreferredSize(new Dimension(273, 230));
            imagePanel.setIconTextGap(10);
            add(imagePanel);
        }

        private void initLogOff() {
            JButton logOff = new JButton("Log Off");
            add(logOff);
            logOff.setPreferredSize(new Dimension(280,40));
            logOff.setFont(new Font("Arial", Font.BOLD, 20));
            logOff.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    management.saveStore();
                    management.dispose();
                }
            });
        }

        private void initAddEarnings() {
            JButton addEarnings = new JButton("Add Daily Store Earnings");
            add(addEarnings);
            addEarnings.setPreferredSize(new Dimension(280,40));
            addEarnings.setFont(new Font("Arial", Font.BOLD, 20));
            addEarnings.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Earning Panel");
                }
            });
        }

        private void initIncHour() {
            JButton incHour = new JButton("Increment Hour");
            add(incHour);
            incHour.setPreferredSize(new Dimension(280,40));
            incHour.setFont(new Font("Arial", Font.BOLD, 20));
            incHour.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    store.incCurrHour();
                    initPanels();
                    eastPanelManager.initPanels();
                    eastPanelManager.show("Main Panel");
                }
            });
        }

        private void initCloseStore() {
            JButton closeButton = new JButton("Close Store");
            add(closeButton);
            closeButton.setPreferredSize(new Dimension(280,40));
            closeButton.setFont(new Font("Arial", Font.BOLD, 20));
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    store.endDay();
                    initPanels();
                    eastPanelManager.initPanels();
                    eastPanelManager.show("Main Panel");
                }
            });
        }

        private void initOpenStore() {
            JButton openButton = new JButton("Open Store");
            add(openButton);
            openButton.setPreferredSize(new Dimension(280,40));
            openButton.setFont(new Font("Arial", Font.BOLD, 20));
            openButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    store.startDay();
                    initPanels();
                    eastPanelManager.initPanels();
                    eastPanelManager.show("Main Panel");
                }
            });
        }

        private void initEmployeeButton() {
            JButton employeesButton = new JButton("Employees");
            add(employeesButton);
            employeesButton.setPreferredSize(new Dimension(280,40));
            employeesButton.setFont(new Font("Arial", Font.BOLD, 20));
            employeesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastPanelManager.show("Employee Menu");
                }
            });
        }
    }

    public EastPanelManager getEastPanelManager() {
        return eastPanelManager;
    }

    public void setEastPanelManager(EastPanelManager eastPanelManager) {
        this.eastPanelManager = eastPanelManager;
    }
}
