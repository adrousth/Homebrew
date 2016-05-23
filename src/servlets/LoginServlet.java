package servlets;

import entities.Results;
import org.apache.catalina.authenticator.FormAuthenticator;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        title = "Login";
        content = "/login.jsp";


        servletResponse(request, response);


    }
    /**
     * Handles HTTP GET requests.
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
            results.setType("Logged in");

            results.addMessage("Success!");
            results.setSuccess(true);
            results.addMessage("admin: " + request.isUserInRole("ADMIN"));
            results.addMessage("member: " + request.isUserInRole("MEMBER"));

            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                results.addMessage("Now you need to get the user and have it set to the context in the login servlet, ok?");


            }
        } catch (ServletException e) {
            results.setType("Login error");
            results.addMessage("invalid username and password");
        }
        request.setAttribute("results", results);
        servletResponse(request, response);

    }


    /*
     public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(username, password);
            User user = userService.find(username, password);
            externalContext.getSessionMap().put("user", user);
            externalContext.redirect(originalURL);
        } catch (ServletException e) {
            // Handle unknown username/password in request.login().
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }

    public User getUser() {
        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userService.find(principal.getName()); // Find User by j_username.
            }
        }
        return user;
    }
    */

}