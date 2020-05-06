import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;

public class JsonToDateOfCourse {
    String jsonObject;

    public JsonToDateOfCourse(String jsonObject) {
        this.jsonObject = jsonObject;
    }

    DateOfCourse jsonToDB() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        DateOfCourse dateOfCourse = objectMapper.readValue(jsonObject, DateOfCourse.class);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Courses");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(dateOfCourse);
        entityManager.flush();

        transaction.commit();
        entityManager.close();
        factory.close();
        return dateOfCourse;

    }
}
