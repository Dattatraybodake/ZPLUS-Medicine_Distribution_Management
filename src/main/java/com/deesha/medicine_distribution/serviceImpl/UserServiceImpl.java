package com.deesha.medicine_distribution.serviceImpl;

import com.deesha.medicine_distribution.dto.LoginRequest;
import com.deesha.medicine_distribution.dto.LoginResponse;
import com.deesha.medicine_distribution.model.UserModel;
import com.deesha.medicine_distribution.repository.UserRepository;
import com.deesha.medicine_distribution.service.UserService;
import org.springframework.beans.BeanUtils;
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

    @Override
    public Boolean updateUserMaster(UserModel userModel) {
        try {
            if (userModel == null || userModel.getUserId() == null) {
                return false;
            }

            UserModel existingUser = userRepository
                    .findById(userModel.getUserId())
                    .orElse(null);

            if (existingUser == null) {
                return false;
            }

            BeanUtils.copyProperties(
                    userModel,
                    existingUser,
                    "userId"
            );
            userRepository.save(existingUser);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}