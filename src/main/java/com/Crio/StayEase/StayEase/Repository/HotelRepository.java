package com.Crio.StayEase.StayEase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Crio.StayEase.StayEase.Entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    
}
