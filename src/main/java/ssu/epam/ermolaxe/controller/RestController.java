package ssu.epam.ermolaxe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssu.epam.ermolaxe.model.Customer;
import ssu.epam.ermolaxe.model.Item;
import ssu.epam.ermolaxe.service.CustomerService;
import ssu.epam.ermolaxe.service.ItemService;

import java.rmi.server.RMIClassLoader;
import java.util.List;

/**
 * Created by Александр on 05.07.2017.
 */

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/epam")
public class RestController {

    private String username;
    private String password;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public @ResponseBody String welcome() {
        return "Hello world!";
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/allitems", method = RequestMethod.GET)
    public @ResponseBody List<Item> allItems() {
        return itemService.getAllItemsByNullBuyer();
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public @ResponseBody List<Item> myCart() {
        return itemService.getByBuyer(customerService.getCustomerByLogin(username));
    }

    @RequestMapping(value = "/myitems", method = RequestMethod.GET)
    public @ResponseBody List<Item> myItems() {
        return itemService.getBySeller(customerService.getCustomerByLogin(username));
    }

}
