package ssu.epam.ermolaxe.service;

import ssu.epam.ermolaxe.model.Customer;
import ssu.epam.ermolaxe.model.Item;

import java.util.List;

/**
 * Created by Александр on 05.07.2017.
 */
public interface ItemService {
    void save(Item item);

    List<Item> getAllItems();
    List<Item> getBySeller(Customer customer);
    List<Item> getByBuyer(Customer customer);

    List<Item> getAllItemsByNullBuyer();

    void buy(Customer customer, Item item);
}