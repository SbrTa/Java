package com.test.service;


import com.test.dao.CounterDAO;
import com.test.dto.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service("counterService")
public class CounterService {
    CounterDAO counterDAO = new CounterDAO();

    @Autowired
    public void setCounterDAO(CounterDAO counterDAO){
        this.counterDAO = counterDAO;
    }

    public void createCounter(Counter counter){
        counterDAO.createCounter(counter);
    }

    public Counter getCounter(int postid) {
        return counterDAO.getCounter(postid);
    }

    public void updateCounter(Counter counter) {
        counterDAO.updateCounter(counter);
    }

    public List<Integer> getIntList(String s){
        List<Integer> list = new ArrayList<Integer>();
        String a[] = s.split("-");
        System.out.println("string:");
        for(String aa: a){
            list.add(parseInt(aa));
            System.out.println(aa);
        }

        System.out.println("int:");
        for(int ll: list){
            System.out.println(ll);
        }
        return list;
    }

    public String remove(List<Integer> disliker, int id) {
        String newDisliker = "0";
        for(int x:disliker){
            if(x==0 || x==id) continue;
            newDisliker+="-";
            newDisliker+=Integer.toString(x);
        }
        return newDisliker;
    }



    public String add(List<Integer> liker, String newLiker, int id) {
        if(!liker.contains(id)){
            newLiker+="-";
            newLiker+=Integer.toString(id);
        }
        return newLiker;
    }

    public List<Counter> getCounterList() {
        return  counterDAO.getCounterList();
    }

    public void deleteCounter(int postid) {
        counterDAO.deleteCounter(postid);
    }
}
