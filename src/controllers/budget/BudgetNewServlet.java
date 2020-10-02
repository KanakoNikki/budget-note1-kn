package controllers.budget;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Budget;

/**
 * Servlet implementation class BudgetNewServlet
 */
@WebServlet("/budget/new")
public class BudgetNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BudgetNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Budget b = new Budget();
        b.setBudget_date(new Date(System.currentTimeMillis()));
        request.setAttribute("budget", b);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/budget/new.jsp");
        rd.forward(request, response);
    }

}