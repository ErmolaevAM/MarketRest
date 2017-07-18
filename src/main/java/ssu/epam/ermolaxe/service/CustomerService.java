package ssu.epam.ermolaxe.service;

import ssu.epam.ermolaxe.model.Customer;

import java.util.List;

/**
 * Created by Александр on 05.07.2017.
 */
public interface CustomerService {

    void save(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerByLogin(String username);
}
