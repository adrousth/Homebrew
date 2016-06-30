package servlets.Admin;

import entities.Asset;
import entities.Results;
import persistence.AssetDao;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alex
 *         6/26/2016
 */
@WebServlet(
        name = "grainForm",
        urlPatterns = {"/admin/grain/*", "/admin/grain", "/admin/grain/"}
)
public class GrainFormServlet extends BaseServlet {
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
        content = "/admin/assetForm.jsp";

        String button = "";

        if (assetId == null) {

            title = "New Grain Form";
            button = "Submit";

        } else if (!assetId.equals("")) {
            AssetDao dao = (AssetDao) getServletContext().getAttribute("assetDao");
            Asset asset = dao.getRecordById(Integer.parseInt(assetId.substring(1)));

            if (asset == null) {
                Results results = new Results();
                results.setType("Error");
                results.addMessage("Grain with id of " + assetId.substring(1) + " does not exist.");
                title = "New Grain Form";
                button = "Submit";
                content = "error.jsp";
                request.setAttribute("results", results);
            } else if (asset.getType().toLowerCase().equals("grain")) {

                request.setAttribute("asset", asset);
                title = asset.getName() + "Grain Update Form";
                button = "Update";

            } else {
                Results results = new Results();
                results.setType("Error");
                results.addMessage("Asset with id of " + assetId.substring(1) + " is not a grain.");
                title = "New Grain Form";
                button = "Submit";
                content = "error.jsp";
                request.setAttribute("results", results);
            }
        }


        request.setAttribute("type", "grain");
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

        String name = request.getParameter("assetName");
        String currentStock = request.getParameter("currentStock");
        String description = request.getParameter("description");

        String assetId = request.getPathInfo();

        Results results = new Results();

        AssetDao dao = (AssetDao) getServletContext().getAttribute("assetDao");
        Asset asset = new Asset();


        if (assetId != null) {
            if (!assetId.equals("")) {
                asset = dao.getRecordById(Integer.parseInt(assetId.substring(1)));
            }
        } else {
            assetId = "";
        }

        asset.setName(name);
        asset.setDescription(description);
        asset.setCurrentStock(Float.parseFloat(currentStock));
        asset.setType("grain");


        if (dao.addOrUpdateRecord(asset)) {
            results.setSuccess(true);
            if (assetId.equals("")) {
                results.setType("Grain was added");
            } else {
                results.setType("Grain was updated");
            }

        } else {
            if (assetId.equals("")) {
                results.setType("Failed to add Grain");
            } else {
                results.setType("Failed to update Grain");
            }
        }

        request.setAttribute("results", results);


        doGet(request, response);

    }
}
