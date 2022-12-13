package com.microservice.admin.service;

import com.microservice.admin.service.dto.UserDTO;
import com.microservice.admin.service.vm.UserVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public Page<UserDTO> getAll(Pageable pageable);
    public UserDTO getById(Long id) throws Exception;
    public UserDTO create(UserVM data) throws Exception;
    public UserDTO update(UserVM data) throws Exception;
    public void delete(UserVM data);
    public void deleteById(Long id);
    UserDTO getCurrentUser();
    public UserDTO status(Long id) throws Exception;
    public UserDTO register(UserVM data) throws Exception;
}
















