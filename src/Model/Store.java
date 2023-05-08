package Model;

import Persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Store extends Observable implements Writable {
    private List<Employee> employeeList;
    private String name;
    private int open;
    private int close;
    private int currHour;
    private int currDay;
    private int weeklyEarnings;
    private int totalEarnings;
    private List<Employee> activeEmployees;

    public Store(String name, int open, int close) {
        employeeList = new ArrayList<>();
        this.name = name;
        this.open = open;
        this.close = close;
        this.currDay = 0;
        this.currHour = open;
        this.weeklyEarnings = 0;
        this.totalEarnings = 0;
        activeEmployees = new ArrayList<>();
    }

    public void hireEmployee(Employee employee) {
        this.employeeList.add(employee);
        addObserver(employee);
    }

    public void fireEmployee(Employee employee) {
        this.employeeList.remove(employee);
        if (activeEmployees.contains(employee)) {
            this.activeEmployees.remove(employee);
        }
        deleteObserver(employee);
    }

    public void incDay() {
        if (currDay == 6) {
            currDay = 0;
            weeklyReset();
        } else {
            currDay++;
        }
    }

    public void incCurrHour() {
        if (this.currHour == this.close) {
            return;
        } else {
            this.currHour++;
        }
        int i = 0;
        while(i < activeEmployees.size()) {
          Employee e = activeEmployees.get(i);
          e.incHour();
          if (e.shiftEnd(currHour, currDay) && currHour != close) {
              activeEmployees.remove(i);
              for (Employee e1: employeeList) {
                  for (Availability avail: e1.getAvailability()) {
                      if (avail.getDay() == currDay && avail.getStart() == currHour) {
                          if(!activeEmployees.contains(e1)) {
                              activeEmployees.add(i, e1);
                          }
                      }
                  }
              }
          }
          i++;
        }
    }

    public void addEarnings(int earnings) {
        this.weeklyEarnings += earnings;
    }

    public void addEmployeeToCurrShift(Employee employee) {
        this.activeEmployees.add(employee);
    }

    public void removeEmployeeFromCurrShift(Employee employee) {
        this.activeEmployees.remove(employee);
    }

    public void weeklyReset() {
        int totalWages = 0;
        setChanged();
        notifyObservers(2);
        for (Employee e: employeeList) {
            totalWages += e.getWeeklySalary();
        }
        this.weeklyEarnings -= totalWages;
        this.totalEarnings += weeklyEarnings;
        weeklyEarnings = 0;
        setChanged();
        notifyObservers(1);
    }

    public void startDay() {
        this.currHour = this.open;
        for (Employee e: employeeList) {
            for (Availability avail: e.getAvailability()) {
                if (avail.getDay() == currDay && avail.getStart() == currHour) {
                    addEmployeeToCurrShift(e);
                }
            }
        }
    }

    public void endDay() {
        for (int i = activeEmployees.size() - 1; i >= 0; i--) {
            removeEmployeeFromCurrShift(activeEmployees.get(i));
        }
        setChanged();
        notifyObservers(2);

        incDay();
        this.currHour = this.open;
    }

    public List<Employee> getActiveEmployees() {
        return activeEmployees;
    }

    public void setActiveEmployees(List<Employee> activeEmployees) {
        this.activeEmployees = activeEmployees;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public int getWeeklyEarnings() {
        return weeklyEarnings;
    }

    public void setWeeklyEarnings(int weeklyEarnings) {
        this.weeklyEarnings = weeklyEarnings;
    }

    public int getCurrDay() {
        return currDay;
    }

    public void setCurrDay(int currDay) {
        this.currDay = currDay;
    }

    public int getCurrHour() {
        return currHour;
    }

    public void setCurrHour(int currHour) {
        this.currHour = currHour;
    }

    public int getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("open", open);
        json.put("close", close);
        json.put("currHour", currHour);
        json.put("currDay", currDay);
        json.put("weeklyEarnings", weeklyEarnings);
        json.put("totalEarnings", totalEarnings);
        json.put("employeeList", employeesToJson());
        json.put("activeEmployees", activeEmployeesToJson());
        return json;
    }

    private JSONArray activeEmployeesToJson() {
        JSONArray employees = new JSONArray();
        for (Employee e: this.activeEmployees) {
            employees.put(e.toJson());
        }
        return employees;
    }

    private JSONArray employeesToJson() {
        JSONArray employees = new JSONArray();
        for (Employee e: this.employeeList) {
            employees.put(e.toJson());
        }
        return employees;
    }
}
