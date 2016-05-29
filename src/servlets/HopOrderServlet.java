package servlets;

import entities.Asset;
import persistence.DataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Alex
 *         5/27/2016
 */
@WebServlet(
        name = "order-form",
        urlPatterns = { "/orderForm" }
)
public class HopOrderServlet extends BaseServlet {
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
        content = "/orderForm.jsp";
        title = "Order Form";
        DataAccessObject dao = (DataAccessObject) getServletContext().getAttribute("dao");
        dao.setType(Asset.class);
        ArrayList<Asset> hops = (ArrayList<Asset>) dao.getRecords("type", "HOP");
        request.setAttribute("hops", hops);
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
        ArrayList<String> hops = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hops.add(request.getParameter("hop" + (i + 1)));
        }
        ArrayList<String> qty = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            qty.add(request.getParameter("hop" + (i + 1) + "Qty"));
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(hops.get(i) + " " + qty.get(i) + " ounces");
        }
        doGet(request, response);
    }

}
