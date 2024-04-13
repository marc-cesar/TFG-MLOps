package com.backend.mlopsbackend.Services;

import com.backend.mlopsbackend.Entities.LoginResponse;
import com.backend.mlopsbackend.Entities.UserToken;
import com.backend.mlopsbackend.Helpers.EncryptionUtils;
import com.backend.mlopsbackend.Repositories.UserRepository;
import com.backend.mlopsbackend.Repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.backend.mlopsbackend.Entities.User;

import java.util.Optional;

@Service
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class UserService {
    @Autowired(required = true)
    private UserRepository userRepository;

    @Autowired(required = true)
    private UserTokenRepository userTokenRepository;

    public LoginResponse login(String username, String password) {
        LoginResponse response = new LoginResponse();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            if (EncryptionUtils.matches(password,user.get().encryptedPassword)){
                UserToken usrToken = createNewUserToken(user.get().id);
                response.Token = usrToken.token;
            }
            response.Username = username;
            response.IsAdmin = user.get().IsAdmin;
        } else {
          response.Token = "";
          response.Username = "";
          response.IsAdmin = false;
        }

        return response;
    }

    public UserToken createNewUserToken(Long userId) {
        // Insert new token to the UserTokens table
        UserToken userToken = new UserToken();
        userToken.token = EncryptionUtils.generateNewToken();
        userToken.userId = userId;
        userTokenRepository.save(userToken);
        return userToken;
    }

    public void logout(String username, String token) {
        Optional<User> user = userRepository.findByUsername(username);
        // Delete the userToken
        user.ifPresent(value -> userTokenRepository.deleteUsersTokenWithUserId(value.id, token));
    }

    public LoginResponse signIn(String username, String password) {
        LoginResponse response = new LoginResponse();
        User user = new User();
        user.username = username;
        user.encryptedPassword = EncryptionUtils.hashString(password);
        System.out.println(user.encryptedPassword);
        userRepository.save(user);

        var usrToken = createNewUserToken(user.id);
        response.IsAdmin = false;
        response.Token = usrToken.token;
        response.Username = username;

        return response;
    }

    public boolean isUserAdmin(String token) {
        Optional<User> User = getUserFromToken(token);
        return User.isPresent() && User.get().IsAdmin;
    }

    public boolean isUserViewer(String token){
        Optional<User> User = getUserFromToken(token);
        return User.isPresent();
    }

    public Optional<User> getUserFromToken(String token){
        Optional<UserToken> usrtok = userTokenRepository.findByToken(token);
        // Get the user
        return usrtok.map(userToken -> userRepository.findById(userToken.userId)).orElse(null);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void save(UserToken usertoken){
        userTokenRepository.save(usertoken);
    }
}
