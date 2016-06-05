package servlets;

import entities.Asset;
import entities.Results;
import persistence.DataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alex
 *         6/4/2016
 */
@WebServlet(
        name = "hop",
        urlPatterns = {"/admin/hop/*", "/admin/hop", "/admin/hop/"}
)
public class HopFormServlet extends BaseServlet {
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

        String assetId = request.getPathInfo();
        System.out.println(assetId);
        String button = "";
        if (assetId == null) {
            title = "New Hop Form";
            button = "Submit";
        } else if (!assetId.equals("")) {
            DataAccessObject dao = (DataAccessObject) getServletContext().getAttribute("dao");
            dao.setType(Asset.class);
            Asset asset = (Asset) dao.getRecordById(Integer.parseInt(assetId.substring(1)));
            request.setAttribute("hop", asset);

            title = asset.getName() + " Update Form";
            button = "Update";
        }
        content = "/admin/hopForm.jsp";
        request.setAttribute("button", button);
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

        String name = request.getParameter("hopName");
        String currentStock = request.getParameter("currentStock");
        String description = request.getParameter("description");
        String assetId = request.getParameter("id");

        Results results = new Results();
        System.out.println(name);
        System.out.println(currentStock);
        System.out.println(description);
        System.out.println(assetId);
        Asset asset = new Asset();
        asset.setName(name);
        if (!assetId.equals("")) {
            asset.setAssetId(Integer.parseInt(assetId));
        }
        asset.setDescription(description);
        asset.setCurrentStock(Float.parseFloat(currentStock));

        DataAccessObject dao = (DataAccessObject) getServletContext().getAttribute("dao");

        if (dao.addOrUpdateRecord(asset)) {
            results.setSuccess(true);
            if (assetId.equals("")) {
                results.setType("Hop was added");
            } else {
                results.setType("Hop was updated");
            }

        } else {
            if (assetId.equals("")) {
                results.setType("Failed to add Hop");
            } else {
                results.setType("Failed to update Hop");
            }
        }

        request.setAttribute("results", results);

        doGet(request, response);

    }
}
