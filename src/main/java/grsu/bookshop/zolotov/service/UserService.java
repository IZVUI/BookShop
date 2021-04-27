package grsu.bookshop.zolotov.service;

import grsu.bookshop.zolotov.model.Role;
import grsu.bookshop.zolotov.model.User;
import grsu.bookshop.zolotov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);

        String message = String.format(
                "Hello, %s! \n" +
                        "Welcome to Book Shop! Please, visit next link: http://localhost:8080/activate/%s",
                user.getUsername(),
                user.getActivationCode()
        );

        if(!user.getEmail().isEmpty()) {
            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }
}
