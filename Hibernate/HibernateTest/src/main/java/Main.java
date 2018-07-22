import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String []args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

        //System.out.println("OK");
        Student std = new Student();
        std.setId(19);
        std.setName("SbrTa");
        std.setCgpa(3.34);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(std);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }
}
