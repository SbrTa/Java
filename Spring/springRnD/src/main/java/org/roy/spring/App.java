package org.roy.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ApplicationContext context = new FileSystemXmlApplicationContext("src/beans/beans.xml");
        Patient patient = (Patient)context.getBean("patient");
        System.out.println(patient);

        ((FileSystemXmlApplicationContext)context).close();
    }
}
