package com.fun7.user.repo;


import com.fun7.user.repo.error.InvalidUserIdException;
import com.fun7.user.repo.error.UserNotFoundException;
import com.fun7.user.repo.models.UserDataModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UsersRepo implements UsersAdminService, UserService{

    private HashMap<String, UserDataModel> userExperience = new HashMap<String, UserDataModel>();

    public Integer getVisitsNumber(String userId){
        if (userId == null)
            throw new InvalidUserIdException(userId);
        UserDataModel model = userExperience.get(userId);
        if (model == null){
            model = new UserDataModel(userId, 1);
        }else
        {
            model.incVisists();
        }
        userExperience.put(userId, model);
        return model.getVisits();
    }

    public List<UserDataModel> getAllUsers(){
        return new ArrayList<>( userExperience.values() );
    }

    public UserDataModel getUser(String userId){
        if (userId == null)
            throw new InvalidUserIdException(userId);
        return userExperience.get(userId);
    }

    public void deleteUser(String userId){
        if (userId == null)
            throw new InvalidUserIdException(userId);
        if ( userExperience.remove(userId) == null )
            throw new UserNotFoundException(userId);
    }
}
