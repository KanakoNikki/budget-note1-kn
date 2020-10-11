package controllers.budget;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Budget;
import models.Users;
import utils.DBUtil;

/**
 * Servlet implementation class UsersIndexServlet
 */
@WebServlet("/budget/index")
public class BudgetIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BudgetIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users login_users = (Users)request.getSession().getAttribute("login_users");

        EntityManager em = DBUtil.createEntityManager();
        List<Budget> budgets = em.createNamedQuery("getMyAllBudgets", Budget.class)
                .setParameter("login_users", login_users).getResultList();

/*        long budgetSum;
        try{
            budgetSum = (long)em.createNamedQuery("sumAllBudgets", Long.class)
                    .setParameter("login_users", login_users).getSingleResult();
        }catch(NullPointerException e){
            budgetSum = 0;
        }↑こっちでもOK！！*/

        long budgetSum = 0;
        Long budgetSumLong = null;
        budgetSumLong = em.createNamedQuery("sumAllBudgets",Long.class).setParameter("login_users", login_users).getSingleResult();
        if(budgetSumLong != null){
            budgetSum=(long)budgetSumLong;
        }

        em.close();
        request.setAttribute("budget", budgets);
        request.setAttribute("budgetSum", budgetSum);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/budget/index.jsp");
        rd.forward(request, response);
    }
}