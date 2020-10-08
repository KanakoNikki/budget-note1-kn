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
import models.Item;
import models.Users;
import utils.DBUtil;

/**
 * Servlet implementation class BudgetEditServlet
 */
@WebServlet("/budget/edit")
public class BudgetEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BudgetEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Budget b = em.find(Budget.class, Integer.parseInt(request.getParameter("id")));
        List<Item> itemList = em.createNamedQuery("getAllItems", Item.class)
                .getResultList();

        em.close();

        Users login_users = (Users)request.getSession().getAttribute("login_users");

        if(b != null && login_users.getId() == b.getUsers().getId()) {
            request.setAttribute("budget", b);
            request.setAttribute("itemList", itemList);
//            request.getSession().setAttribute("login_users", login_users);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("budget_id", b.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/budget/edit.jsp");
        rd.forward(request, response);
    }

}
