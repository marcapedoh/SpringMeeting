package com.marcRG.javaCommunity.controller;

import com.marcRG.javaCommunity.DTO.UserDTO;
import com.marcRG.javaCommunity.auth.AuthenticationRequest;
import com.marcRG.javaCommunity.auth.AuthenticationResponse;
import com.marcRG.javaCommunity.controller.API.UserAPI;
import com.marcRG.javaCommunity.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController implements UserAPI {

    private UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public AuthenticationResponse save(UserDTO userDTO) {
        return userServices.save(userDTO);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userServices.findByEmail(email);
    }

    @Override
    public UserDTO findById(Integer id) {
        return userServices.findById(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return userServices.findAll();
    }

    @Override
    public UserDTO findByUsername(String Username) {
        return userServices.findByUsername(Username);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return userServices.authenticate(request);
    }

    @Override
    public void delete(Integer id) {
        userServices.delete(id);
    }
}
