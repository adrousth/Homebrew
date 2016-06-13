package servlets;

import entities.Order;
import persistence.DataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alex
 *         6/10/2016
 */
@WebServlet(
        name = "admin-order}",
        urlPatterns = {"/admin/order/*"}
)
public class UpdateOrderServlet extends BaseServlet {
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

        String orderId = request.getPathInfo();

        if (orderId == null) {
            response.sendRedirect("/");
        } else if (!orderId.equals("")) {
            DataAccessObject dao = (DataAccessObject) getServletContext().getAttribute("dao");
            dao.setType(Order.class);
            Order order = (Order) dao.getRecordById(Integer.parseInt(orderId.substring(1)));
            request.setAttribute("order", order);

            title = "Order Update Form";
        }
        content = "/admin/orderUpdate.jsp";

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
        String status = request.getParameter("status");
        String orderId = request.getParameter("orderId");

        DataAccessObject dao = (DataAccessObject) getServletContext().getAttribute("dao");

        dao.setType(Order.class);

    }
}
