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
import models.validators.BudgetValidator;
import utils.DBUtil;

/**
 * Servlet implementation class BudgetUpdateServlet
 */
@WebServlet("/budget/update")
public class BudgetUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BudgetUpdateServlet() {
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

            Budget b = em.find(Budget.class, (Integer)(request.getSession().getAttribute("budget_id")));

            b.setBudget_date(Date.valueOf(request.getParameter("budget_date")));
            Item i = em.find(Item.class, Integer.parseInt(request.getParameter("item_id")));
            b.setItem(i);
            b.setDetail(request.getParameter("detail"));
            b.setAmount(Integer.parseInt(request.getParameter("amount")));

            List<String> errors = BudgetValidator.validate(b);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("budget", b);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/budget/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("budget_id");
                response.sendRedirect(request.getContextPath() + "/budget/index");
            }
        }
    }

}