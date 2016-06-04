package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * @author Alex
 * 5/18/2016
 */
public class BaseServlet extends HttpServlet {
    protected String url;
    protected String title;
    protected String content;
    public void servletResponse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.jsp";

        request.setAttribute("pageContent", content);
        request.setAttribute("pageTitle", title);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void redirecting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }





}
