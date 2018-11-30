package org.roy.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Start..." );

        ApplicationContext context = new FileSystemXmlApplicationContext("src/beans/beans.xml");
        Logger logger = (Logger) context.getBean("logger");
        System.out.println(logger);
        logger.writeFile("main file writer ");
        logger.writeConsole("main console writer");
        ((FileSystemXmlApplicationContext)context).close();
    }
}
