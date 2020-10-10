package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Budget;

public class BudgetValidator {
    public static List<String> validate(Budget b) {
        List<String> errors = new ArrayList<String>();

        /*2020年10月09日時点、amountのバリデーター作成中です*/

/*        String amountNull_error = _validateAmount(b.getAmount());
        if(!amountNull_error.equals("")) {
            errors.add(amountNull_error);
        }

        String tooBigAmount_error = _validateAmount(b.getAmount());
        if(!tooBigAmount_error.equals("")) {
            errors.add(tooBigAmount_error);
        }*/

        String detail_error = _validateDetail(b.getDetail());
        if(!detail_error.equals("")) {
            errors.add(detail_error);
        }

        return errors;
    }


/*    private static String _validateAmount(Integer amount) {
        if(amount >= 9999999) {
            return "金額は\'999,999以下で設定してください。";
        }
        return"";
    }*/


    private static String _validateDetail(String detail) {
        if(detail == null ||detail.equals("")) {
            return "詳細を入力してください。";
        }
        return"";
    }
}
