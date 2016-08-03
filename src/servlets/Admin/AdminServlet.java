package servlets.Admin;

import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alex
 * 5/18/2016
 */
@WebServlet(
        name = "admin",
        urlPatterns = {"/admin"}
)
public class AdminServlet extends BaseServlet {

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
        String content = "/admin/admin.jsp";
        String title = "Admin Home";

        servletResponse(request, response, title, content);
    }
}
