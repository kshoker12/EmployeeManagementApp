package UI;

import Model.Availability;
import Model.Employee;
import Model.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EastPanelManager {
    private Store store;
    private EmployeeManagement management;
    private WestPanelManager westPanelManager;
    private JPanel eastPanel;
    private CardLayout eastLayout;

    public EastPanelManager(Store store, EmployeeManagement management) {
        this.store = store;
        this.management = management;
        eastLayout = new CardLayout();
        this.westPanelManager = westPanelManager;
        eastPanel = new JPanel(eastLayout);
        eastPanel.setPreferredSize(new Dimension(300, 600));
        management.add(eastPanel, BorderLayout.EAST);
        initPanels();
        eastLayout.show(eastPanel, "Main Panel");
    }

    public WestPanelManager getWestPanelManager() {
        return westPanelManager;
    }

    public void setWestPanelManager(WestPanelManager westPanelManager) {
        this.westPanelManager = westPanelManager;
    }

    public void initPanels() {
        initMainPanel();
        initEarningsPanel();
        initEmployeesMenu();
        initViewEmployeeMenu();
        initFireEmployee();
        initHireEmployee();
    }

    private void initHireEmployee() {
        HireEmployeePanel hireEmployeePanel = new HireEmployeePanel();
        addPanel(hireEmployeePanel, "Hire Employee Panel");
    }

    private void initFireEmployee() {
        FireEmployeePanel fireEmployeePanel = new FireEmployeePanel();
        addPanel(fireEmployeePanel, "Fire Employee Panel");
    }

    private void initViewEmployeeMenu() {
        ViewEmployeeMenu viewEmployeeMenu = new ViewEmployeeMenu();
        addPanel(viewEmployeeMenu, "View Employee Panel");
    }

    private void initEmployeesMenu() {
        EmployeeMenu employeeMenu = new EmployeeMenu();
        addPanel(employeeMenu, "Employee Menu");
    }

    private void initEarningsPanel() {
        EarningPanel earningPanel = new EarningPanel();
        addPanel(earningPanel, "Earning Panel");
    }

    public void show(String s) {
        eastLayout.show(eastPanel, s);
    }
    private void initMainPanel() {
        MainPanel mainPanel = new MainPanel();
        addPanel(mainPanel, "Main Panel");
    }

    public void addPanel(JPanel responsibilityViewPanel, String r_view_panel) {
        eastPanel.add(responsibilityViewPanel, r_view_panel);
    }

    private class MainPanel extends JPanel {

        public MainPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.red);
            initPanel();
        }

        private void initPanel() {
            initStoreLabel();
            initToolsImage();
            initTotalEarnings();
            initWeeklyEarnings();
            initCurrTime();
            initCurrEmployees();
        }



        private void initCurrEmployees() {
            JLabel activeEmployeeLabel = new JLabel("Currently Working");
            activeEmployeeLabel.setBackground(Color.darkGray);
            activeEmployeeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            activeEmployeeLabel.setPreferredSize(new Dimension(276, 25));
            activeEmployeeLabel.setForeground(Color.white);
            add(activeEmployeeLabel);
            for (Employee e: store.getActiveEmployees()) {
                int startTime = 0;
                int endTime = 0;
                String ap = "";
                String ap2 = "";
                for (Availability avail: e.getAvailability()) {
                    if (avail.getDay() == store.getCurrDay()) {
                        startTime = avail.getStart();
                        endTime = startTime + avail.getHours();
                    }
                }
                if (startTime > 12) {
                    startTime = startTime - 12;
                    ap = "pm";
                } else if (startTime == 12) {
                    ap = "pm";
                } else {
                    ap = "am";
                }
                if (endTime > 12) {
                    endTime = endTime - 12;
                    ap2 = "pm";
                } else if (endTime == 12) {
                    ap2 = "pm";
                } else {
                    ap2 = "am";
                }
                JLabel employeeLabel = new JLabel(e.getName() + ": " + startTime + ":00 " + ap + " - " + endTime + ":00 " + ap2);
                employeeLabel.setBackground(Color.darkGray);
                employeeLabel.setFont(new Font("Arial", Font.BOLD, 16));
                employeeLabel.setPreferredSize(new Dimension(276, 25));
                employeeLabel.setForeground(Color.white);
                add(employeeLabel);
            }
        }

        private void initCurrTime() {
            String currDay = "";
            String ap;
            int currTime = store.getCurrHour();
            switch(store.getCurrDay()) {
                case 0:
                    currDay = "Monday";
                    break;
                case 1:
                    currDay = "Tuesday";
                    break;
                case 2:
                    currDay = "Wednesday";
                    break;
                case 3:
                    currDay = "Thursday";
                    break;
                case 4:
                    currDay = "Friday";
                    break;
                case 5:
                    currDay = "Saturday";
                    break;
                case 6:
                    currDay = "Sunday";
                    break;
            }
            if (currTime > 12) {
                currTime = currTime - 12;
                ap = "pm";
            } else if (currTime == 12) {
                ap = "pm";
            } else {
                ap = "am";
            }
            JTextField timeLabel = new JTextField(currDay + " - " + currTime + ":00 " + ap);
            timeLabel.setBackground(Color.red);
            timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
            timeLabel.setPreferredSize(new Dimension(285, 30));
            timeLabel.setForeground(Color.white);
            timeLabel.setEditable(false);
            timeLabel.setHorizontalAlignment(JTextField.CENTER);
            add(timeLabel);

        }

        private void initWeeklyEarnings() {
            JTextField weeklyEarnings = new JTextField("Weekly Earnings: $" + store.getWeeklyEarnings());
            weeklyEarnings.setBackground(Color.red);
            weeklyEarnings.setFont(new Font("Arial", Font.BOLD, 20));
            weeklyEarnings.setPreferredSize(new Dimension(285, 30));
            weeklyEarnings.setForeground(Color.white);
            weeklyEarnings.setEditable(false);
            weeklyEarnings.setHorizontalAlignment(JTextField.CENTER);
            add(weeklyEarnings);
        }

        private void initTotalEarnings() {
            JTextField totalEarnings = new JTextField("Total Earnings: $" + store.getTotalEarnings());
            totalEarnings.setBackground(Color.red);
            totalEarnings.setFont(new Font("Arial", Font.BOLD, 20));
            totalEarnings.setPreferredSize(new Dimension(285, 30));
            totalEarnings.setForeground(Color.white);
            totalEarnings.setEditable(false);
            totalEarnings.setHorizontalAlignment(JTextField.CENTER);
            add(totalEarnings);
        }

        private void initToolsImage() {
            ImageIcon image = new ImageIcon("./Data/tools.jpg");
            JLabel imagePanel = new JLabel();
            imagePanel.setIcon(image);
            imagePanel.setPreferredSize(new Dimension(268, 250));
            imagePanel.setIconTextGap(10);
            add(imagePanel);
        }

        private void initStoreLabel() {
            JTextField storeName = new JTextField(store.getName());
            storeName.setBackground(Color.red);
            storeName.setFont(new Font("Arial", Font.BOLD, 20));
            storeName.setPreferredSize(new Dimension(285, 50));
            storeName.setForeground(Color.white);
            storeName.setEditable(false);
            storeName.setHorizontalAlignment(JTextField.CENTER);
            add(storeName);
        }
    }

    private class EarningPanel extends JPanel {
        JTextField earningTextField;

        public EarningPanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.red);
            initPanel();
        }

        private void initPanel() {
            initStoreLabel();
            initEarningTextField();
            initTotalEarnings();
            initWeeklyEarnings();
            initCurrTime();
            initCurrEmployees();
        }

        private void initEarningTextField() {
            JLabel earningLabel = new JLabel("Enter Earning Amount ($)");
            earningLabel.setBackground(Color.darkGray);
            earningLabel.setFont(new Font("Arial", Font.BOLD, 16));
            earningLabel.setPreferredSize(new Dimension(276, 25));
            earningLabel.setForeground(Color.white);
            add(earningLabel);

            earningTextField = new JTextField();
            earningTextField.setBackground(Color.white);
            earningTextField.setFont(new Font("Arial", Font.BOLD, 20));
            earningTextField.setPreferredSize(new Dimension(285, 30));
            earningTextField.setForeground(Color.black);
            earningTextField.setEditable(true);
            earningTextField.setHorizontalAlignment(JTextField.LEFT);
            add(earningTextField);

            JButton confirm = new JButton("Confirm");
            add(confirm);
            confirm.setPreferredSize(new Dimension(280,40));
            confirm.setFont(new Font("Arial", Font.BOLD, 20));
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = earningTextField.getText();
                    int i = Integer.parseInt(text);
                    earningTextField.setText("");
                    store.addEarnings(i);
                    initPanels();
                    eastLayout.show(eastPanel, "Main Panel");
                }
            });

            JButton back = new JButton("Cancel");
            add(back);
            back.setPreferredSize(new Dimension(280,40));
            back.setFont(new Font("Arial", Font.BOLD, 20));
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    earningTextField.setText("");
                    eastLayout.show(eastPanel, "Main Panel");
                }
            });


        }

        private void initStoreLabel() {
            JTextField storeName = new JTextField(store.getName());
            storeName.setBackground(Color.red);
            storeName.setFont(new Font("Arial", Font.BOLD, 20));
            storeName.setPreferredSize(new Dimension(285, 50));
            storeName.setForeground(Color.white);
            storeName.setEditable(false);
            storeName.setHorizontalAlignment(JTextField.CENTER);
            add(storeName);
        }

        private void initTotalEarnings() {
            JTextField totalEarnings = new JTextField("Total Earnings: $" + store.getTotalEarnings());
            totalEarnings.setBackground(Color.red);
            totalEarnings.setFont(new Font("Arial", Font.BOLD, 20));
            totalEarnings.setPreferredSize(new Dimension(285, 30));
            totalEarnings.setForeground(Color.white);
            totalEarnings.setEditable(false);
            totalEarnings.setHorizontalAlignment(JTextField.CENTER);
            add(totalEarnings);
        }

        private void initWeeklyEarnings() {
            JTextField weeklyEarnings = new JTextField("Weekly Earnings: $" + store.getWeeklyEarnings());
            weeklyEarnings.setBackground(Color.red);
            weeklyEarnings.setFont(new Font("Arial", Font.BOLD, 20));
            weeklyEarnings.setPreferredSize(new Dimension(285, 30));
            weeklyEarnings.setForeground(Color.white);
            weeklyEarnings.setEditable(false);
            weeklyEarnings.setHorizontalAlignment(JTextField.CENTER);
            add(weeklyEarnings);
        }

        private void initCurrTime() {
            String currDay = "";
            String ap;
            int currTime = store.getCurrHour();
            switch(store.getCurrDay()) {
                case 0:
                    currDay = "Monday";
                    break;
                case 1:
                    currDay = "Tuesday";
                    break;
                case 2:
                    currDay = "Wednesday";
                    break;
                case 3:
                    currDay = "Thursday";
                    break;
                case 4:
                    currDay = "Friday";
                    break;
                case 5:
                    currDay = "Saturday";
                    break;
                case 6:
                    currDay = "Sunday";
                    break;
            }
            if (currTime > 12) {
                currTime = currTime - 12;
                ap = "pm";
            } else if (currTime == 12) {
                ap = "pm";
            } else {
                ap = "am";
            }
            JTextField timeLabel = new JTextField(currDay + " - " + currTime + ":00 " + ap);
            timeLabel.setBackground(Color.red);
            timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
            timeLabel.setPreferredSize(new Dimension(285, 30));
            timeLabel.setForeground(Color.white);
            timeLabel.setEditable(false);
            timeLabel.setHorizontalAlignment(JTextField.CENTER);
            add(timeLabel);

        }

        private void initCurrEmployees() {
            JLabel activeEmployeeLabel = new JLabel("Currently Working");
            activeEmployeeLabel.setBackground(Color.darkGray);
            activeEmployeeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            activeEmployeeLabel.setPreferredSize(new Dimension(276, 25));
            activeEmployeeLabel.setForeground(Color.white);
            add(activeEmployeeLabel);
            for (Employee e: store.getActiveEmployees()) {
                int startTime = 0;
                int endTime = 0;
                String ap = "";
                String ap2 = "";
                for (Availability avail: e.getAvailability()) {
                    if (avail.getDay() == store.getCurrDay()) {
                        startTime = avail.getStart();
                        endTime = startTime + avail.getHours();
                    }
                }
                if (startTime > 12) {
                    startTime = startTime - 12;
                    ap = "pm";
                } else if (startTime == 12) {
                    ap = "pm";
                } else {
                    ap = "am";
                }
                if (endTime > 12) {
                    endTime = endTime - 12;
                    ap2 = "pm";
                } else if (endTime == 12) {
                    ap2 = "pm";
                } else {
                    ap2 = "am";
                }
                JLabel employeeLabel = new JLabel(e.getName() + ": " + startTime + ":00 " + ap + " - " + endTime + ":00 " + ap2);
                employeeLabel.setBackground(Color.darkGray);
                employeeLabel.setFont(new Font("Arial", Font.BOLD, 16));
                employeeLabel.setPreferredSize(new Dimension(276, 25));
                employeeLabel.setForeground(Color.white);
                add(employeeLabel);
            }


        }
    }

    private class EmployeeMenu extends JPanel {

        public EmployeeMenu() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.red);
            initPanel();
        }

        private void initPanel() {
            initLabel();
            initViewEmployees();
            initHireEmployee();
            initFireEmployee();
            initBackButton();
        }

        private void initLabel() {
            JTextField employeeMenu = new JTextField("Employee Menu");
            employeeMenu.setBackground(Color.red);
            employeeMenu.setFont(new Font("Arial", Font.BOLD, 20));
            employeeMenu.setPreferredSize(new Dimension(285, 50));
            employeeMenu.setForeground(Color.white);
            employeeMenu.setEditable(false);
            employeeMenu.setHorizontalAlignment(JTextField.CENTER);
            add(employeeMenu);
        }

        private void initViewEmployees() {
            JButton viewEmployees = new JButton("View Employee List");
            add(viewEmployees);
            viewEmployees.setPreferredSize(new Dimension(280,40));
            viewEmployees.setFont(new Font("Arial", Font.BOLD, 20));
            viewEmployees.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastLayout.show(eastPanel, "View Employee Panel");
                }
            });
        }

        private void initHireEmployee() {
            JButton hireEmployee = new JButton("Hire Employee");
            add(hireEmployee);
            hireEmployee.setPreferredSize(new Dimension(280,40));
            hireEmployee.setFont(new Font("Arial", Font.BOLD, 20));
            hireEmployee.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastLayout.show(eastPanel, "Hire Employee Panel");
                }
            });
        }

        private void initFireEmployee() {
            JButton fireEmployee = new JButton("Fire Employee");
            add(fireEmployee);
            fireEmployee.setPreferredSize(new Dimension(280,40));
            fireEmployee.setFont(new Font("Arial", Font.BOLD, 20));
            fireEmployee.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastLayout.show(eastPanel, "Fire Employee Panel");
                }
            });
        }

        private void initBackButton() {
            JButton back = new JButton("Back");
            add(back);
            back.setPreferredSize(new Dimension(280,40));
            back.setFont(new Font("Arial", Font.BOLD, 20));
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastLayout.show(eastPanel, "Main Panel");
                }
            });
        }
    }

    private class ViewEmployeeMenu extends JPanel {

        public ViewEmployeeMenu() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.red);
            initPanel();
        }

        private void initPanel() {
            initLabel();
            initEmployees();
            initBack();
        }

        private void initEmployees() {
            for (Employee e1: store.getEmployeeList()) {
                EmployeePanel employeePanel = new EmployeePanel(e1);
                addPanel(employeePanel, e1.getName() + " Employee Panel");
                JButton employeeButton = new JButton(e1.getName());
                add(employeeButton);
                employeeButton.setPreferredSize(new Dimension(280,40));
                employeeButton.setFont(new Font("Arial", Font.BOLD, 20));
                employeeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastLayout.show(eastPanel, e1.getName() + " Employee Panel");
                    }
                });
            }
        }

        private void initLabel() {
            JTextField employeeMenu = new JTextField("Employees List");
            employeeMenu.setBackground(Color.red);
            employeeMenu.setFont(new Font("Arial", Font.BOLD, 20));
            employeeMenu.setPreferredSize(new Dimension(285, 50));
            employeeMenu.setForeground(Color.white);
            employeeMenu.setEditable(false);
            employeeMenu.setHorizontalAlignment(JTextField.CENTER);
            add(employeeMenu);
        }

        private void initBack() {
            JButton back = new JButton("Back");
            add(back);
            back.setPreferredSize(new Dimension(280,40));
            back.setFont(new Font("Arial", Font.BOLD, 20));
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastLayout.show(eastPanel, "Employee Menu");
                }
            });
        }

        private class EmployeePanel extends JPanel {
            private Employee employee;

            public EmployeePanel(Employee employee) {
                super(new GridLayout(10, 1, 10, 10));
                setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                setPreferredSize(new Dimension(300, 600));
                this.employee = employee;
                setBackground(Color.red);
                initPanel();
            }

            private void initPanel() {
                initNameLabel();
                initImage();
                initTotalEarningsLabel();
                initWeeklyEarnings();
                initHourlyWage();
                initHoursLabel();
                initEditHourlySalary();
                viewSchedule();
                editSchedule();
                initBack();
            }

            private void initImage() {
                ImageIcon image;
                if (employee.getGenderBiological() == 0) {
                    image = new ImageIcon("./Data/male.png");
                } else {
                    image = new ImageIcon("./Data/female.png");
                }
                JLabel imagePanel = new JLabel();
                imagePanel.setIcon(image);
                imagePanel.setPreferredSize(new Dimension(230, 160));
                imagePanel.setIconTextGap(10);
                add(imagePanel);
            }

            private void initEditHourlySalary() {
                JButton editHourlySalary = new JButton("Edit Hourly Salary");
                add(editHourlySalary);
                EditHourlySalaryPanel hourlySalaryPanel = new EditHourlySalaryPanel();
                addPanel(hourlySalaryPanel, employee.getName() + " Edit Hourly Salary");
                editHourlySalary.setPreferredSize(new Dimension(280,40));
                editHourlySalary.setFont(new Font("Arial", Font.BOLD, 20));
                editHourlySalary.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastLayout.show(eastPanel, employee.getName() + " Edit Hourly Salary");
                    }
                });
            }

            private void initHourlyWage() {
                JTextField hourlyWageLabel = new JTextField("Hourly Salary: $" + employee.getHourlySalary());
                hourlyWageLabel.setBackground(Color.red);
                hourlyWageLabel.setFont(new Font("Arial", Font.BOLD, 18));
                hourlyWageLabel.setPreferredSize(new Dimension(285, 25));
                hourlyWageLabel.setForeground(Color.white);
                hourlyWageLabel.setEditable(false);
                hourlyWageLabel.setHorizontalAlignment(JTextField.CENTER);
                add(hourlyWageLabel);
            }

            private void initHoursLabel() {
                JTextField hoursLabel = new JTextField("Weekly Hours: " + employee.getHours());
                hoursLabel.setBackground(Color.red);
                hoursLabel.setFont(new Font("Arial", Font.BOLD, 18));
                hoursLabel.setPreferredSize(new Dimension(285, 25));
                hoursLabel.setForeground(Color.white);
                hoursLabel.setEditable(false);
                hoursLabel.setHorizontalAlignment(JTextField.CENTER);
                add(hoursLabel);
            }

            private void initWeeklyEarnings() {
                JTextField weeklyEarnings = new JTextField("Weekly Earnings: $" + employee.getWeeklySalary());
                weeklyEarnings.setBackground(Color.red);
                weeklyEarnings.setFont(new Font("Arial", Font.BOLD, 18));
                weeklyEarnings.setPreferredSize(new Dimension(285, 25));
                weeklyEarnings.setForeground(Color.white);
                weeklyEarnings.setEditable(false);
                weeklyEarnings.setHorizontalAlignment(JTextField.CENTER);
                add(weeklyEarnings);
            }

            private void initTotalEarningsLabel() {
                JTextField totalEarningLabel = new JTextField("Total Earnings: $" + employee.getTotalSalary());
                totalEarningLabel.setBackground(Color.red);
                totalEarningLabel.setFont(new Font("Arial", Font.BOLD, 18));
                totalEarningLabel.setPreferredSize(new Dimension(285, 25));
                totalEarningLabel.setForeground(Color.white);
                totalEarningLabel.setEditable(false);
                totalEarningLabel.setHorizontalAlignment(JTextField.CENTER);
                add(totalEarningLabel);
            }

            private void editSchedule() {
                JButton editSchedule = new JButton("Edit Schedule");
                add(editSchedule);
                EditSchedulePanel editSchedulePanel = new EditSchedulePanel();
                addPanel(editSchedulePanel, employee.getName() + " Edit Schedule Panel");
                editSchedule.setPreferredSize(new Dimension(280,40));
                editSchedule.setFont(new Font("Arial", Font.BOLD, 20));
                editSchedule.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastLayout.show(eastPanel, employee.getName() + " Edit Schedule Panel");
                    }
                });
            }

            private void viewSchedule() {
                JButton viewSchedule = new JButton("View Schedule");
                add(viewSchedule);
                ViewSchedulePanel viewSchedulePanel = new ViewSchedulePanel();
                addPanel(viewSchedulePanel, employee.getName() + " View Schedule Panel");
                viewSchedule.setPreferredSize(new Dimension(280,40));
                viewSchedule.setFont(new Font("Arial", Font.BOLD, 20));
                viewSchedule.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastLayout.show(eastPanel, employee.getName() + " View Schedule Panel");
                    }
                });
            }

            private void initNameLabel() {
                JTextField employeeMenu = new JTextField(employee.getName());
                employeeMenu.setBackground(Color.red);
                employeeMenu.setFont(new Font("Arial", Font.BOLD, 20));
                employeeMenu.setPreferredSize(new Dimension(285, 50));
                employeeMenu.setForeground(Color.white);
                employeeMenu.setEditable(false);
                employeeMenu.setHorizontalAlignment(JTextField.CENTER);
                add(employeeMenu);
            }

            private void initBack() {
                JButton back = new JButton("Back");
                add(back);
                back.setPreferredSize(new Dimension(280,40));
                back.setFont(new Font("Arial", Font.BOLD, 20));
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eastLayout.show(eastPanel, "View Employee Panel");
                    }
                });
            }

            private class ViewSchedulePanel extends JPanel {

                public ViewSchedulePanel() {
                    super(new GridLayout(10, 1, 10, 10));
                    setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                    setPreferredSize(new Dimension(300, 600));
                    setBackground(Color.red);
                    initPanel();
                }

                private void initPanel() {
                    initLabel();
                    initAvailability();
                    initBack();
                }

                private void initAvailability() {
                    for (Availability avail: employee.getAvailability()) {
                        int start = avail.getStart();
                        String ap = "";
                        int end = start + avail.getHours();
                        String ap2 = "";
                        String day = "";
                        switch(avail.getDay()) {
                            case 0:
                                day = "Monday";
                                break;
                            case 1:
                                day = "Tuesday";
                                break;
                            case 2:
                                day = "Wednesday";
                                break;
                            case 3:
                                day = "Thursday";
                                break;
                            case 4:
                                day = "Friday";
                                break;
                            case 5:
                                day = "Saturday";
                                break;
                            case 6:
                                day = "Sunday";
                                break;
                        }

                        if (start > 12) {
                            start = start - 12;
                            ap = "pm";
                        } else if (start == 12) {
                            ap = "pm";
                        } else {
                            ap = "am";
                        }

                        if (end > 12) {
                            end = end - 12;
                            ap2 = "pm";
                        } else if (end == 12) {
                            ap2 = "pm";
                        } else {
                            ap2 = "am";
                        }
                        JTextField employeeSchedule = new JTextField(day + ": " + start + ":00 " + ap  + " - " + end + ":00 " + ap2);
                        employeeSchedule.setBackground(Color.red);
                        employeeSchedule.setFont(new Font("Arial", Font.BOLD, 18));
                        employeeSchedule.setPreferredSize(new Dimension(285, 50));
                        employeeSchedule.setForeground(Color.white);
                        employeeSchedule.setEditable(false);
                        employeeSchedule.setHorizontalAlignment(JTextField.CENTER);
                        add(employeeSchedule);
                    }
                }

                private void initLabel() {
                    JTextField employeeSchedule = new JTextField(employee.getName() + " Schedule");
                    employeeSchedule.setBackground(Color.red);
                    employeeSchedule.setFont(new Font("Arial", Font.BOLD, 20));
                    employeeSchedule.setPreferredSize(new Dimension(285, 50));
                    employeeSchedule.setForeground(Color.white);
                    employeeSchedule.setEditable(false);
                    employeeSchedule.setHorizontalAlignment(JTextField.CENTER);
                    add(employeeSchedule);
                }

                private void initBack() {
                    JButton back = new JButton("Back");
                    add(back);
                    back.setPreferredSize(new Dimension(280,40));
                    back.setFont(new Font("Arial", Font.BOLD, 20));
                    back.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastLayout.show(eastPanel, employee.getName() + " Employee Panel");
                        }
                    });
                }
            }

            private class EditSchedulePanel extends JPanel {

                public EditSchedulePanel() {
                    super(new GridLayout(10, 1, 10, 10));
                    setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                    setPreferredSize(new Dimension(300, 600));
                    setBackground(Color.red);
                    initPanel();
                }

                private void initPanel() {
                    initLabel();
                    initEditLabel();
                    initAvailability();
                    initAddAvailability();
                    initRemoveAvailability();
                    initBack();
                }

                private void initEditLabel() {
                    JLabel editLabel = new JLabel("Click Availability to edit");
                    editLabel.setBackground(Color.darkGray);
                    editLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    editLabel.setPreferredSize(new Dimension(276, 25));
                    editLabel.setForeground(Color.white);
                    add(editLabel);
                }

                private void initRemoveAvailability() {
                    JButton removeAvailability = new JButton("Toggle Remove");
                    add(removeAvailability);
                    RemoveAvailabilityPanel removeAvailabilityPanel = new RemoveAvailabilityPanel();
                    addPanel(removeAvailabilityPanel, employee.getName() + " Remove Availability Panel");
                    removeAvailability.setPreferredSize(new Dimension(280,40));
                    removeAvailability.setFont(new Font("Arial", Font.BOLD, 20));
                    removeAvailability.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastLayout.show(eastPanel, employee.getName() + " Remove Availability Panel");
                        }
                    });
                }

                private void initAddAvailability() {
                    JButton addAvailability = new JButton("Add Availability");
                    add(addAvailability);
                    AddAvailabilityPanel addAvailabilityPanel = new AddAvailabilityPanel();
                    addPanel(addAvailabilityPanel, employee.getName() + " Add Availability Panel");
                    addAvailability.setPreferredSize(new Dimension(280,40));
                    addAvailability.setFont(new Font("Arial", Font.BOLD, 20));
                    addAvailability.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastLayout.show(eastPanel, employee.getName() + " Add Availability Panel");
                        }
                    });
                }

                private void initAvailability() {
                    for (Availability avail: employee.getAvailability()) {
                        int start = avail.getStart();
                        String ap = "";
                        int end = start + avail.getHours();
                        String ap2 = "";
                        String day = "";
                        switch(avail.getDay()) {
                            case 0:
                                day = "Monday";
                                break;
                            case 1:
                                day = "Tuesday";
                                break;
                            case 2:
                                day = "Wednesday";
                                break;
                            case 3:
                                day = "Thursday";
                                break;
                            case 4:
                                day = "Friday";
                                break;
                            case 5:
                                day = "Saturday";
                                break;
                            case 6:
                                day = "Sunday";
                                break;
                        }

                        if (start > 12) {
                            start = start - 12;
                            ap = "pm";
                        } else if (start == 12) {
                            ap = "pm";
                        } else {
                            ap = "am";
                        }

                        if (end > 12) {
                            end = end - 12;
                            ap2 = "pm";
                        } else if (end == 12) {
                            ap2 = "pm";
                        } else {
                            ap2 = "am";
                        }
                        JButton employeeSchedule = new JButton(day + ": " + start + ":00 " + ap  + " - " + end + ":00 " + ap2);
                        employeeSchedule.setBackground(Color.green);
                        EditAvailabilityPanel editAvailabilityPanel = new EditAvailabilityPanel(avail);
                        addPanel(editAvailabilityPanel, employee.getName() + avail.getDay() + " Edit Panel");
                        employeeSchedule.setFont(new Font("Arial", Font.BOLD, 17));
                        employeeSchedule.setPreferredSize(new Dimension(285, 50));
                        employeeSchedule.setForeground(Color.white);
                        employeeSchedule.setHorizontalAlignment(JTextField.CENTER);
                        add(employeeSchedule);
                        employeeSchedule.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                eastLayout.show(eastPanel, employee.getName() + avail.getDay() + " Edit Panel");
                            }
                        });
                    }
                }

                private void initLabel() {
                    JTextField employeeSchedule = new JTextField(employee.getName() + " Schedule");
                    employeeSchedule.setBackground(Color.red);
                    employeeSchedule.setFont(new Font("Arial", Font.BOLD, 20));
                    employeeSchedule.setPreferredSize(new Dimension(285, 50));
                    employeeSchedule.setForeground(Color.white);
                    employeeSchedule.setEditable(false);
                    employeeSchedule.setHorizontalAlignment(JTextField.CENTER);
                    add(employeeSchedule);
                }

                private void initBack() {
                    JButton back = new JButton("Back");
                    add(back);
                    back.setPreferredSize(new Dimension(280,40));
                    back.setFont(new Font("Arial", Font.BOLD, 20));
                    back.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            eastLayout.show(eastPanel, employee.getName() + " Employee Panel");
                        }
                    });
                }

                private class RemoveAvailabilityPanel extends JPanel {

                    public RemoveAvailabilityPanel() {
                        super(new GridLayout(10, 1, 10, 10));
                        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                        setPreferredSize(new Dimension(300, 600));
                        setBackground(Color.red);
                        initPanel();
                    }

                    private void initPanel() {
                        initLabel();
                        initEditLabel();
                        initAvailability();
                        initAddAvailability();
                        initRemoveAvailability();
                        initBack();
                    }

                    private void initEditLabel() {
                        JLabel editLabel = new JLabel("Click Availability to remove");
                        editLabel.setBackground(Color.darkGray);
                        editLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        editLabel.setPreferredSize(new Dimension(276, 25));
                        editLabel.setForeground(Color.white);
                        add(editLabel);
                    }

                    private void initRemoveAvailability() {
                        JButton removeAvailability = new JButton("Toggle Remove");
                        add(removeAvailability);
                        removeAvailability.setPreferredSize(new Dimension(280,40));
                        removeAvailability.setFont(new Font("Arial", Font.BOLD, 20));
                        removeAvailability.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                eastLayout.show(eastPanel, employee.getName() + " Edit Schedule Panel");
                            }
                        });
                    }

                    private void initAddAvailability() {
                        JButton addAvailability = new JButton("Add Availability");
                        add(addAvailability);
                        addAvailability.setPreferredSize(new Dimension(280,40));
                        addAvailability.setFont(new Font("Arial", Font.BOLD, 20));
                        addAvailability.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                eastLayout.show(eastPanel, employee.getName() + " Add Availability Panel");
                            }
                        });
                    }

                    private void initAvailability() {
                        for (Availability avail: employee.getAvailability()) {
                            int start = avail.getStart();
                            String ap = "";
                            int end = start + avail.getHours();
                            String ap2 = "";
                            String day = "";
                            switch(avail.getDay()) {
                                case 0:
                                    day = "Monday";
                                    break;
                                case 1:
                                    day = "Tuesday";
                                    break;
                                case 2:
                                    day = "Wednesday";
                                    break;
                                case 3:
                                    day = "Thursday";
                                    break;
                                case 4:
                                    day = "Friday";
                                    break;
                                case 5:
                                    day = "Saturday";
                                    break;
                                case 6:
                                    day = "Sunday";
                                    break;
                            }

                            if (start > 12) {
                                start = start - 12;
                                ap = "pm";
                            } else if (start == 12) {
                                ap = "pm";
                            } else {
                                ap = "am";
                            }

                            if (end > 12) {
                                end = end - 12;
                                ap2 = "pm";
                            } else if (end == 12) {
                                ap2 = "pm";
                            } else {
                                ap2 = "am";
                            }
                            JButton employeeSchedule = new JButton(day + ": " + start + ":00 " + ap  + " - " + end + ":00 " + ap2);
                            employeeSchedule.setBackground(Color.red);
                            employeeSchedule.setFont(new Font("Arial", Font.BOLD, 17));
                            employeeSchedule.setPreferredSize(new Dimension(285, 50));
                            employeeSchedule.setForeground(Color.white);
                            employeeSchedule.setHorizontalAlignment(JTextField.CENTER);
                            add(employeeSchedule);
                            employeeSchedule.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    employee.removeAvailability(avail);
                                    initPanels();
                                    eastLayout.show(eastPanel, employee.getName() + " Edit Schedule Panel");
                                }
                            });
                        }
                    }

                    private void initLabel() {
                        JTextField employeeSchedule = new JTextField(employee.getName() + " Schedule");
                        employeeSchedule.setBackground(Color.red);
                        employeeSchedule.setFont(new Font("Arial", Font.BOLD, 20));
                        employeeSchedule.setPreferredSize(new Dimension(285, 50));
                        employeeSchedule.setForeground(Color.white);
                        employeeSchedule.setEditable(false);
                        employeeSchedule.setHorizontalAlignment(JTextField.CENTER);
                        add(employeeSchedule);
                    }

                    private void initBack() {
                        JButton back = new JButton("Back");
                        add(back);
                        back.setPreferredSize(new Dimension(280,40));
                        back.setFont(new Font("Arial", Font.BOLD, 20));
                        back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                eastLayout.show(eastPanel, employee.getName() + " Employee Panel");
                            }
                        });
                    }


                }

                private class AddAvailabilityPanel extends JPanel {
                    JTextField startTime;
                    JTextField hours;
                    int dayChosen;

                    public AddAvailabilityPanel() {
                        super(new GridLayout(10, 1, 10, 10));
                        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                        setPreferredSize(new Dimension(300, 600));
                        setBackground(Color.red);
                        dayChosen = 8;
                        initPanel();
                    }

                    private void initPanel() {
                        initLabel();
                        initStarTime();
                        initHours();
                        initDay();
                        initConfirm();
                        initBack();
                    }

                    private void initDay() {
                        for (int i = 0; i < 7; i++) {
                            int x = i;
                            String day = "";
                            switch(i) {
                                case 0:
                                    day = "Monday";
                                    break;
                                case 1:
                                    day = "Tuesday";
                                    break;
                                case 2:
                                    day = "Wednesday";
                                    break;
                                case 3:
                                    day = "Thursday";
                                    break;
                                case 4:
                                    day = "Friday";
                                    break;
                                case 5:
                                    day = "Saturday";
                                    break;
                                case 6:
                                    day = "Sunday";
                                    break;
                            }
                            JCheckBox checkBox = new JCheckBox();
                            JButton button = new JButton(day);
                            add(button);
                            button.setPreferredSize(new Dimension(280,30));
                            button.setFont(new Font("Arial", Font.BOLD, 20));
                            button.setBackground(Color.darkGray);
                            button.setForeground(Color.white);
                            button.add(checkBox);
                            checkBox.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    dayChosen = x;
                                    eastLayout.show(eastPanel, employee.getName() + " Add Availability Panel");
                                }
                            });
                        }
                    }

                    private void initConfirm() {
                        JButton confirm = new JButton("Confirm");
                        add(confirm);
                        confirm.setPreferredSize(new Dimension(280,40));
                        confirm.setFont(new Font("Arial", Font.BOLD, 20));
                        confirm.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (hours.getText() != "" && startTime.getText() != "" && dayChosen != 8) {
                                    int h = Integer.parseInt(hours.getText());
                                    int s = Integer.parseInt(startTime.getText());
                                    Availability availability = new Availability(dayChosen, h, s);
                                    employee.addAvailability(availability);
                                    initPanels();
                                    eastLayout.show(eastPanel, employee.getName() + " Edit Schedule Panel");
                                }
                            }
                        });
                    }

                    private void initHours() {
                        JLabel hoursLabel = new JLabel("Hours");
                        hoursLabel.setBackground(Color.darkGray);
                        hoursLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        hoursLabel.setPreferredSize(new Dimension(276, 25));
                        hoursLabel.setForeground(Color.white);
                        add(hoursLabel);

                        hours = new JTextField();
                        hours.setBackground(Color.white);
                        hours.setFont(new Font("Arial", Font.BOLD, 20));
                        hours.setPreferredSize(new Dimension(285, 30));
                        hours.setForeground(Color.black);
                        hours.setEditable(true);
                        hours.setHorizontalAlignment(JTextField.LEFT);
                        add(hours);

                    }

                    private void initStarTime() {
                        JLabel startLabel = new JLabel("Start Time");
                        startLabel.setBackground(Color.darkGray);
                        startLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        startLabel.setPreferredSize(new Dimension(276, 25));
                        startLabel.setForeground(Color.white);
                        add(startLabel);

                        startTime = new JTextField();
                        startTime.setBackground(Color.white);
                        startTime.setFont(new Font("Arial", Font.BOLD, 20));
                        startTime.setPreferredSize(new Dimension(285, 30));
                        startTime.setForeground(Color.black);
                        startTime.setEditable(true);
                        startTime.setHorizontalAlignment(JTextField.LEFT);
                        add(startTime);
                    }

                    private void initLabel() {
                        JTextField employeeSchedule = new JTextField("Add Availability Menu");
                        employeeSchedule.setBackground(Color.red);
                        employeeSchedule.setFont(new Font("Arial", Font.BOLD, 20));
                        employeeSchedule.setPreferredSize(new Dimension(285, 50));
                        employeeSchedule.setForeground(Color.white);
                        employeeSchedule.setEditable(false);
                        employeeSchedule.setHorizontalAlignment(JTextField.CENTER);
                        add(employeeSchedule);
                    }

                    private void initBack() {
                        JButton back = new JButton("Back");
                        add(back);
                        back.setPreferredSize(new Dimension(280,40));
                        back.setFont(new Font("Arial", Font.BOLD, 20));
                        back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                initPanels();
                                eastLayout.show(eastPanel, employee.getName() + " Edit Schedule Panel");
                            }
                        });
                    }

                }

                private class EditAvailabilityPanel extends JPanel{
                    private Availability availability;
                    JTextField hours;
                    JTextField start;

                    public EditAvailabilityPanel(Availability avail) {
                        super(new GridLayout(10, 1, 10, 10));
                        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                        setPreferredSize(new Dimension(300, 600));
                        setBackground(Color.red);
                        this.availability = avail;
                        initPanel();
                    }

                    private void initPanel() {
                        initLabel();
                        initDay();
                        initStartTime();
                        initHours();
                        initConfirm();
                        initBack();
                    }

                    private void initDay() {
                        String day = "";
                        switch(availability.getDay()) {
                            case 0:
                                day = "Monday";
                                break;
                            case 1:
                                day = "Tuesday";
                                break;
                            case 2:
                                day = "Wednesday";
                                break;
                            case 3:
                                day = "Thursday";
                                break;
                            case 4:
                                day = "Friday";
                                break;
                            case 5:
                                day = "Saturday";
                                break;
                            case 6:
                                day = "Sunday";
                                break;
                        }
                        JLabel dayLabel = new JLabel(day);
                        dayLabel.setBackground(Color.darkGray);
                        dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        dayLabel.setPreferredSize(new Dimension(276, 25));
                        dayLabel.setForeground(Color.white);
                        add(dayLabel);
                    }

                    private void initConfirm() {
                        JButton confirm = new JButton("Confirm");
                        add(confirm);
                        confirm.setPreferredSize(new Dimension(280,40));
                        confirm.setFont(new Font("Arial", Font.BOLD, 20));
                        confirm.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (hours.getText() != "" && start.getText() != "") {
                                    int h = Integer.parseInt(hours.getText());
                                    int s = Integer.parseInt(start.getText());
                                    availability.setHours(h);
                                    availability.setStart(s);
                                    initPanels();
                                    eastLayout.show(eastPanel, employee.getName() + " Edit Schedule Panel");
                                }
                            }
                        });
                    }

                    private void initHours() {
                        JLabel hoursLabel = new JLabel("Hours");
                        hoursLabel.setBackground(Color.darkGray);
                        hoursLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        hoursLabel.setPreferredSize(new Dimension(276, 25));
                        hoursLabel.setForeground(Color.white);
                        add(hoursLabel);

                        hours = new JTextField();
                        hours.setBackground(Color.white);
                        hours.setFont(new Font("Arial", Font.BOLD, 20));
                        hours.setPreferredSize(new Dimension(285, 30));
                        hours.setForeground(Color.black);
                        hours.setEditable(true);
                        hours.setHorizontalAlignment(JTextField.LEFT);
                        add(hours);
                    }

                    private void initStartTime() {
                        JLabel startTime = new JLabel("Start Time");
                        startTime.setBackground(Color.darkGray);
                        startTime.setFont(new Font("Arial", Font.BOLD, 16));
                        startTime.setPreferredSize(new Dimension(276, 25));
                        startTime.setForeground(Color.white);
                        add(startTime);

                        start = new JTextField();
                        start.setBackground(Color.white);
                        start.setFont(new Font("Arial", Font.BOLD, 20));
                        start.setPreferredSize(new Dimension(285, 30));
                        start.setForeground(Color.black);
                        start.setEditable(true);
                        start.setHorizontalAlignment(JTextField.LEFT);
                        add(start);
                    }

                    private void initLabel() {
                        JTextField employeeSchedule = new JTextField("Edit Availability");
                        employeeSchedule.setBackground(Color.red);
                        employeeSchedule.setFont(new Font("Arial", Font.BOLD, 20));
                        employeeSchedule.setPreferredSize(new Dimension(285, 50));
                        employeeSchedule.setForeground(Color.white);
                        employeeSchedule.setEditable(false);
                        employeeSchedule.setHorizontalAlignment(JTextField.CENTER);
                        add(employeeSchedule);
                    }

                    private void initBack() {
                        JButton back = new JButton("Back");
                        add(back);
                        back.setPreferredSize(new Dimension(280,40));
                        back.setFont(new Font("Arial", Font.BOLD, 20));
                        back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                initPanels();
                                eastLayout.show(eastPanel, employee.getName() + " Edit Schedule Panel");
                            }
                        });
                    }
                }
            }

            private class EditHourlySalaryPanel extends JPanel {
                private JTextField newSalary;

                public EditHourlySalaryPanel() {
                    super(new GridLayout(10, 1, 10, 10));
                    setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                    setPreferredSize(new Dimension(300, 600));
                    setBackground(Color.red);
                    initPanel();
                }

                private void initPanel() {
                    initLabel();
                    initHourlyWage();
                    initTextField();
                    initConfirm();
                    initBack();
                }

                private void initConfirm() {
                    JButton confirm = new JButton("Confirm");
                    add(confirm);
                    confirm.setPreferredSize(new Dimension(280,40));
                    confirm.setFont(new Font("Arial", Font.BOLD, 20));
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (newSalary.getText() != "") {
                                int h = Integer.parseInt(newSalary.getText());
                                employee.setHourlySalary(h);
                                initPanels();
                                eastLayout.show(eastPanel, employee.getName() + " Employee Panel");
                            }
                        }
                    });
                }

                private void initTextField() {
                    JLabel newHourlyWage = new JLabel("Enter new Hourly Salary");
                    newHourlyWage.setBackground(Color.darkGray);
                    newHourlyWage.setFont(new Font("Arial", Font.BOLD, 16));
                    newHourlyWage.setPreferredSize(new Dimension(276, 25));
                    newHourlyWage.setForeground(Color.white);
                    add(newHourlyWage);

                    newSalary = new JTextField();
                    newSalary.setBackground(Color.white);
                    newSalary.setFont(new Font("Arial", Font.BOLD, 20));
                    newSalary.setPreferredSize(new Dimension(285, 30));
                    newSalary.setForeground(Color.black);
                    newSalary.setEditable(true);
                    newSalary.setHorizontalAlignment(JTextField.LEFT);
                    add(newSalary);
                }

                private void initHourlyWage() {
                    JLabel hourlyWage = new JLabel("Current Hourly Salary: $" + employee.getHourlySalary());
                    hourlyWage.setBackground(Color.darkGray);
                    hourlyWage.setFont(new Font("Arial", Font.BOLD, 16));
                    hourlyWage.setPreferredSize(new Dimension(276, 25));
                    hourlyWage.setForeground(Color.white);
                    add(hourlyWage);
                }

                private void initLabel() {
                    JTextField editHourlySalaryLabel = new JTextField("Edit Hourly Salary");
                    editHourlySalaryLabel.setBackground(Color.red);
                    editHourlySalaryLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    editHourlySalaryLabel.setPreferredSize(new Dimension(285, 50));
                    editHourlySalaryLabel.setForeground(Color.white);
                    editHourlySalaryLabel.setEditable(false);
                    editHourlySalaryLabel.setHorizontalAlignment(JTextField.CENTER);
                    add(editHourlySalaryLabel);
                }

                private void initBack() {
                    JButton back = new JButton("Back");
                    add(back);
                    back.setPreferredSize(new Dimension(280,40));
                    back.setFont(new Font("Arial", Font.BOLD, 20));
                    back.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            initPanels();
                            eastLayout.show(eastPanel, employee.getName() + " Employee Panel");
                        }
                    });
                }
            }
        }
    }

    private class FireEmployeePanel extends JPanel {

        public FireEmployeePanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            setBackground(Color.red);
            initPanel();
        }

        private void initPanel() {
            initLabel();
            initEmployees();
            initBack();
        }

        private void initEmployees() {
            for (Employee e1: store.getEmployeeList()) {
                JButton employeeButton = new JButton(e1.getName());
                add(employeeButton);
                employeeButton.setPreferredSize(new Dimension(280,40));
                employeeButton.setFont(new Font("Arial", Font.BOLD, 20));
                employeeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        store.fireEmployee(e1);
                        initPanels();
                        eastLayout.show(eastPanel, "Employee Menu");

                    }
                });
            }
        }

        private void initLabel() {
            JTextField employeeMenu = new JTextField("Fire Employee Menu");
            employeeMenu.setBackground(Color.red);
            employeeMenu.setFont(new Font("Arial", Font.BOLD, 20));
            employeeMenu.setPreferredSize(new Dimension(285, 50));
            employeeMenu.setForeground(Color.white);
            employeeMenu.setEditable(false);
            employeeMenu.setHorizontalAlignment(JTextField.CENTER);
            add(employeeMenu);
        }

        private void initBack() {
            JButton back = new JButton("Back");
            add(back);
            back.setPreferredSize(new Dimension(280,40));
            back.setFont(new Font("Arial", Font.BOLD, 20));
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastLayout.show(eastPanel, "Employee Menu");
                }
            });
        }
    }

    private class HireEmployeePanel extends JPanel{
        int gender; 
        JTextField nameField; 
        JTextField wageField;
        JCheckBox maleCheckBox;
        JCheckBox femaleCheckBox;
        
        public HireEmployeePanel() {
            super(new GridLayout(10, 1, 10, 10));
            setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            setPreferredSize(new Dimension(300, 600));
            gender = 2;
            setBackground(Color.red);
            initPanel();
        }

        private void initPanel() {
            initLabel();
            initNameField();
            initWageField();
            initGenderBiological();
            initConfirm();
            initBack();
        }

        private void initConfirm() {
            JButton confirm = new JButton("Confirm");
            add(confirm);
            confirm.setPreferredSize(new Dimension(280,40));
            confirm.setFont(new Font("Arial", Font.BOLD, 20));
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (nameField.getText() != "" && wageField.getText() != "" && gender != 2) {
                        String name = nameField.getText();
                        nameField.setText("");
                        int wage = Integer.parseInt(wageField.getText());
                        Employee employee = new Employee(name, wage, gender);
                        store.hireEmployee(employee);
                        initPanels();
                        eastLayout.show(eastPanel, "Employee Menu");
                    }
                }
            });
        }

        private void initGenderBiological() {
            JLabel Gender = new JLabel("Biological Gender");
            Gender.setBackground(Color.darkGray);
            Gender.setFont(new Font("Arial", Font.BOLD, 16));
            Gender.setPreferredSize(new Dimension(276, 25));
            Gender.setForeground(Color.white);
            add(Gender);
            maleCheckBox = new JCheckBox();
            femaleCheckBox = new JCheckBox();

            JButton male = new JButton("Male");
            add(male);
            male.setPreferredSize(new Dimension(280,30));
            male.setFont(new Font("Arial", Font.BOLD, 20));
            male.setBackground(Color.darkGray);
            male.setForeground(Color.white);
            male.add(maleCheckBox);
            maleCheckBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gender = 0;
                    eastLayout.show(eastPanel, "Hire Employee Panel");
                }
            });

            JButton female = new JButton("Female");
            add(female);
            female.setPreferredSize(new Dimension(280,30));
            female.setFont(new Font("Arial", Font.BOLD, 20));
            female.setBackground(Color.darkGray);
            female.setForeground(Color.white);
            female.add(femaleCheckBox);
            femaleCheckBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gender = 1;
                    eastLayout.show(eastPanel, "Hire Employee Panel");
                }
            });
        }

        private void initWageField() {
            JLabel hourlyWageLabel = new JLabel("Hourly Wage");
            hourlyWageLabel.setBackground(Color.darkGray);
            hourlyWageLabel.setFont(new Font("Arial", Font.BOLD, 16));
            hourlyWageLabel.setPreferredSize(new Dimension(276, 25));
            hourlyWageLabel.setForeground(Color.white);
            add(hourlyWageLabel);

            wageField = new JTextField();
            wageField.setBackground(Color.white);
            wageField.setFont(new Font("Arial", Font.BOLD, 20));
            wageField.setPreferredSize(new Dimension(285, 30));
            wageField.setForeground(Color.black);
            wageField.setEditable(true);
            wageField.setHorizontalAlignment(JTextField.LEFT);
            add(wageField);
        }

        private void initNameField() {
            JLabel nameLabel = new JLabel("Employee Name");
            nameLabel.setBackground(Color.darkGray);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setPreferredSize(new Dimension(276, 25));
            nameLabel.setForeground(Color.white);
            add(nameLabel);

            nameField = new JTextField();
            nameField.setBackground(Color.white);
            nameField.setFont(new Font("Arial", Font.BOLD, 20));
            nameField.setPreferredSize(new Dimension(285, 30));
            nameField.setForeground(Color.black);
            nameField.setEditable(true);
            nameField.setHorizontalAlignment(JTextField.LEFT);
            add(nameField);
        }

        private void initBack() {
            JButton back = new JButton("Back");
            add(back);
            back.setPreferredSize(new Dimension(280,40));
            back.setFont(new Font("Arial", Font.BOLD, 20));
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eastLayout.show(eastPanel, "Employee Menu");
                }
            });
        }

        private void initLabel() {
            JTextField hireEmployeeLabel = new JTextField("Hire Employee Menu");
            hireEmployeeLabel.setBackground(Color.red);
            hireEmployeeLabel.setFont(new Font("Arial", Font.BOLD, 20));
            hireEmployeeLabel.setPreferredSize(new Dimension(285, 50));
            hireEmployeeLabel.setForeground(Color.white);
            hireEmployeeLabel.setEditable(false);
            hireEmployeeLabel.setHorizontalAlignment(JTextField.CENTER);
            add(hireEmployeeLabel);
        }
    }
}
