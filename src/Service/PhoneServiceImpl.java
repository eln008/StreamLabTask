package Service;
import DataBase.DataBase;
import Model.Contact;
import Model.Phone;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhoneServiceImpl implements PhoneInterface {
    DataBase dataBase = new DataBase();

    public PhoneServiceImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    @Override
    public String addPhone(Phone phone) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите нмоер телфона: ");
        String newPhoneNumber = scan.next();
        System.out.println("Введите имя нового контакта: ");
        String nameContact = scan.next();
        Contact newContact = new Contact(nameContact, newPhoneNumber);

        List<Contact> contacts = phone.getContacts();

        List<Contact> updatedContacts = Stream.concat(
                        contacts.stream(),
                        Stream.of(newContact))
                .collect(Collectors.toList());

        phone.setContacts(updatedContacts);

        System.out.println("Новый номер телефона добавлен в телефон "+ updatedContacts);
        return null;
    }

    @Override
    public Phone getPhoneById(int phoneId) {
        List<Phone> phoneList = dataBase.getPhoneList();

        Optional<Phone> result = phoneList.stream().filter(phone -> phone.getId() == phoneId).findFirst();
        System.out.println(result);
        return null;
    }

    @Override
    public Phone updatePhoneNameById(int phoneId, String newName) {
        List<Phone> phoneList = dataBase.getPhoneList();
        Optional<Phone> result = phoneList.stream().filter(phone -> phone.getId() == phoneId)
                .findFirst();

        result.ifPresent(phone -> {
            phone.setName(newName);
            System.out.println(result);
        });
        return null;
    }


    @Override
    public List<Phone> getAllPhones() {
        List<Phone> phoneList = dataBase.getPhoneList();
        phoneList.stream().forEach(System.out::println);
        return null;
    }

    @Override
    public List<Phone> getAllPhonesByBrand(String brand) {
        List<Phone> phoneList = dataBase.getPhoneList();
        Optional<Phone> phones = phoneList.stream().filter(phone -> phone.getBrand().equals(brand)).findFirst();
        System.out.println(phones);
        return null;
    }

    @Override
    public void deletePhoneById(int phoneId) {
        List<Phone> phoneList = dataBase.getPhoneList();
        List<Phone> phoneLi = phoneList.stream().filter(phone -> phone.getId() != phoneId).toList();

        dataBase.setPhoneList(phoneLi);
        System.out.println("Телефон с id"+phoneId+"удален из базы данных");

    }
}
