package exam_module2.util;

import exam_module2.models.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWrite {
    public static void writeFile(String filePath, List<Contact> contactList, boolean append) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
            for (Contact contact : contactList) {
                writer.write(contactToCSV(contact));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    public static List<Contact> readFile(String filePath) {
        List<Contact> contactList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return contactList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = csvToContact(line);
                contactList.add(contact);
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }

        return contactList;
    }

    private static String contactToCSV(Contact contact) {
        return contact.getPhoneNumber() + "," +
                contact.getGroup() + "," +
                contact.getFullName() + "," +
                contact.getGender() + "," +
                contact.getAddress() + "," +
                contact.getBirthday() + "," +
                contact.getEmail();
    }

    private static Contact csvToContact(String csvLine) {
        String[] values = csvLine.split(",");

        if (values.length == 7) {
            return new Contact(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
        }
        return null;
    }
}
