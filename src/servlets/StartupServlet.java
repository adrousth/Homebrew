package servlets;

import entities.Member;
import org.apache.catalina.deploy.LoginConfig;
import persistence.MemberDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alex
 * 5/18/2016
 */

@WebServlet(
        name = "startup",
        urlPatterns = { "/startup" },
        loadOnStartup = 1
)
public class StartupServlet extends HttpServlet {

    public void init() {

        MemberDao dao = new MemberDao();

        ServletContext context = getServletContext();

        context.setAttribute("dao", dao);
        context.setAttribute("stuff", "stuff");
        context.setAttribute("pageContent", "/home.jsp");
        context.setAttribute("pageTitle", "Welcome");
    }
}
