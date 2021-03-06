package edu.louisville.cecs.event;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.louisville.cecs.controllers.ConnectionPool;
import edu.louisville.cecs.controllers.UsersController;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private static Connection connection       = null;
    private UsersController   uc;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet()
    {
        super();
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = "/Welcome.jsp";
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        if (user == null || user.length() == 0)
        {
            url = "/Login.jsp";
            request.setAttribute("error", "User name must not be empty");
        }
        else
        {
            ConnectionPool pool = ConnectionPool.getInstance("jdbc/cawald03");
            connection = pool.getConnection();
            if (connection != null)
            {
                uc = new UsersController(connection);
                if (uc.findUser(user, password) == true)
                {
                    request.setAttribute("fullname", uc.getFullName());
                }
                else
                {
                    url = "/Login.jsp";
                    request.setAttribute("error", "User does not exist");
                } // end if
                pool.freeConnection(connection);
            }
            else
            {
                System.out.println("Connection is null");
            } // end if
        } // end if
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
