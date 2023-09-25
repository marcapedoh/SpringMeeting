package com.marcRG.javaCommunity.services;

import com.marcRG.javaCommunity.DTO.UserDTO;
import com.marcRG.javaCommunity.auth.AuthenticationRequest;
import com.marcRG.javaCommunity.auth.AuthenticationResponse;

import java.util.List;

public interface UserServices {
    AuthenticationResponse save(UserDTO userDTO);
    UserDTO findByEmail(String email);
    UserDTO findById(Integer id);
    List<UserDTO> findAll();
    UserDTO findByUsername(String Username);

    AuthenticationResponse authenticate(AuthenticationRequest request);
    void delete(Integer id);
}
