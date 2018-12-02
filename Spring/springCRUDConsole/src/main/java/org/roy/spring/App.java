package org.roy.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Start..." );

        ApplicationContext context = new FileSystemXmlApplicationContext("src/beans/beans.xml");

        NoticesDAO dao = (NoticesDAO)context.getBean("noticesDao");
        /*
        //Create
        Notice noticeCreate = new Notice("Alice","alice@hotmail.com","I love Bob...");
        dao.create(noticeCreate);*/

        /*//Batch create
        Notice notice11 = new Notice("Alice11","alice@hotmail.com","I love Bob...");
        Notice notice22 = new Notice("Alice22","alice@hotmail.com","I love Bob...");
        Notice notice33 = new Notice("Alice33","alice@hotmail.com","I love Bob...");
        List<Notice> noticeList = new ArrayList<>();
        noticeList.add(notice11);
        noticeList.add(notice22);
        noticeList.add(notice33);
        dao.create(noticeList);*/

        /*
        //Update
        Notice noticeUpdate = new Notice(3,"Bob","bob@hotmail.com","I loove Alice too..");
        dao.update(noticeUpdate);*/

        //List
        System.out.println("List : ");
        List<Notice> notices = dao.getNotices();
        for (Notice notice1 : notices){
            System.out.println(notice1);
        }

        /*
        //Single object
        System.out.println("Object : ");
        Notice notice = dao.getNotice(2);
        System.out.println(notice);*/

        /*
        //Delete
        System.out.println("Delete : ");
        dao.delete(1);*/



        ((FileSystemXmlApplicationContext)context).close();
    }
}
