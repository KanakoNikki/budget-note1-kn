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
//        request.getSession().getAttribute("login_users");
        Users login_users = (Users)request.getSession().getAttribute("login_users");

        EntityManager em = DBUtil.createEntityManager();
        List<Budget> budgets = em.createNamedQuery("getMyAllBudgets", Budget.class)
                .setParameter("login_users", login_users).getResultList();
//        List<Budget> itemId = em.createNamedQuery("getItemId", Budget.class)
//                .setParameter("budget", budgets).getResultList();
//       List<Item> itemId = em.createNamedQuery("getItemName", Item.class)
//                .setParameter("item_id", budgets).getResultList();
//       List<Item> i = em.find(Item.class, Integer.parseInt(budgets.item));


        em.close();
        request.setAttribute("budget", budgets);
 //       request.getSession().setAttribute("login_users", login_users);
 //       request.setAttribute("itemId", itemId);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/budget/index.jsp");
        rd.forward(request, response);
    }
}