package com.guestpro.iot.emoney.services;



import com.guestpro.iot.emoney.model.Role;
import com.guestpro.iot.emoney.model.User;
import com.guestpro.iot.emoney.pojo.RegisterUser;

import java.util.List;

public interface UserService {
    User register(RegisterUser user, List<Role> roles);

    User unRegisterUser(String id);
}
