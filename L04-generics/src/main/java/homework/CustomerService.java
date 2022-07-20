package homework;

import java.util.*;


public class CustomerService {



    private final TreeMap<Customer, String> customerService;

    public CustomerService() {
        this.customerService = new TreeMap<>();
    }

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Customer customerFromMap = customerService.firstEntry().getKey();
        Customer clonedCustomer = new Customer(customerFromMap.getId(), customerFromMap.getName(), customerFromMap.getScores());
        return new AbstractMap.SimpleEntry<>(clonedCustomer, customerService.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        if (customerService.higherEntry(customer) == null) {
            return null;
        }
        Customer customerFromMap = customerService.higherEntry(customer).getKey();
        Customer clonedCustomer = new Customer(customerFromMap.getId(), customerFromMap.getName(), customerFromMap.getScores());
        return new AbstractMap.SimpleEntry<>(clonedCustomer, customerService.higherEntry(customer).getValue());
    }

    public void add(Customer customer, String data) {
        customerService.put(customer, data);
    }
}
