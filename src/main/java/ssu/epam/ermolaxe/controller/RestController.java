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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("User ["+username+"] requested all items.");
        return itemService.getAllItemsByNullBuyer();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public @ResponseBody List<Item> myCart() {
        Customer customer = new Customer();
        try {
            customer = customerService.getCustomerByLogin(username);
        } catch (NullPointerException e) {
            System.out.println("Empty customer.");
        }
        if (customer != null) {
            System.out.println("User ["+username+"] requested his cart.");
            return itemService.getByBuyer(customer);
        } else {
            System.out.println("User ["+username+"] requested his cart.");
            return new ArrayList<>();
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/myitems", method = RequestMethod.GET)
    public @ResponseBody List<Item> myItems() {
        Customer customer = new Customer();
        try {
            customer = customerService.getCustomerByLogin(username);
        } catch (NullPointerException e) {
            System.out.println("Empty customer");
        }
        System.out.println("User ["+username+"] requested his items.");
        return itemService.getBySeller(customer);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
                List<String> words = new ArrayList<>();
                for (String item : s.next().split("[{\":},]")) {
                    if (item.length() > 0) {
                        words.add(item);
                    }
                }
                if (words.size() == 4) {
                    Customer customer = customerService.getCustomerByLogin(words.get(1));
                    if (customer != null && customer.getPassword().equals(words.get(3))) {
                        customer.setEnable(true);
                        customerService.save(customer);
                        username = words.get(1);
                        password = words.get(3);
                    }
                }
                System.out.println("Logged in as ["+username+"] with password ["+password+"].");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout() {
        try {
            Customer customer = customerService.getCustomerByLogin(username);
            customer.setEnable(false);
            customerService.save(customer);
            System.out.println("User ["+username+"] logged out.");
        } catch (NullPointerException e) {
            System.out.println("Empty customer.");
        } finally {
            username = null;
            password = null;
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addItem(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
                List<String> words = new ArrayList<>();
                for (String item : s.next().split("[{\":},]")) {
                    if (item.length() > 0) {
                        words.add(item);
                    }
                }
                if (words.size() == 6 && username != null) {
                    Item item = new Item();
                    item.setName(words.get(1));
                    item.setDescription(words.get(3));
                    item.setPrice(Integer.parseInt(words.get(5)));
                    item.setSeller(customerService.getCustomerByLogin(username));
                    item.setSold(false);
                    itemService.save(item);
                    System.out.println("User ["+username+"] add item ["+item.toString()+"].");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
                for (String item : s.next().split("[{\"\\[\\]:},]")) {
                    if (item.length() > 0) {
                        for (Item elem : itemService.getAllItemsByNullBuyer()) {
                            if (item.equals(String.valueOf(elem.getId()))) {
                                elem.setBuyer(customerService.getCustomerByLogin(username));
                                elem.setSold(true);
                                itemService.save(elem);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void registration(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
                List<String> words = new ArrayList<>();
                for (String item : s.next().split("[{\":},]")) {
                    if (item.length() > 0) {
                        words.add(item);
                        System.out.println(item);
                    }
                }
                if (words.size() == 4) {
                    Customer customer = new Customer(words.get(1), words.get(3));
                    System.out.println(customer.toString());
                    customerService.save(customer);
                }
                System.out.println("Create new user ["+username+"] with password ["+password+"].");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
