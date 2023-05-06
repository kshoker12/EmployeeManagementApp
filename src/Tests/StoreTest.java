package Tests;

import Model.Availability;
import Model.Employee;
import Model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreTest {
    Store store;
    Employee e1;
    Employee e2;
    Employee e3;
    Employee e4;

    @BeforeEach
    void init() {
        store = new Store("Coast Meridian", 8, 20);
        e1 = new Employee("James Bond", 15, 0);
        e1.addAvailability(new Availability(0, 6, 8));
        e1.addAvailability(new Availability(1, 6, 8));
        e1.addAvailability(new Availability(2, 6, 8));
        e1.addAvailability(new Availability(3, 6, 14));
        e1.addAvailability(new Availability(4, 6, 14));
        e1.addAvailability(new Availability(5, 6, 8));
        e1.addAvailability(new Availability(6, 6, 8));

        e2 = new Employee("Wonder Woman", 15, 1);
        e2.addAvailability(new Availability(0, 6, 8));
        e2.addAvailability(new Availability(1, 6, 14));
        e2.addAvailability(new Availability(2, 6, 8));
        e2.addAvailability(new Availability(3, 6, 14));
        e2.addAvailability(new Availability(4, 6, 8));
        e2.addAvailability(new Availability(5, 6, 14));
        e2.addAvailability(new Availability(6, 6, 8));

        e3 = new Employee("Superman", 13, 0);
        e3.addAvailability(new Availability(0, 6, 14));
        e3.addAvailability(new Availability(1, 6, 8));
        e3.addAvailability(new Availability(2, 6, 14));
        e3.addAvailability(new Availability(3, 6, 8));
        e3.addAvailability(new Availability(4, 6, 8));
        e3.addAvailability(new Availability(5, 6, 14));
        e3.addAvailability(new Availability(6, 6, 14));

        e4 = new Employee("Batman", 14, 0);
        e4.addAvailability(new Availability(0, 6, 14));
        e4.addAvailability(new Availability(1, 6, 14));
        e4.addAvailability(new Availability(2, 6, 14));
        e4.addAvailability(new Availability(3, 6, 8));
        e4.addAvailability(new Availability(4, 6, 14));
        e4.addAvailability(new Availability(5, 6, 8));
        e4.addAvailability(new Availability(6, 6, 14));
    }

    @Test
    void testConstructor() {
        assertEquals(store.getName(), "Coast Meridian");
        assertEquals(store.getClose(), 20);
        assertEquals(store.getOpen(), 8);
        assertEquals(store.getActiveEmployees().size(), 0);
        assertEquals(store.getEmployeeList().size(), 0);
        assertEquals(store.getCurrDay(), 0);
        assertEquals(store.getCurrHour(), 8);
        assertEquals(store.getWeeklyEarnings(), 0);
        assertEquals(store.getTotalEarnings(), 0);
    }

    @Test
    void testHireFireEmployee() {
        for (int i = 0; i < 5; i++) {
            assertEquals(store.getEmployeeList().size(), i);
            store.hireEmployee(new Employee("Employee" + i, 10, 0));
            assertEquals(store.getEmployeeList().size(), i+1);
        }

        for (int i = 4; i >= 0; i--) {
            assertEquals(store.getEmployeeList().size(), i+1);
            store.fireEmployee(store.getEmployeeList().get(i));
            assertEquals(store.getEmployeeList().size(), i);
        }
    }

    @Test
    void testIncDay() {
        assertEquals(0, store.getCurrDay());
        store.incDay();
        assertEquals(1, store.getCurrDay());
        store.incDay();
        store.incDay();
        store.incDay();
        store.incDay();
        store.incDay();
        assertEquals(6, store.getCurrDay());
        store.incDay();
        assertEquals(0, store.getCurrDay());
    }

    @Test
    void testAddEarnings() {
        assertEquals(0, store.getWeeklyEarnings());
        store.addEarnings(100);
        assertEquals(100, store.getWeeklyEarnings());
        store.addEarnings(100);
        assertEquals(200, store.getWeeklyEarnings());
    }

    @Test
    void testAddRemoveEmployeeToCurrShift() {
        assertEquals(0, store.getActiveEmployees().size());
        store.addEmployeeToCurrShift(new Employee("James Bond", 10, 0));
        assertEquals(1, store.getActiveEmployees().size());
        store.addEmployeeToCurrShift(new Employee("Batman", 10, 0));
        assertEquals(2, store.getActiveEmployees().size());
        store.removeEmployeeFromCurrShift(store.getActiveEmployees().get(1));
        assertEquals(1, store.getActiveEmployees().size());
        store.removeEmployeeFromCurrShift(store.getActiveEmployees().get(0));
        assertEquals(0, store.getActiveEmployees().size());
    }

    @Test
    void testStartEndDay() {
        store.hireEmployee(e1);
        store.hireEmployee(e2);
        store.hireEmployee(e3);
        store.hireEmployee(e4);
        assertEquals(0, store.getActiveEmployees().size());
        store.startDay();
        assertEquals(2, store.getActiveEmployees().size());
        assertEquals(true, store.getActiveEmployees().contains(e1));
        assertEquals(true, store.getActiveEmployees().contains(e2));
        assertEquals(store.getOpen(), store.getCurrHour());
        store.endDay();
        assertEquals(0, store.getActiveEmployees().size());
        store.setCurrDay(4);
        store.startDay();
        assertEquals(2, store.getActiveEmployees().size());
        assertEquals(true, store.getActiveEmployees().contains(e2));
        assertEquals(true, store.getActiveEmployees().contains(e3));
        assertEquals(store.getOpen(), store.getCurrHour());
        store.endDay();
        assertEquals(0, store.getActiveEmployees().size());
    }

    @Test
    void testWeeklyReset() {
        store.hireEmployee(e1);
        store.hireEmployee(e2);
        store.hireEmployee(e3);
        store.hireEmployee(e4);
        e1.setHours(10);
        e2.setHours(10);
        e3.setHours(10);
        e4.setHours(10);
        store.setWeeklyEarnings(10000);
        assertEquals(store.getWeeklyEarnings(), 10000);
        assertEquals(store.getTotalEarnings(), 0);
        store.weeklyReset();
        assertEquals(store.getWeeklyEarnings(), 0);
        assertEquals(store.getTotalEarnings(), 10000 - 570);
        assertEquals(e1.getHours(), 0);
        assertEquals(e2.getHours(), 0);
        assertEquals(e3.getHours(), 0);
        assertEquals(e4.getHours(), 0);
    }

    @Test
    void testIncCurrHour() {
        store.hireEmployee(e1);
        store.hireEmployee(e2);
        store.hireEmployee(e3);
        store.hireEmployee(e4);
        store.setCurrHour(store.getClose() - 1);
        assertEquals(store.getCurrHour(), store.getClose() - 1);
        store.incCurrHour();
        assertEquals(store.getCurrHour(), store.getClose());
        store.incCurrHour();
        assertEquals(store.getCurrHour(), store.getClose());
        store.setCurrDay(0);
        store.setCurrHour(13);
        store.addEmployeeToCurrShift(e1);
        store.addEmployeeToCurrShift(e2);
        store.incCurrHour();
        assertEquals(14, store.getCurrHour());
        assertEquals(true, store.getActiveEmployees().contains(e3));
        assertEquals(true, store.getActiveEmployees().contains(e4));
    }

}
