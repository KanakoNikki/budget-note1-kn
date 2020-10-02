package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Budget;

public class BudgetValidator {
    public static List<String> validate(Budget b) {
        List<String> errors = new ArrayList<String>();

        String amount_error = _validateAmount(b.getAmount());
        if(!amount_error.equals("")) {
            errors.add(amount_error);
        }

        String detail_error = _validateDetail(b.getDetail());
        if(!detail_error.equals("")) {
            errors.add(detail_error);
        }

        return errors;
    }

    private static String _validateAmount(Integer amount) {
        if(amount == null || amount.equals("")) {
            return "金額を入力してください。";
        }
        return "";
    }


    private static String _validateDetail(String detail) {
        if(detail == null || detail.equals("")) {
            return "詳細を入力してください。";
        }
        return "";
    }
}
