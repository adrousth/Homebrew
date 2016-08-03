package servlets.Admin;

import entities.Order;
import entities.Results;
import persistence.DataAccessObject;
import persistence.OrderDao;
import servlets.BaseServlet;

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

        String title = "";
        String orderId = request.getPathInfo();
        System.out.println(orderId);
        if (orderId == null) {
            response.sendRedirect("/");
        } else if (!orderId.equals("")) {
            OrderDao dao = (OrderDao) getServletContext().getAttribute("orderDao");

            Order order = dao.getRecordById(Integer.parseInt(orderId.substring(1)));
            request.setAttribute("order", order);

            title = "Order Update Form";
        }
        String content = "/admin/orderUpdate.jsp";

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
        String status = request.getParameter("status");
        String orderId = request.getParameter("orderId");
        System.out.println(status);
        System.out.println(orderId);

        OrderDao dao = (OrderDao) getServletContext().getAttribute("orderDao");
        Order order = dao.getRecordById(Integer.parseInt(orderId));
        System.out.println(order.getOrderId());
        order.setOrderStatus(status);
        Results results = new Results();
        if (dao.updateOrder(order)) {

            results.setType("Success");
            results.addMessage("Order was updated.");
            results.setSuccess(true);
        } else {
            results.setType("Error");
            results.addMessage("Order was not updated.");
        }

        request.setAttribute("order", order);
        request.setAttribute("results", results);

        String content = "/admin/orderUpdate.jsp";
        String title = "Order Update Form";

        servletResponse(request, response, title, content);



    }
}
