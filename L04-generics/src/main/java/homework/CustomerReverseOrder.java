package homework;


import java.util.*;

public class CustomerReverseOrder {


    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    private final ArrayDeque<Customer> customersInReverseOrder;
    Iterator<Customer> iterator;

    public CustomerReverseOrder() {
        this.customersInReverseOrder = new ArrayDeque<>();
    }

    public void add(Customer customer) {
        customersInReverseOrder.addLast(customer);
        iterator = customersInReverseOrder.descendingIterator();
    }

    public Customer take() {
        return iterator.next();
    }
}
