import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


import logic.Model;
import logic.User;


@WebServlet(urlPatterns = "/servletAdd")
public class servletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();


//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
//        response.setContentType("text/html;charset=utf-8");
//
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw =response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        User user = new User(name, surname, salary);
//        model.add(counter.getAndIncrement(), user);
//
//        pw.print("<html>" +
//                "<h3>Пользователь " + name + " " + surname + " " + salary + " успешно создан! :)</h3>" +
//                "<a href=\"addUser.html\">Создать нового пользователя</a><br/>" +
//                "<a href=\"index.jsp\">Домой</a>" +
//                "</html>");
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        StringBuffer sb = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject json = gson.fromJson(String.valueOf(sb), JsonObject.class );

        request.setCharacterEncoding("utf-8");

        String name = json.get("name").getAsString();
        String surname = json.get("surname").getAsString();
        double salary = json.get("salary").getAsDouble();

        User user = new User(name, surname, salary);
        model.add(counter.getAndIncrement(), user);

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(model.getList()));

    }

}