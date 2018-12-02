package org.roy.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Start..." );

        ApplicationContext context = new FileSystemXmlApplicationContext("src/beans/beans.xml");

        Parrot parrot = (Parrot)context.getBean("parrot");
        parrot.speak();
        ((FileSystemXmlApplicationContext)context).close();
    }
}
