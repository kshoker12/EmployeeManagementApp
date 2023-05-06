package Tests;

import Model.Availability;
import Model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {
    Employee employee;
    List<Availability> availabilityList;

    @BeforeEach
    void init() {
        employee = new Employee("James Bond", 18, 0);
        availabilityList = new ArrayList<>();
        availabilityList.add(new Availability(0, 8, 7));
        availabilityList.add(new Availability(2, 8, 7));
        availabilityList.add(new Availability(4, 8, 7));
        availabilityList.add(new Availability(6, 8, 7));
    }

    @Test
    void testConstructor() {
        assertEquals(employee.getName(), "James Bond");
        assertEquals(employee.getHours(), 0);
        assertEquals(employee.getAvailability().size(), 0);
        assertEquals(employee.getWeeklySalary(), 0);
        assertEquals(employee.getTotalSalary(), 0);
        assertEquals(employee.getHourlySalary(), 18);
        assertEquals(employee.getGenderBiological(), 0);
    }

    @Test
    void testIncHour() {
        assertEquals(employee.getHours(), 0);
        employee.incHour();
        assertEquals(employee.getHours(), 1);
        employee.incHour();
        assertEquals(employee.getHours(), 2);
    }

    @Test
    void testUpdateSalary() {
        assertEquals(employee.getWeeklySalary(), 0);
        employee.setHours(10);
        employee.updateSalary();
        assertEquals(employee.getWeeklySalary(), 180);
    }

    @Test
    void testShiftEnd() {
        employee.setAvailability(availabilityList);
        assertEquals(employee.shiftEnd(10, 0), false);
        assertEquals(employee.shiftEnd(15, 0), true);
        assertEquals(employee.shiftEnd(14, 0), false);
        assertEquals(employee.shiftEnd(15, 2), true);
        assertEquals(employee.shiftEnd(20, 2), false);
    }

    @Test
    void testAddRemoveEmployee() {
        for (int i = 0; i < availabilityList.size(); i++) {
            assertEquals(i, employee.getAvailability().size());
            employee.addAvailability(availabilityList.get(i));
            assertEquals(i+1, employee.getAvailability().size());
        }

        for (int i = availabilityList.size() - 1; i >= 0; i--) {
            assertEquals(i+1, employee.getAvailability().size());
            employee.removeAvailability(employee.getAvailability().get(i));
            assertEquals(i, employee.getAvailability().size());
        }
    }

    @Test
    void testWeeklyReset() {
        employee.setHours(10);
        employee.setWeeklySalary(1000);
        assertEquals(10, employee.getHours());
        assertEquals(1000, employee.getWeeklySalary());
        assertEquals(0, employee.getTotalSalary());
        employee.weeklyReset();
        assertEquals(0, employee.getHours());
        assertEquals(0, employee.getWeeklySalary());
        assertEquals(1000, employee.getTotalSalary());
    }


}
