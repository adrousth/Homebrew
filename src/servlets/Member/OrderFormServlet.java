package servlets.Member;

import entities.Asset;
import entities.OrderResults;
import entities.Results;
import org.opensaml.xml.encryption.P;
import persistence.AssetDao;
import persistence.DataAccessObject;
import persistence.OrderDao;
import servlets.BaseServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Alex
 *         5/27/2016
 */
@WebServlet(
        name = "order-form",
        urlPatterns = { "/member/orderForm" }
)
public class OrderFormServlet extends BaseServlet {
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
        content = "/member/orderForm.jsp";
        title = "Order Form";
        AssetDao dao = (AssetDao) getServletContext().getAttribute("assetDao");
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

        OrderResults results = new OrderResults();
        OrderDao orderDao = (OrderDao) getServletContext().getAttribute("orderDao");
        Map<String, String> orderItems = new TreeMap<>();
        String notes = request.getParameter("notes");
        results.setNotes(notes);
        for (int i = 0; i < 5; i++) {
            if (!orderItems.containsKey(request.getParameter("hop" + (i + 1))) || request.getParameter("hop" + (i + 1)).equals("")) {
                orderItems.put(request.getParameter("hop" + (i + 1)), request.getParameter("hop" + (i + 1) + "Qty"));
            } else {
                results.setSuccess(false);
                results.addMessage("Please do not select a hop more than once");
                request.setAttribute("results", results);
                doGet(request, response);
            }
        }
        results = orderDao.orderFromWebForm(orderItems, request.getRemoteUser(), "HOP", notes);

        request.setAttribute("results", results);

        doGet(request, response);
    }

}
