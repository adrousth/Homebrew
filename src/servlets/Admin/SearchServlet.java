package servlets.Admin;

import persistence.MemberDao;
import persistence.OrderDao;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author Alex
 *         6/15/2016
 */
@WebServlet(
        name = "searching",
        urlPatterns = {"/admin/search/*"}
)
public class SearchServlet extends BaseServlet {
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

        String searchType = request.getPathInfo();
        String title;
        String content;

        if (searchType == null) {
            response.sendRedirect("/admin/search");
            return;
        } else if (searchType.equals("/members")) {
            MemberDao dao = (MemberDao) getServletContext().getAttribute("memberDao");

            String firstName = "";
            String lastName = "";

            if (request.getParameter("firstName") != null) {
                firstName = request.getParameter("firstName");
            }
            if (request.getParameter("lastName") != null) {
                lastName = request.getParameter("lastName");
            }


            Set memberList = dao.searchMemberByName(firstName, lastName);
            request.setAttribute("members", memberList);
            title = "Member Search";
            content = "admin/members.jsp";

        } else if (searchType.equals("/orders")) {

            OrderDao dao = (OrderDao) getServletContext().getAttribute("orderDao");

            String orderStatus = "";
            String type = "";

            if (request.getParameter("orderStatus") != null) {
                orderStatus = request.getParameter("orderStatus");
            }
            if (request.getParameter("type") != null) {
                type = request.getParameter("type");
            }

            Set orderList = dao.searchOrdersByStatus(orderStatus, type);
            System.out.println("orders");
            request.setAttribute("type", type);
            request.setAttribute("orderStatus", orderStatus);
            request.setAttribute("orders", orderList);

            title = "Order Search";
            content = "admin/orders.jsp";
        } else {
            response.sendRedirect("/admin/search");
            return;
        }

        servletResponse(request, response, title, content);



    }
}
