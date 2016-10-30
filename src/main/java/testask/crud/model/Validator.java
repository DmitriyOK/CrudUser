package testask.crud.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmitriy on 30.10.2016.
 *
 * User data validator when create new user.
 *
 */
public class Validator {

    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z]{1,45}\\s?[A-Za-z]{1,45}");
    private static final Pattern AGE_PATTERN = Pattern.compile("\\d{0,2}");


    public static boolean validateDataUser(User user){

        return (NAME_PATTERN.matcher(user.getName()).matches() &&
                AGE_PATTERN.matcher(String.valueOf(user.getAge())).matches());
    }

    public static boolean validateNumber(int value) {

        boolean find = false;

        int[] values = {10, 15, 25};

        for (int n : values)
            if (value == n)
                find = true;

        return find;
    }
}
