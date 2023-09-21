import DataBase.DataBase;
import Model.Contact;
import Model.Phone;
import Service.ContactService;
import Service.ContactServiceImpl;
import Service.PhoneServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Contact> contacts1 = new ArrayList<>();
        contacts1.add(new Contact("John Doe", "+123456789"));
        contacts1.add(new Contact("Alice Smith", "+987654321"));

        List<Contact> contacts2 = new ArrayList<>();
        contacts2.add(new Contact("Bob Johnson", "+111111111"));
        contacts2.add(new Contact("Eve Wilson", "+222222222"));

        Phone phone1 = new Phone(1, "iPhone 13", "Apple", contacts1);
        Phone phone2 = new Phone(2, "Galaxy S21", "Samsung", contacts2);
        List<Phone> phoneList = new ArrayList<>(Arrays.asList(phone2,phone1));

        DataBase dataBase = new DataBase();
        dataBase.setPhoneList(phoneList);
        PhoneServiceImpl phoneServiceImpl = new PhoneServiceImpl(dataBase);
        ContactServiceImpl contactServiceImpl = new ContactServiceImpl(dataBase);


        phoneServiceImpl.addPhone(phone1);
        phoneServiceImpl.getPhoneById(2);
        phoneServiceImpl.updatePhoneNameById(2,"newName");
        phoneServiceImpl.getAllPhones();
        phoneServiceImpl.getAllPhonesByBrand("Apple");
        phoneServiceImpl.deletePhoneById(1);

        contactServiceImpl.addContactToPhone(1, new Contact("Matilda","+7 7876 7878"));
        contactServiceImpl.findContactByName(1,"Alice");
        contactServiceImpl.findContactByPhoneNumber(1,"+123456789");
        contactServiceImpl.sortContactsByName(1);
        contactServiceImpl.deleteContactByNameFromPhone(2,"Eve Wilson");
    }
}