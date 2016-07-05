package servlets.Member;

import entities.Asset;
import persistence.AssetDao;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author Alex
 *         6/2/2016
 */
@WebServlet(
        name = "hops",
        urlPatterns = {"/member/hops", "/member/grains"}
)
public class AssetsListServlet extends BaseServlet {
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

        String assetType = request.getRequestURI();

        String type = "";

        if (assetType.equals("/member/hops")) {
            title = "Hop List";
            type = "hop";
        } else if (assetType.equals("/member/grains")) {
            title = "Grain List";
            type = "grain";
        }


        AssetDao dao = (AssetDao) getServletContext().getAttribute("assetDao");
        TreeSet<Asset> assets = dao.searchLikeRecords("type", type);


        request.setAttribute("assets", assets);
        request.setAttribute("assetType", type);

        content = "/member/assets.jsp";
        servletResponse(request, response);
    }
}
