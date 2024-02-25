package com.niksan.controller;

import com.niksan.config.JwtConstant;
import com.niksan.dto.RestaurantDto;
import com.niksan.model.Restaurant;
import com.niksan.model.User;
import com.niksan.request.CreateRestaurantRequest;
import com.niksan.service.RestaurantService;
import com.niksan.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class RestaurantController {

    private RestaurantService restaurantService;

    private UserService userService;

    @GetMapping("restaurants/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader(JwtConstant.JWT_HEADER) String jwt,
                                                            @RequestParam String keyword) throws Exception {

        userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader(JwtConstant.JWT_HEADER) String jwt
                                                            ) throws Exception {

        userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("restaurants/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader(JwtConstant.JWT_HEADER)
                                                 String jwt, @PathVariable Long id) throws Exception {

        userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("restaurants/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(@RequestHeader(JwtConstant.JWT_HEADER)
                                                         String jwt, @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
