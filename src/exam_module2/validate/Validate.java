package exam_module2.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static boolean isRequired(String value) {
        return value != null && !value.trim().isEmpty();
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[-\\s]?\\d{3}[-\\s]?\\d{4}$";  // Định dạng số điện thoại
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";  // Định dạng email
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
