package servlets;

import entities.Member;
import entities.Results;
import org.apache.catalina.authenticator.FormAuthenticator;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import persistence.DataAccessObject;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;

/**
 * @author Alex
 * 5/18/2016
 */

@WebServlet(
        name = "login",
        urlPatterns = { "/login" }
)
public class LoginServlet extends BaseServlet {

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

        request.getSession().setAttribute("url", request.getAttribute("javax.servlet.forward.request_uri"));

        if (request.getUserPrincipal() == null) {
            title = "Login";
            content = "/login.jsp";
            servletResponse(request, response);
        } else {
            response.sendRedirect("/member");
        }


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


        Results results = new Results();

        try {
            request.login(request.getParameter("username"), request.getParameter("password"));


            results.setSuccess(true);
            results.addMessage("admin: " + request.isUserInRole("ADMIN"));
            results.addMessage("member: " + request.isUserInRole("MEMBER"));

            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                String user = request.getRemoteUser();
                DataAccessObject dao = (DataAccessObject) getServletContext().getAttribute("dao");
                dao.setType(Member.class);
                Member member = (Member) dao.getRecordByEmail(user);
                request.getSession().setAttribute("user", member);
                results.setType("Welcome " + member.getFirstName() + " " + member.getLastName());

            }
        } catch (ServletException e) {
            results.setType("Login error");
            results.addMessage("invalid username and password");
            results.setSuccess(false);
        }
        request.setAttribute("results", results);

        if (request.isUserInRole("MEMBER")) {
            if (request.getSession().getAttribute("url") == null) {
                url = "/member";
            } else if (request.getSession().getAttribute("url").equals("/login")) {
                url = "/member";
            } else {
                url = (String) request.getSession().getAttribute("url");
            }
        } else {
            url = "/login";
            System.out.println(3);
            System.out.println(url);
        }

        getServletContext().setAttribute("results", results);
        response.sendRedirect(url);

    }

}