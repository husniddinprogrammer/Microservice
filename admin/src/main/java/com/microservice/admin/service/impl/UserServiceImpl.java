package com.microservice.admin.service.impl;


import com.microservice.admin.entity.Roles;
import com.microservice.admin.entity.User;
import com.microservice.admin.repository.UserRepository;
import com.microservice.admin.service.UserService;
import com.microservice.admin.service.dto.UserDTO;
import com.microservice.admin.service.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());
    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        Page<User> users =  userRepository.findAllByIdGreaterThanOrderByIdDesc(2l,pageable);
        return  users.map(UserDTO::new);
    }

    @Override
    public UserDTO status(Long id) throws Exception {
        User result=userRepository.findById(id).get();
        if(result.getStatus()==true){
            result.setStatus(false);
        }
        else {
            result.setStatus(true);
        }
        return new UserDTO(userRepository.save(result));
    }

    @Override
    public UserDTO getById(Long id) throws Exception {
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent()){
            User u =  user.get();
            UserDTO userDTO = new UserDTO(u);
            return userDTO;
        }
        throw new Exception("User not found");
    }

    @Override
    public UserDTO create(UserVM data) throws Exception {
        UserDTO currentUser = getCurrentUser();
        User user = userRepository.findByLogin(data.getLogin()).get();
        user.setName(data.getName());
        user.setFirstName(data.getFirstName());
        data.getRoles().remove(Roles.ADMIN);
        if(!currentUser.getRoles().contains(Roles.ADMIN)){
            data.getRoles().remove(Roles.MANAGER);
        }
        user.setRoles(data.getRoles());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setLogin(data.getLogin());
        logger.info("user came");
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO update(UserVM data) throws Exception {
        if (data.getId() != null){
            UserDTO currentUser = getCurrentUser();
            User user = userRepository.findByLogin(currentUser.getLogin()).get();
            if(passwordEncoder.matches(data.getOldPassword(), user.getPassword())){
                user.setName(data.getName());
                user.setFirstName(data.getFirstName());
                user.setPassword(passwordEncoder.encode(data.getPassword()));
                return new UserDTO(userRepository.save(user));
            }
            else{
                return null;
            }
        }else{
            throw new Exception("id not found");
        }
    }



    @Override
    public void delete(UserVM data) {
        deleteById(data.getId());
    }
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getCurrentUser(){
        String username = getPrincipal();
        if (username != null)
            return userRepository.findByLogin(username).map(UserDTO::new).orElse(null);
        return null;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    @Override
    public UserDTO register(UserVM data) throws Exception{
        User user = new User();
        user.setName(data.getName());
        user.setFirstName(data.getFirstName());
        user.setRoles(data.getRoles());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setLogin(data.getLogin());
        user.setStatus(false);
        return new UserDTO(userRepository.save(user));
    }
}

