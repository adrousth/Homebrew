package servlets;
/**
 * Created by Alex on 5/18/2016.
 */

import entities.Results;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "logout}",
        urlPatterns = {"/logout"}
)
public class LogoutServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Results results = new Results();
        if (request.getRemoteUser() == null) {
            results.setSuccess(false);
            results.setType("not logged in");
        } else {
            results.setSuccess(true);
            request.logout();
            results.setType("Logged out");
            request.getSession().setAttribute("user", null);
        }

        getServletContext().setAttribute("results", results);
        response.sendRedirect("/");
    }
}
