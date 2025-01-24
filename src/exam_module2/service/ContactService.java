package exam_module2.service;

import exam_module2.models.Contact;
import exam_module2.util.ReadAndWrite;

import java.util.ArrayList;
import java.util.List;

public class ContactService implements IContactService {
    private static final String FILE_PATH = "src/exam_module2/data/contacts.csv";

    @Override
    public List<Contact> readContactsFromFile() {
        return ReadAndWrite.readFile(FILE_PATH);
    }

    // Ghi danh bạ vào file CSV
    @Override
    public void writeContactsToFile(List<Contact> contactList, boolean append) {
        ReadAndWrite.writeFile(FILE_PATH, contactList, append);
    }

    @Override
    public void addContact(Contact contact) {
        List<Contact> contacts = readContactsFromFile();
        contacts.add(contact);
        writeContactsToFile(contacts, false);
    }

    @Override
    public void updateContact(Contact updatedContact) {
        List<Contact> contacts = readContactsFromFile();
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(updatedContact.getPhoneNumber())) {
                contacts.set(i, updatedContact); // Cập nhật contact
                break;
            }
        }
        writeContactsToFile(contacts, false);
    }

    @Override
    public void deleteContact(String phoneNumber) {
        List<Contact> contacts = readContactsFromFile();
        contacts.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumber));
        writeContactsToFile(contacts, false);
    }

    @Override
    public List<Contact> searchContacts(String keyword) {
        List<Contact> contacts = readContactsFromFile();
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().contains(keyword) || contact.getFullName().contains(keyword)) {
                result.add(contact);
            }
        }
        return result;
    }
}
