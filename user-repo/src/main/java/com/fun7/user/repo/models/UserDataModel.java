package com.fun7.user.repo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDataModel {
    String userId;
    Integer visits;

    public void incVisists(){ visits++; }
}
