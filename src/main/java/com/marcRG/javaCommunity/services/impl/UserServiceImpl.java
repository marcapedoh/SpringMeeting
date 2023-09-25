package com.marcRG.javaCommunity.services.impl;

import com.marcRG.javaCommunity.DTO.UserDTO;
import com.marcRG.javaCommunity.auth.AuthenticationRequest;
import com.marcRG.javaCommunity.auth.AuthenticationResponse;
import com.marcRG.javaCommunity.config.JwtService;
import com.marcRG.javaCommunity.exception.EntityNotFoundException;
import com.marcRG.javaCommunity.exception.ErrorCodes;
import com.marcRG.javaCommunity.exception.InvalidEntityException;
import com.marcRG.javaCommunity.models.User;
import com.marcRG.javaCommunity.repository.UserRepository;
import com.marcRG.javaCommunity.services.UserServices;
import com.marcRG.javaCommunity.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@EnableWebMvc
@EnableAutoConfiguration
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;

        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse save(UserDTO userDTO) {
       List<String> error= UserValidator.validate(userDTO);
       if(!error.isEmpty()){
           log.error("erreur lors de la validation de cet objet {}",userDTO);
           throw new InvalidEntityException("L'utilisateur n'est pas valide donc! ", ErrorCodes.User_Not_valid,error);
       }
       userDTO.setMotDePasse(passwordEncoder.encode(userDTO.getMotDePasse()));
        UserDTO.fromEntity(
                userRepository.save(UserDTO.toEntity(userDTO))
        );
        var jwtToken=jwtService.generateToken(UserDTO.toEntity(userDTO));
       return AuthenticationResponse.builder()
               .token(jwtToken)
               .build();
    }

    @Override
    public UserDTO findByEmail(String email) {
        if(!StringUtils.hasLength(email)){
            log.error("vous passer un email null en parametre!");
            return null;
        }
        Optional<User> user=userRepository.findUserByEmail(email);
        return user.map(UserDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Utilisateur non trouvé",ErrorCodes.User_Not_Found));
    }

    @Override
    public UserDTO findById(Integer id) {
        if(id==null){
            log.error("l'id pour la recherche est null");
            return null;
        }
        Optional<User> userOptional=userRepository.findById(id);
        return userOptional.map(UserDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé id passer en parametre", ErrorCodes.User_Not_Found));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(String Username) {
        if(!StringUtils.hasLength(Username)){
            log.error("vous ne pouvez pas faire une recherche en ne fournissant une chaine vide !");
            return null;
        }
        Optional<User> user=userRepository.findUserByUsername(Username);
        return user.map(UserDTO::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("User not found",ErrorCodes.User_Not_Found));
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.warn("drjdtkjyfukyuj bonsoirrrrrrrrrrrrrrrrrrrrrrrr!!");
        log.warn("voila les parametre",request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),request.getPassword())
        );
        log.warn("drjdtkjyfukyuj bonsoirrrrrrrrrrrrrrrrrrrrrrrr marccc!!");
        UserDetails user=userRepository.findUserByUsername(request.getUsername()).orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        if(!StringUtils.hasLength(user.getUsername())){
            log.warn("le username de ce utilisateur est nul");
        }
        String jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("vous passer une id null!");
        }
        userRepository.deleteById(id);
    }
}
