package servlets.Member;/**
 * Created by Alex on 5/18/2016.
 */

import entities.Member;
import entities.Order;
import persistence.DataAccessObject;
import persistence.OrderDao;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

@WebServlet(
        name = "member}",
        urlPatterns = {"/member"}
)
public class MemberServlet extends BaseServlet {
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
        title = "Member Home";
        content = "/member/member.jsp";
        OrderDao dao = (OrderDao) getServletContext().getAttribute("orderDao");

        Map<String, Object> map = new TreeMap<>();
        Member member = (Member) request.getSession().getAttribute("user");
        map.put("memberId", member.getMemberId());
        map.put("orderStatus", "unfilled");
        Set<Order> orders = new TreeSet<>(dao.searchMultipleParams(map));
        request.setAttribute("orders", orders);
        servletResponse(request, response);

    }

}
