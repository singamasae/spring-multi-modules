package com.guestpro.iot.emoney.services.impl;

import com.guestpro.iot.emoney.model.Account;
import com.guestpro.iot.emoney.model.Role;
import com.guestpro.iot.emoney.model.User;
import com.guestpro.iot.emoney.pojo.RegisterUser;
import com.guestpro.iot.emoney.repository.AccountRepository;
import com.guestpro.iot.emoney.repository.UserRepository;
import com.guestpro.iot.emoney.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service(value = "userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User register(RegisterUser user, List<Role> roles) {
        try {
            User u = new User();
            u.setUserName(user.getUsername());
            u.setEnabled(Boolean.TRUE);
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            u.setRoles(roles);
            u = userRepository.save(u);

            Account account = new Account();
            account.setBalance(new BigDecimal(0));
            account.setUser(u);
            accountRepository.save(account);

            return u;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    @Override
    @Transactional
    public User unRegisterUser(String id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null)
                return null;

            user.setEnabled(false);
            user = userRepository.save(user);
            return user;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }
}
