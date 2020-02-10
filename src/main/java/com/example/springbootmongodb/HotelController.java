package com.example.springbootmongodb;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    @GetMapping("/all")
    public List<Hotel> getAll(){
       List<Hotel> hotels=this.hotelRepository.findAll();
       return hotels;
    }

    @PutMapping
    public void insert(@RequestBody Hotel hotel){
        this.hotelRepository.insert(hotel);
    }

    @PostMapping
    public void update(@RequestBody Hotel hotel){
        this.hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.hotelRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable("id") String id){
        Hotel hotel=this.hotelRepository.findHotelById(id);
        return hotel;
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getHotelByMaxPrice(@PathVariable("maxPrice") int maxPrice){
        List<Hotel> hotels=this.hotelRepository.findByPricePerNightLessThan(maxPrice);
        return hotels;
    }

    //Query is from HotelRepository interface
    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city){
        List<Hotel> hotels = this.hotelRepository.findByCity(city);

        return hotels;
    }
    //Query will be managed by Query DSL

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable("country") String country){
        //Create a query class (QHotel)
       QHotel qHotel=new QHotel("hotel");
       //Create Query class to make filter
        BooleanExpression findByCountry=qHotel.address.country.eq(country);

        // We can pass the filters to the findAll method
        List<Hotel> hotels=(List<Hotel>) this.hotelRepository.findAll(findByCountry);
        return hotels;
    }

    @GetMapping("/recommended")
    public List<Hotel> getRecommended(){
        final int maxPrice=100;
        final int minRating=7;

        // Creatre a query class qhotel
        QHotel qHotel=new QHotel("hotel");

        //Create filters using the Query class
        BooleanExpression filterByMaxPrice=qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByminRating=qHotel.reviews.any().rating.gt(minRating);

        //using filters for findAll method
        List<Hotel> hotels=(List<Hotel>) this.hotelRepository.findAll(filterByMaxPrice.and(filterByminRating));
        return hotels;
    }



}



