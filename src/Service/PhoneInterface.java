package Service;
import Model.Phone;
import java.util.List;

public interface PhoneInterface {
    String addPhone(Phone phone);

    Phone getPhoneById(int phoneId);

    Phone updatePhoneNameById(int phoneId, String newName);

    List<Phone> getAllPhones();

    List<Phone> getAllPhonesByBrand(String brand);

    void deletePhoneById(int phoneId);
}
