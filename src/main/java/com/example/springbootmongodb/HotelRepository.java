package com.example.springbootmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String> {
    Hotel findHotelById(String id);
    List<Hotel> findByPricePerNightLessThan(int maxPrice);
}
