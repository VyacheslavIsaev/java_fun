package com.fun7.user.repo;


import com.fun7.user.repo.models.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UsersRepo {

    private HashMap<String, UserModel> userExperience = new HashMap<String, UserModel>();

    public int getCallsNumber(String userId){
        UserModel model = userExperience.get(userId);
        if (model == null){
            model = new UserModel(userId, 1);
        }else
        {
            model.incVisists();
        }
        userExperience.put(userId, model);
        return model.getVisits();
    }

    public List<UserModel> listAllUsers(){
        return new ArrayList<>( userExperience.values() );
    }

    public UserModel getUser(String userId){
        return userExperience.get(userId);
    }

    public void deleteUser(String userId){
        userExperience.remove(userId);
    }

}
