package com.roy.spring.service;

import com.roy.spring.dto.Counter;
import com.roy.spring.dto.Pending;
import com.roy.spring.dto.UserDetails;
import com.roy.spring.dto.UserPost;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Transactional
@Component("commonService")
public class CommonService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private CounterService counterService;


    public List<Pending> getPendingList(){
        return userPostService.getPending();
    }

    public UserPost getUserPost(int postId){
        return userPostService.getFinal(postId);
    }



    public UserDetails getUserDetails(String userName) {
        return userService.getUserDetails(userName);
    }

    public List<UserPost> getPostList(){
        return userPostService.getFinal();
    }

    public Map<Integer,List<Integer>> getLikers(){
        Map<Integer,List<Integer>> likers = new HashMap<Integer, List<Integer>>();
        List<Counter> counters = counterService.getCounterList();
        for(Counter x:counters){
            List<Integer> list = counterService.getIntList(x.getLiker());
            likers.put(x.getPost(),list);
        }
        return likers;
    }

    public Map<Integer,List<Integer>> getDislikers(){
        Map<Integer,List<Integer>> disLikers = new HashMap<Integer, List<Integer>>();
        List<Counter> counters = counterService.getCounterList();
        for(Counter x:counters){
            List<Integer> list = counterService.getIntList(x.getDisliker());
            disLikers.put(x.getPost(),list);
        }
        return disLikers;
    }
}
