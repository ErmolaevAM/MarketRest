package ssu.epam.ermolaxe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.epam.ermolaxe.model.Customer;
import ssu.epam.ermolaxe.model.Item;

import java.util.List;

/**
 * Created by Александр on 05.07.2017.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySeller(Customer customer);
    List<Item> findByBuyer(Customer customer);

    List<Item> findByBuyerNull();
}
