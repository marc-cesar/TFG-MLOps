package com.backend.mlopsbackend.Services;

import com.backend.mlopsbackend.Entities.UserToken;
import com.backend.mlopsbackend.Helpers.EncryptionUtils;
import com.backend.mlopsbackend.Repositories.UserRepository;
import com.backend.mlopsbackend.Repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.backend.mlopsbackend.Entities.User;

@Service
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class UserService {
    @Autowired(required = true)
    private UserRepository userRepository;

    @Autowired(required = true)
    private UserTokenRepository userTokenRepository;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (EncryptionUtils.matches(password,user.encryptedPassword)){
            UserToken usrToken = createNewUserToken(user.id);
            return usrToken.Token;
        }
        return "";
    }

    public UserToken createNewUserToken(Long userId) {
        // Insert new token to the UserTokens table
        UserToken userToken = new UserToken();
        userToken.Token = EncryptionUtils.generateNewToken();
        userToken.userId = userId;
        userTokenRepository.save(userToken);
        return userToken;
    }

    public void logout(String username, String token) {
        User user = userRepository.findByUsername(username);
        // Delete the userToken
        userTokenRepository.deleteUsersTokenWithUserId(user.id, token);
    }

    public String signIn(String username, String password) {
        User user = new User();
        user.username = username;
        user.encryptedPassword = EncryptionUtils.hashString(password);
        System.out.println(user.encryptedPassword);
        userRepository.save(user);

        var usrToken = createNewUserToken(user.id);
        return usrToken.Token;
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void save(UserToken usertoken){
        userTokenRepository.save(usertoken);
    }
}
