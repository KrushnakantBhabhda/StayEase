package com.Crio.StayEase.StayEase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Crio.StayEase.StayEase.Entity.Booking;

public interface BookingRepository  extends JpaRepository<Booking,Long>{

    
} 
