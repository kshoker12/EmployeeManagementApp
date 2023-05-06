package UI;

import Model.Store;
import Persistence.StoreReader;
import Persistence.StoreWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EmployeeManagement extends JFrame {
    private static final String Store_JSON = "./Data/store.json";
    private Store store;
    private EastPanelManager eastPanelManager;
    private WestPanelManager westPanelManager;
    private StoreWriter storeWriter;
    private StoreReader storeReader;

    public EmployeeManagement() {
        super("Employee Management App");
//        store = new Store("Coast Meridian Hardware", 8, 20);
        storeReader = new StoreReader(Store_JSON);
        storeWriter = new StoreWriter(Store_JSON);
        store = loadStore();
//        initStore();
        setupPanel();
        initPanels();
    }

//    private void initStore() {
//        Employee e1 = new Employee("James Bond", 15, 0);
//        e1.addAvailability(new Availability(0, 6, 8));
//        e1.addAvailability(new Availability(1, 6, 8));
//        e1.addAvailability(new Availability(2, 6, 14));
//        e1.addAvailability(new Availability(4, 6, 14));
//        e1.addAvailability(new Availability(5, 12, 8));
//
//        Employee e2 = new Employee("Batman", 14, 0);
//        e2.addAvailability(new Availability(0, 6, 8));
//        e2.addAvailability(new Availability(2, 6, 8));
//        e2.addAvailability(new Availability(4, 6, 14));
//        e2.addAvailability(new Availability(5, 6, 8));
//
//        Employee e3 = new Employee("Superman", 13, 0);
//        e3.addAvailability(new Availability(0, 6, 8));
//        e3.addAvailability(new Availability(1, 6, 8));
//        e3.addAvailability(new Availability(3, 12, 8));
//        e3.addAvailability(new Availability(4, 6, 8));
//        e3.addAvailability(new Availability(6, 6, 8));
//
//        Employee e4 = new Employee("Wonder Woman", 16, 1);
//        e4.addAvailability(new Availability(0, 6, 14));
//        e4.addAvailability(new Availability(1, 6, 14));
//        e4.addAvailability(new Availability(2, 6, 14));
//        e4.addAvailability(new Availability(4, 6, 8));
//        e4.addAvailability(new Availability(5, 6, 14));
//
//        Employee e5 = new Employee("Super Woman", 15, 1);
//        e5.addAvailability(new Availability(0, 6, 14));
//        e5.addAvailability(new Availability(2, 6, 8));
//        e5.addAvailability(new Availability(3, 6, 14));
//        e5.addAvailability(new Availability(6, 12, 8));
//
//        Employee e6 = new Employee("Cat Woman", 14, 1);
//        e6.addAvailability(new Availability(0, 6, 14));
//        e6.addAvailability(new Availability(1, 6, 14));
//        e6.addAvailability(new Availability(3, 6, 8));
//        e6.addAvailability(new Availability(6, 6, 14));
//
//        store.hireEmployee(e1);
//        store.hireEmployee(e2);
//        store.hireEmployee(e3);
//        store.hireEmployee(e4);
//        store.hireEmployee(e5);
//        store.hireEmployee(e6);
//    }

    public void initPanels() {
        eastPanelManager = new EastPanelManager(store, this);
        westPanelManager = new WestPanelManager(store, this);
        eastPanelManager.setWestPanelManager(westPanelManager);
        westPanelManager.setEastPanelManager(eastPanelManager);
    }

    private Store loadStore() {
        Store store = null;
        try {
            store = storeReader.read();
            System.out.println("Loaded " + store.getName() + " from " + Store_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + Store_JSON);
        }
        return store;
    }

    private void setupPanel() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(615, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout(10, 10));
    }

    public void saveStore() {
        try {
            storeWriter.open();
            storeWriter.write(store);
            storeWriter.close();
            System.out.println("Saved " + store.getName() + " to " + Store_JSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + Store_JSON);
        }
    }

    public void dispose() {
        super.dispose();
    }

}
