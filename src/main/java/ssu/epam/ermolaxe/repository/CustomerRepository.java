package ssu.epam.ermolaxe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.epam.ermolaxe.model.Customer;

/**
 * Created by Александр on 05.07.2017.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByLogin(String login);
}
