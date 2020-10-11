package controllers.budget;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Budget;
import utils.DBUtil;

/**
 * Servlet implementation class BudgetDestroyServlet
 */
@WebServlet("/budget/destroy")
public class BudgetDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BudgetDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Budget b = em.find(Budget.class, (Integer)(request.getSession().getAttribute("budget_id")));

            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("budget_id");
            request.getSession().setAttribute("flush", "選択した記録を削除しました。");
            response.sendRedirect(request.getContextPath() + "/budget/index");
        }
    }
}
