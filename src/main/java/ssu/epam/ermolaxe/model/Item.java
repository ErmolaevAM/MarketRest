package ssu.epam.ermolaxe.model;

import javax.persistence.*;

/**
 * Created by Александр on 05.07.2017.
 */
@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "SELLER", nullable = false)
    private Customer seller;

    @ManyToOne
    @JoinColumn(name = "BUYER")
    private Customer buyer;

    @Column(name = "SOLD", nullable = false)
    private Boolean sold;

    public Item() {
    }

    public Item(String name, int price, String description, Customer seller, Customer buyer, Boolean sold) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.seller = seller;
        this.buyer = buyer;
        this.sold = sold;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getSeller() {
        return seller;
    }

    public void setSeller(Customer seller) {
        this.seller = seller;
    }

    public Customer getBuyer() {
        return buyer;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public void setBuyer(Customer buyer) {
        this.buyer = buyer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Name=" + name + ", price=" + price + ", description=" + description + ", seller=" + seller + ", buyer=" + buyer;
    }
}