package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Users;

public class UsersValidator {
    public static List<String> validate(Users u) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(u.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = _validatePassword(u.getPassword());
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String _validatePassword(String password) {
        // パスワードを変更する場合のみ実行
        if(password == null || password.equals("")) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}
