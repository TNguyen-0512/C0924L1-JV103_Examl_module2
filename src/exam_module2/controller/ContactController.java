package exam_module2.controller;

import exam_module2.models.Contact;
import exam_module2.service.ContactService;
import exam_module2.service.IContactService;
import exam_module2.validate.Validate;

import java.util.List;
import java.util.Scanner;

public class ContactController {
    private IContactService contactService;
    private Scanner scanner;

    public ContactController() {
        this.contactService = new ContactService();
        this.scanner = new Scanner(System.in);
    }

    // Hiển thị menu chính
    public void displayMenu() {
        int choice;

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Lưu vào file");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAllContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    searchContact();
                    break;
                case 6:
                    readFromFile();
                    break;
                case 7:
                    writeToFile();
                    break;
                case 0:
                    System.out.println("Thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    private void showAllContacts() {
        List<Contact> contacts = contactService.readContactsFromFile();
        if (contacts.isEmpty()) {
            System.out.println("Danh bạ rỗng.");
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }

    private void addNewContact() {
        String phoneNumber, group, fullName, gender, address, birthday, email;

        while (true) {
            System.out.print("Nhập số điện thoại: ");
            phoneNumber = scanner.nextLine();
            if (!Validate.isRequired(phoneNumber)) {
                System.out.println("Số điện thoại là bắt buộc!");
            } else if (!Validate.isValidPhoneNumber(phoneNumber)) {
                System.out.println("Số điện thoại không hợp lệ, vui lòng nhập lại.");
            } else {
                break;
            }
        }

        System.out.print("Nhập nhóm: ");
        group = scanner.nextLine();
        while (!Validate.isRequired(group)) {
            System.out.println("Nhóm là bắt buộc!");
            System.out.print("Nhập nhóm: ");
            group = scanner.nextLine();
        }

        System.out.print("Nhập họ tên: ");
        fullName = scanner.nextLine();
        while (!Validate.isRequired(fullName)) {
            System.out.println("Họ tên là bắt buộc!");
            System.out.print("Nhập họ tên: ");
            fullName = scanner.nextLine();
        }

        System.out.print("Nhập giới tính: ");
        gender = scanner.nextLine();
        while (!Validate.isRequired(gender)) {
            System.out.println("Giới tính là bắt buộc!");
            System.out.print("Nhập giới tính: ");
            gender = scanner.nextLine();
        }

        System.out.print("Nhập địa chỉ: ");
        address = scanner.nextLine();
        while (!Validate.isRequired(address)) {
            System.out.println("Địa chỉ là bắt buộc!");
            System.out.print("Nhập địa chỉ: ");
            address = scanner.nextLine();
        }

        System.out.print("Nhập ngày sinh: ");
        birthday = scanner.nextLine();
        while (!Validate.isRequired(birthday)) {
            System.out.println("Ngày sinh là bắt buộc!");
            System.out.print("Nhập ngày sinh: ");
            birthday = scanner.nextLine();
        }

        // Kiểm tra email
        while (true) {
            System.out.print("Nhập email: ");
            email = scanner.nextLine();
            if (!Validate.isRequired(email)) {
                System.out.println("Email là bắt buộc!");
            } else if (!Validate.isValidEmail(email)) {
                System.out.println("Email không hợp lệ, vui lòng nhập lại.");
            } else {
                break;
            }
        }

        Contact contact = new Contact(phoneNumber, group, fullName, gender, address, birthday, email);
        contactService.addContact(contact);
        System.out.println("Đã thêm mới danh bạ.");
    }

    private void updateContact() {
        System.out.print("Nhập số điện thoại cần cập nhật: ");
        String phoneNumber = scanner.nextLine();
        List<Contact> contacts = contactService.readContactsFromFile();

        Contact contactToUpdate = null;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                contactToUpdate = contact;
                break;
            }
        }

        if (contactToUpdate != null) {
            System.out.println("Cập nhật thông tin cho: " + contactToUpdate);

            // Kiểm tra và nhập lại các trường bắt buộc
            System.out.print("Nhập nhóm mới: ");
            String group = scanner.nextLine();
            while (!Validate.isRequired(group)) {
                System.out.println("Nhóm là bắt buộc!");
                System.out.print("Nhập nhóm mới: ");
                group = scanner.nextLine();
            }
            contactToUpdate.setGroup(group);

            System.out.print("Nhập họ tên mới: ");
            String fullName = scanner.nextLine();
            while (!Validate.isRequired(fullName)) {
                System.out.println("Họ tên là bắt buộc!");
                System.out.print("Nhập họ tên mới: ");
                fullName = scanner.nextLine();
            }
            contactToUpdate.setFullName(fullName);

            System.out.print("Nhập giới tính mới: ");
            String gender = scanner.nextLine();
            while (!Validate.isRequired(gender)) {
                System.out.println("Giới tính là bắt buộc!");
                System.out.print("Nhập giới tính mới: ");
                gender = scanner.nextLine();
            }
            contactToUpdate.setGender(gender);

            System.out.print("Nhập địa chỉ mới: ");
            String address = scanner.nextLine();
            while (!Validate.isRequired(address)) {
                System.out.println("Địa chỉ là bắt buộc!");
                System.out.print("Nhập địa chỉ mới: ");
                address = scanner.nextLine();
            }
            contactToUpdate.setAddress(address);

            System.out.print("Nhập ngày sinh mới: ");
            String birthday = scanner.nextLine();
            while (!Validate.isRequired(birthday)) {
                System.out.println("Ngày sinh là bắt buộc!");
                System.out.print("Nhập ngày sinh mới: ");
                birthday = scanner.nextLine();
            }
            contactToUpdate.setBirthday(birthday);

            // Kiểm tra email
            while (true) {
                System.out.print("Nhập email mới: ");
                String email = scanner.nextLine();
                if (!Validate.isRequired(email)) {
                    System.out.println("Email là bắt buộc!");
                } else if (!Validate.isValidEmail(email)) {
                    System.out.println("Email không hợp lệ, vui lòng nhập lại.");
                } else {
                    contactToUpdate.setEmail(email);
                    break;
                }
            }

            contactService.updateContact(contactToUpdate);
            System.out.println("Cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy danh bạ với số điện thoại đã nhập.");
        }
    }

