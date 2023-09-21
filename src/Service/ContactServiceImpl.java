package Service;
import DataBase.DataBase;
import Model.Contact;
import Model.Phone;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ContactServiceImpl implements ContactInterface {
    DataBase dataBase = new DataBase();

    public ContactServiceImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String addContactToPhone(int phoneId, Contact contact) {
        List<Phone> phoneList = dataBase.getPhoneList();

        Optional<Phone> updatedPhone = phoneList.stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst();

        if (updatedPhone.isPresent()) {
            Phone phone = updatedPhone.get();
            List<Contact> contacts = phone.getContacts();
            contacts.add(contact);
            dataBase.setPhoneList(phoneList);

            System.out.println(updatedPhone);

            return "Контакт успешно добавлен к телефону с ID " + phoneId;
        } else {
            return "Телефон с ID " + phoneId + " не найден";
        }
    }


    @Override
    public Contact findContactByName(int phoneId, String contactName) {
        List<Phone> phoneList = dataBase.getPhoneList();

        Optional<Contact> result = phoneList.stream()
                .filter(phone -> phone.getId() == phoneId)
                .flatMap(phone -> phone.getContacts().stream())
                .filter(contact -> contact.getName().contains(contactName))
                .findFirst();

        if (result.isPresent()) {
            System.out.println("Найден контакт: " + result.get().getName() + ", Телефон: " + result.get().getPhoneNumber());
            return result.get();
        } else {
            System.out.println("Контакт с именем " + contactName + " не найден.");
            return null;
        }
    }


    @Override
    public Contact findContactByPhoneNumber(int phoneId, String phoneNumber) {
        List<Phone> phoneList = dataBase.getPhoneList();

        Optional<Contact> result = phoneList.stream()
                .filter(phone -> phone.getId() == phoneId)
                .flatMap(phone -> phone.getContacts().stream())
                .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
                .findFirst();

        if (result.isPresent()) {
            System.out.println("Найден контакт с номером: " + result.get().getPhoneNumber() + "\n" + result.get());
            return result.get();
        } else {
            System.out.println("Контакт с номером " + phoneNumber + " не найден.");
            return null;
        }
    }


    @Override
    public List<Contact> sortContactsByName(int phoneId) {
        List<Phone> contactList = dataBase.getPhoneList();
        Optional<Phone> optionalPhone = contactList.stream()
                .filter(phone -> phone.getId() == phoneId).findFirst();
        List<Contact> contactsList = optionalPhone.get().getContacts();
        List<Contact> result = contactsList.stream().sorted(Comparator.comparing(Contact::getName)).toList();
        System.out.println(result);


        return null;
    }

    @Override
    public void deleteContactByNameFromPhone(int phoneId, String contactName) {
        List<Phone> phoneList = dataBase.getPhoneList();

        Optional<Phone> optionalPhone = phoneList.stream()
                .filter(phone -> phone.getId() == phoneId)
                .findFirst();

        if (optionalPhone.isPresent()) {
            Phone phone = optionalPhone.get();
            List<Contact> contacts = phone.getContacts();

            contacts.removeIf(contact -> contact.getName().equals(contactName));

            phone.setContacts(contacts);
            dataBase.setPhoneList(phoneList);

            System.out.println("Контакт с именем " + contactName + " удален из телефона с ID " + phoneId);
        } else {
            System.out.println("Телефон с ID " + phoneId + " не найден");
        }
    }

}
