package exam_module2.view;

import exam_module2.controller.ContactController;
import exam_module2.models.Contact;
import exam_module2.util.ReadAndWrite;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ContactController c = new ContactController();
        c.displayMenu();
    }
}
