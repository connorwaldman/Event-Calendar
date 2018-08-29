package edu.louisville.cecs.event;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DropdownBoxExampleServlet
 */
@WebServlet("/EventsServlet")
public class EventsServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public EventsServlet()
    {
        super();
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String url = "/EventsProcessor.jsp";
    	String id = request.getParameter("id");
        String venue = request.getParameter("venue");
        String username = request.getParameter("username");
        String performer = request.getParameter("performer");
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        session.setAttribute("venue", venue);
        session.setAttribute("username", username);
        session.setAttribute("performer", performer);
       
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
    }  
        
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {    
        doGet(request, response);  
    }
}
