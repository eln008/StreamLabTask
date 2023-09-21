package Model;

import java.util.List;

public class Phone {
    private int id;
    private String name;
    private String brand;
    private List<Contact> contacts;

    public Phone(int id, String name, String brand, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.contacts = contacts;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", \nname='" + name + '\'' +
                ", \nbrand='" + brand + '\'' +
                ", \ncontacts=" + contacts +
                '}';
    }
}
