package com.guestpro.iot.emoney.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterUser implements Serializable {
    private String username;
    private String password;
    private String repassword;
    private String roleId;
}
