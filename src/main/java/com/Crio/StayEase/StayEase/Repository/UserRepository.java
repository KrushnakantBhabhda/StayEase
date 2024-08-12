package com.Crio.StayEase.StayEase.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.Crio.StayEase.StayEase.Entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

    UserDetails findByEmail(String email);
    
}
