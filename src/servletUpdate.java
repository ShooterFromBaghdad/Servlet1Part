import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.util.*;

import logic.User;
import logic.Model;

@WebServlet(urlPatterns = "/servletUpdate")
public class servletUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Model model = Model.getInstance();

        response.setContentType("text/html;charset=utf-8");

        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        if (id > 0) {
            if (id > model.getList().size()) {
                pw.print("<html>" +
                        "<h3>Такого пользователя нет</h3><br/>" +
                        "<a href=\"index.jsp\">Домой</a>" +
                        "</html>");
            } else {
                String name = model.getList().get(id).getName();
                String surname = model.getList().get(id).getSurname();
                double salary = model.getList().get(id).getSalary();

                String nameNEW = request.getParameter("name");
                String surnameNEW = request.getParameter("surname");
                double salaryNEW = Double.parseDouble(request.getParameter("salary"));

                model.doPut(id, nameNEW, surnameNEW, salaryNEW);

                pw.print("<html>" +
                        "<h3>Пользователь " + name + " " + surname + " " + salary + " успешно изменен на " + nameNEW + " " +
                        surnameNEW + " " + salary + " :)</h3>" +
                        "<a href=\"index.jsp\">Домой</a>" +
                        "</html>");
            }

            }
    }

}
