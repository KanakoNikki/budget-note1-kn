package controllers.budget;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Budget;
import models.Item;
import models.Users;
import models.validators.BudgetValidator;
import utils.DBUtil;

/**
 * Servlet implementation class BudgetCreateServlet
 */
@WebServlet("/budget/create")
public class BudgetCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BudgetCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Budget b = new Budget();

            b.setUsers((Users)request.getSession().getAttribute("login_users"));

            Date budget_date = new Date(System.currentTimeMillis());
            String bd_str = request.getParameter("budget_date");
            if(bd_str != null && !bd_str.equals("")) {
                budget_date = Date.valueOf(request.getParameter("budget_date"));
            }
            b.setBudget_date(budget_date);

            Item i = em.find(Item.class, Integer.parseInt(request.getParameter("item_id")));
            b.setItem(i);

            List<Item> itemList = em.createNamedQuery("getAllItems", Item.class)
                    .getResultList();
            b.setDetail(request.getParameter("detail"));

            String amountNull_error= new String();
            String tooBigAmount_error= new String();

            try {
                    if(request.getParameter("amount")==null || request.getParameter("amount").equals("")){
                        amountNull_error="金額を入力してください。";
                    } else if(Integer.parseInt(request.getParameter("amount"))>999999){
                        tooBigAmount_error = "金額は999,999以下で入力してください。";
                    } else {
                        b.setAmount(Integer.parseInt(request.getParameter("amount")));
                    }
                } catch(NumberFormatException e) {
                    tooBigAmount_error = "金額は999,999以下で入力してください。";
            }

            List<String> errors = BudgetValidator.validate(b);
            if(!amountNull_error.equals("")) {
                errors.add(amountNull_error);
            }
            if(!tooBigAmount_error.equals("")) {
                errors.add(tooBigAmount_error);
            }

            if(errors.size() > 0 ) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("budget", b);
                request.setAttribute("errors", errors);
                request.setAttribute("itemList", itemList);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/budget/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(b);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                request.setAttribute("_token", request.getSession().getId());
                response.sendRedirect(request.getContextPath() + "/budget/index");
            }
        }
    }
}