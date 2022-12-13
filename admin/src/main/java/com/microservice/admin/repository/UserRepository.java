package com.microservice.admin.repository;


import com.microservice.admin.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByLogin(String username);
  public Page<User> findAllByIdGreaterThanOrderByIdDesc(Long id,Pageable pageable);
}