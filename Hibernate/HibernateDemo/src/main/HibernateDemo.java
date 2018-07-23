package main;

import entity.Student;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HibernateDemo {
    public static void main(String[] args){
        Session session = createHibernateSession.hibernateSession();
/*
        //Create
        Student student = new Student();
        student.setName("mahim");
        student.setRoll("IT-13048");
        session.save(student);
        session.getTransaction().commit();
        System.out.println("student object saved....");
*/


/*
        //Read
        List<Student> studentList = session.createCriteria(Student.class).list();
        for(Student student: studentList){
            System.out.println(student);
        }
*/

/*        //Update
        List<Student> studentList = session.createCriteria(Student.class).list();
        Student student = studentList.get(1);
        student.setName("mahim");
        student.setRoll("IT-13048");
        session.update(student);
        session.getTransaction().commit();
*/

/*        //Delete
        List<Student> studentList = session.createCriteria(Student.class).list();
        Student student = studentList.get(1);
        session.delete(student);
        session.getTransaction().commit();
*/

/*
        //Query (SQL query)
        Query query = session.createSQLQuery("update student set name='atiq' where name='SbrTa'");
        query.executeUpdate();
        session.getTransaction().commit();
*/

        //HQL (Hibernate row SQL)
        Query query = session.createQuery("FROM Student where id >= :idParam");
        query.setParameter("idParam",1);
        List<Student> studentList = query.list();
        for (Student student : studentList){
            System.out.println(student);
        }
        session.getTransaction().commit();

    }
}
