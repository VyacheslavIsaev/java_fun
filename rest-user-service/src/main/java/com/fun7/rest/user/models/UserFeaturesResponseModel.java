package com.fun7.rest.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFeaturesResponseModel {
    @NotEmpty
    private String multiplayer="disabled";

    @NotEmpty
    private String user_support="disabled";

    @NotEmpty
    private String ads="disabled";
}
