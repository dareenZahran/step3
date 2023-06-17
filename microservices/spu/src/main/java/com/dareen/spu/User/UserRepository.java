package com.dareen.spu.User;

import java.util.Optional;

// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

   
    
    
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

  
}




