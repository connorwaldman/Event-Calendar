package edu.louisville.cecs.event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.louisville.cecs.controllers.ConnectionPool;
import edu.louisville.cecs.controllers.SQLUtil;
/**
 * Servlet implementation class CompanySQLProcessorServlet
 */
@WebServlet("/EventsSQLServlet")
public class EventsSQLServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public ResultSet          resultSet        = null;
    public Statement          statement        = null;
    public String             sqlResult        = "";
    private static Connection  connection       = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventsSQLServlet()
    {
        super();
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String sqlStatement = request.getParameter("sqlStatement");
        ConnectionPool pool = ConnectionPool.getInstance("jdbc/cawald03");
        connection = pool.getConnection();
        if (connection != null)
        {
            parseAndExecute(sqlStatement, connection);
            sqlStatement = "";
            pool.freeConnection(connection);
            HttpSession session = request.getSession();
            session.setAttribute("sqlResult", sqlResult);
            session.setAttribute("sqlStatement", sqlStatement);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/EventsProcessor.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            System.out.println("Connection is null");
        } // end if
    }
    private void parseAndExecute(String sqlStatement, Connection connection)
    {
        try
        {
            statement = connection.createStatement();
            sqlStatement = sqlStatement.trim();
            if (sqlStatement.length() >= 6)
            {
                String sqlType = sqlStatement.substring(0, 6);
                if (sqlType.equalsIgnoreCase("select"))
                {
                    try
                    {
                        resultSet = statement.executeQuery(sqlStatement);
                        sqlResult = SQLUtil.getHtmlTable(resultSet);
                        resultSet.close();
                    }
                    catch (SQLException e)
                    {
                        System.out.println("Cannot execute query");
                        e.printStackTrace();
                    }
                }
                else
                {
                    try
                    {
                        System.out.println(sqlStatement);
                        int i = -1;
                        i = statement.executeUpdate(sqlStatement);
                        if (i == 0)
                        {
                            sqlResult = "The statement executed successfully.";
                        }
                        else
                        {
                            sqlResult = "The statement executed successfully.<br>" + i + " row(s) affected.";
                        } // end if
                    }
                    catch (SQLException e)
                    {
                        System.out.println("Cannot execute query");
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Could not create statment: " + e.getMessage());
        }
    }
}
