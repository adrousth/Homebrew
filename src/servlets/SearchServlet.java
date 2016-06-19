package servlets;

import entities.Member;
import persistence.DataAccessObject;
import persistence.MemberDao;
import persistence.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author Alex
 *         6/15/2016
 */
@WebServlet(
        name = "searching",
        urlPatterns = {"/search/*"}
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

        if (searchType == null) {
            response.sendRedirect("/search");
        } else if (searchType.equals("/members")) {
            MemberDao dao = (MemberDao) getServletContext().getAttribute("memberDao");

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            Set memberList = dao.searchMemberByName(firstName, lastName);
            request.setAttribute("members", memberList);
            title = "Member Search";
            content = "admin/members.jsp";
        } else if (searchType.equals("/orders")) {
            OrderDao dao = (OrderDao) getServletContext().getAttribute("orderDao");
            String orderStatus = request.getParameter("orderStatus");
            String types = request.getParameter("type");

            Set orderList = dao.searchOrders(orderStatus, types);

            request.setAttribute("orders", orderList);

            title = "Order Search";
            content = "admin/orders.jsp";
        } else {
            response.sendRedirect("/search");
        }

        servletResponse(request, response);



    }
}
