package com.deesha.medicine_distribution.serviceImpl;

import com.deesha.medicine_distribution.dto.LoginRequest;
import com.deesha.medicine_distribution.dto.LoginResponse;
import com.deesha.medicine_distribution.model.UserModel;
import com.deesha.medicine_distribution.repository.UserRepository;
import com.deesha.medicine_distribution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteUser(int userid) {
        if (userRepository.existsById(userid)) {
            userRepository.deleteById(userid);
        }
    }

    @Override
    public boolean saveUsers(UserModel usermodel) {
        return userRepository.save(usermodel) != null;
    }

    @Override
    public List<UserModel> viewAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        LoginResponse loginResponse = new LoginResponse();

        UserModel usermodel = userRepository.findByEmail(loginRequest.getEmail());

        if (usermodel == null) {
            loginResponse.setFlag(false);
            loginResponse.setMessage("User not found");
            return loginResponse;
        }

        if (!Objects.equals(usermodel.getEmail(), loginRequest.getEmail())) {

            loginResponse.setFlag(false);
            loginResponse.setMessage("Username is not correct");
            return loginResponse;
        }

        if (!Objects.equals(usermodel.getPassword(), loginRequest.getPassword())) {

            loginResponse.setFlag(false);
            loginResponse.setMessage("Password is not correct");
            return loginResponse;
        }

        loginResponse.setFlag(true);
        loginResponse.setMessage("Login Successful");

        return loginResponse;
    }

//    @Override
//    public LoginResponse login(LoginRequest loginRequest) {
//
//        LoginResponse loginResponse = new LoginResponse();
//
//        try {
//            UserModel usermodel = userRepository.findByEmail(loginRequest.getEmail());
//
//            if (usermodel == null) {
//                loginResponse.setFlag(false);
//                loginResponse.setMessage("User Model Not Found");
//                return loginResponse;
//            }
//
//            if (usermodel.getEmail().equalsIgnoreCase(loginRequest.getEmail())) {
//                loginResponse.setFlag(true);
//                loginResponse.setMessage("Login SuccessFull");
//            } else {
//
//                loginResponse.setFlag(false);
//                loginResponse.setMessage("Password is not correct");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return loginresponse;
//    }
}