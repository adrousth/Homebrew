package servlets;

import entities.MemberResults;
import persistence.MemberDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alex
 *         6/19/2016
 */
@WebServlet(
        name = "admin-newMember",
        urlPatterns = {"/admin/newMember"}
)
public class NewMemberServlet extends BaseServlet {
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

        title = "New Member Form";
        content = "/admin/newMemberForm.jsp";

        servletResponse(request, response);
    }

    /**
     * Handles HTTP POST requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("home");
        String email = request.getParameter("email");

        MemberDao dao = (MemberDao) getServletContext().getAttribute("memberDao");

        MemberResults results = dao.createNewMemberFromForm(firstName, lastName, email, phone);

        request.setAttribute("results", results);

        doGet(request, response);
    }
}
