package servlets.Admin;

import entities.Member;
import entities.Order;
import persistence.MemberDao;
import persistence.OrderDao;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Alex
 *         7/1/2016
 */
@WebServlet(
        name = "admin-member-orders",
        urlPatterns = {"/admin/member       String content;" +
                "String title;/orders/*"}
)
public class MemberOrdersServlet extends BaseServlet {
    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse objec        String content;
     *                 String title;t
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String memberId = request.getPathInfo();
        String content;
        String title;

        if (memberId == null) {
            content = "error.jsp";
            title = "Error";
        } else {
            memberId = memberId.substring(1);
            MemberDao dao = (MemberDao) getServletContext().getAttribute("memberDao");
            Member member = dao.getRecordById(Integer.parseInt(memberId));

            request.setAttribute("orders", member.getMemberOrders());

            request.setAttribute("member", member);
            content = "/admin/orders.jsp";
            title = member.getFirstName() + " " + member.getLastName() + "'s Orders";
        }

        servletResponse(request, response, title, content);

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

    }
}
