package UI;

import javax.swing.*;

public class EmployeeManagementApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                EmployeeManagement main = new EmployeeManagement();
                main.setVisible(true);
            }
        });
    }

}
