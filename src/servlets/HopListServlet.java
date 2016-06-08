package servlets;

import entities.Asset;
import persistence.DataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Alex
 *         6/2/2016
 */
@WebServlet(
        name = "hops}",
        urlPatterns = {"/member/hops", "/member/grains"}
)
public class HopListServlet extends BaseServlet {
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
        content = "/hops.jsp";
        title = "Hop List";
        DataAccessObject dao = (DataAccessObject) getServletContext().getAttribute("dao");
        dao.setType(Asset.class);
        ArrayList<Asset> hops = (ArrayList<Asset>) dao.getRecords("type", "HOP");
        request.setAttribute("hops", hops);
        servletResponse(request, response);
    }
}
