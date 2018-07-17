import com.util.ConnectionManager;
import com.util.*;
import com.util.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

public class App {

    public static void main(String []args) throws Exception {
        int choice;
        String inputString = null;
        System.out.println("Enter your choice:");
        System.out.println("1: Add Student \n2: Delete Student \n3: View Student\n? ");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        inputString = in.readLine();
        choice = Integer.parseInt(inputString);

        int id;
        String name;
        float cgpa;
        JDBCDAO dao = new JDBCDAO();
        if(choice == 1){
            System.out.println("Add Student");

            System.out.println("ID : ");
            inputString = in.readLine();
            id = Integer.parseInt(inputString);

            System.out.println("Name : ");
            inputString = in.readLine();
            name = inputString;

            System.out.println("CGPA : ");
            inputString = in.readLine();
            cgpa = Float.parseFloat(inputString);

            Student std = new Student(id,name,cgpa);
            dao.addStudent(std);
            System.out.println("Student added successfully...");
        }
        else if(choice == 2){
            System.out.println("ID : ");
            inputString = in.readLine();
            id = Integer.parseInt(inputString);
            dao.deleteStudent(id);
            System.out.println("Student deleted successfully...");
        }
        else if(choice == 3){
            dao.viewAll();
        }

    }
}
