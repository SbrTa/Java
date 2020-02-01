package com.example.image.service;

import com.example.image.entity.UserChoice;
import com.example.image.repository.UserChoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class UserChoiceService extends BaseService{

    private UserChoiceRepository userChoiceRepository;

    public UserChoiceService(UserChoiceRepository userChoiceRepository) {
        this.userChoiceRepository = userChoiceRepository;
    }

    public void saveUserChoice(int talkativeLevel, int findFaultLevel, int thoroughJobLevel, int depressionLevel) {
        UserChoice userChoice = new UserChoice();
        userChoice.setUserId(getCurrentUser().getId());
        userChoice.setTalkativeLevel(talkativeLevel);
        userChoice.setFindFaultLevel(findFaultLevel);
        userChoice.setThoroughJobLevel(thoroughJobLevel);
        userChoice.setDepressionLevel(depressionLevel);
        UserChoice choice = userChoiceRepository.save(userChoice);
        System.out.println(choice);
    }
}
