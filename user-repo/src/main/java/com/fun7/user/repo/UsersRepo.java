package com.fun7.user.repo;


import com.fun7.user.repo.error.InvalidUserIdException;
import com.fun7.user.repo.error.UserNotFoundException;
import com.fun7.user.repo.models.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UsersRepo implements UsersAdminService, UserService{

    private HashMap<String, UserModel> userExperience = new HashMap<String, UserModel>();

    public Integer getVisitsNumber(String userId){
        if (userId == null)
            throw new InvalidUserIdException(userId);
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

    public List<UserModel> getAllUsers(){
        return new ArrayList<>( userExperience.values() );
    }

    public UserModel getUser(String userId){
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
