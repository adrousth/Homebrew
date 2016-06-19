package servlets;

import entities.Order;
import persistence.DataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Alex
 *         6/6/2016
 */
@WebServlet(
        name = "admin-orders",
        urlPatterns = {"/admin/orders"}
)
public class OrdersServlet extends BaseServlet {
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

        Map<String, Object> map = new TreeMap<>();


        map.put("firstName", request.getParameter("firstName"));
        map.put("lastName", request.getParameter("lastName"));
        map.put("orderStatus", request.getParameter("orderStatus"));
        map.put("type", request.getParameterValues("orderType"));



        content = "/admin/orders.jsp";
        title = "Orders";
        DataAccessObject dao = (DataAccessObject) getServletContext().getAttribute("dao");
        dao.setType(Order.class);

        map.put("type", "HOP");
        map.put("orderStatus", "unfilled");
        Set<Order> orders = new TreeSet<>(dao.searchMultipleParams(map));
        request.setAttribute("orders", orders);
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

    }
}