    private void deleteContact() {
        System.out.print("Nhập số điện thoại cần xóa: ");
        String phoneNumber = scanner.nextLine();
        List<Contact> contacts = contactService.readContactsFromFile();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                found = true;
                System.out.print("Bạn có chắc chắn muốn xóa không? (Y/N): ");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    contactService.deleteContact(phoneNumber);
                    System.out.println("Đã xóa danh bạ.");
                } else {
                    System.out.println("Hủy bỏ xóa danh bạ.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy danh bạ với số điện thoại đã nhập.");
        }
    }

    private void searchContact() {
        System.out.print("Nhập từ khóa để tìm kiếm (số điện thoại hoặc tên): ");
        String keyword = scanner.nextLine();
        List<Contact> results = contactService.searchContacts(keyword);

        if (results.isEmpty()) {
            System.out.println("Không tìm thấy danh bạ phù hợp.");
        } else {
            for (Contact contact : results) {
                System.out.println(contact);
            }
        }
    }

    private void readFromFile() {
        List<Contact> contacts = contactService.readContactsFromFile();
        if (contacts.isEmpty()) {
            System.out.println("Danh bạ hiện tại rỗng.");
        } else {
            System.out.println("Đã tải danh bạ từ file.");
            showAllContacts();
        }
    }

    private void writeToFile() {
        List<Contact> contacts = contactService.readContactsFromFile();
        contactService.writeContactsToFile(contacts, false);
        System.out.println("Đã lưu danh bạ vào file.");
    }
}
