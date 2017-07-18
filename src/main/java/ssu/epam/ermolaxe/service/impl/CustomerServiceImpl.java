package ssu.epam.ermolaxe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssu.epam.ermolaxe.model.Customer;
import ssu.epam.ermolaxe.repository.CustomerRepository;
import ssu.epam.ermolaxe.service.CustomerService;

import java.util.List;

/**
 * Created by Александр on 05.07.2017.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByLogin(String username) {
        return customerRepository.findByLogin(username);
    }
}
