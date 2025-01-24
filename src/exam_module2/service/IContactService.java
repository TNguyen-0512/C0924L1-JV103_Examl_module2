package exam_module2.service;

import exam_module2.models.Contact;

import java.util.List;

public interface IContactService {
    List<Contact> readContactsFromFile();
    void writeContactsToFile(List<Contact> contactList, boolean append);
    void addContact(Contact contact);
    void updateContact(Contact updatedContact);
    void deleteContact(String phoneNumber);
    List<Contact> searchContacts(String keyword);
}
