package ssu.epam.ermolaxe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssu.epam.ermolaxe.model.Customer;
import ssu.epam.ermolaxe.model.Item;
import ssu.epam.ermolaxe.repository.ItemRepository;
import ssu.epam.ermolaxe.service.ItemService;

import java.util.List;

/**
 * Created by Александр on 05.07.2017.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getBySeller(Customer customer) {
        return itemRepository.findBySeller(customer);
    }

    public List<Item> getByBuyer(Customer customer) {
        return itemRepository.findByBuyer(customer);
    }

    public List<Item> getAllItemsByNullBuyer() {
        return itemRepository.findByBuyerNull();
    }

    public void buy(Customer customer, Item item) {
        item.setBuyer(customer);
        item.setSold(true);
        itemRepository.save(item);
    }
}
