import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

public class CourseServlet extends HttpServlet {
    static String date;
    DateOfCourse docNew;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        date = req.getParameter("date");
        List doc;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Courses");
        EntityManager em = factory.createEntityManager();
        doc = em.createQuery("select DateOfCourse from DateOfCourse DateOfCourse where DateOfCourse.date IN :key").setParameter("key", date).getResultList();

        if (doc.size() == 0) {
            URL url = new URL("http://data.fixer.io/api/" + date + "?access_key=a58b3c08a332f91b79800ddca3ed1f11&symbols=usd,uah");
            InputStream input = url.openStream();
            byte[] buffer = input.readAllBytes();
            String str = new String(buffer);
            var jtdoc = new JsonToDateOfCourse(rebuild(str));
            docNew = jtdoc.jsonToDB();
        } else {
            docNew = (DateOfCourse) doc.get(0);
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<title>" +
                "TakeFileServlet" +
                "</title>" +
                "</head>" +
                "<body>" +
                "<h1>Course on date " + docNew.getDate() + "</h1></br>" +
                "<h2>Euro - base</h2></br>" +
                "<h2>Dollar USA - " + docNew.getUsd() + "</h2></br>" +
                "<h2>Hrivnya UKR - " + docNew.getUAH() + "</h2></br>" +
                "</body>" +
                "</html>"
        );
        printWriter.close();
    }

    private String rebuild(String str) {
        str = str.replace("USD", "usd");
        str = str.replace("UAH", "uah");
        str = str.replace("\"rates\":{", "");
        str = str.replace(str.substring(str.indexOf('}')), "") + "}";
    return str;
    }
}

