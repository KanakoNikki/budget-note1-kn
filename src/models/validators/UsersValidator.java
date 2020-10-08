package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Users;
import utils.DBUtil;

public class UsersValidator {
    public static List<String> validate(Users u, Boolean email_duplicate_check_flag) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(u.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String email_error = _validateEmail(u.getEmail(), email_duplicate_check_flag);
        if(!email_error.equals("")) {
            errors.add(email_error);
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

    private static String _validateEmail(String email, Boolean email_duplicate_check_flag) {
        if(email == null || email.equals("")) {
            return "メールアドレスを入力してください。";
        }

        if(email_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredEmail", Long.class)
                                           .setParameter("email", email)
                                             .getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたEmailアドレスの情報はすでに存在しています。";
            }
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
