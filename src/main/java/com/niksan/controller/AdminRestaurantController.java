package com.niksan.controller;

import com.niksan.config.JwtConstant;
import com.niksan.model.Restaurant;
import com.niksan.model.User;
import com.niksan.request.CreateRestaurantRequest;
import com.niksan.response.MessageResponse;
import com.niksan.service.RestaurantService;
import com.niksan.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminRestaurantController {

    private RestaurantService restaurantService;

    private UserService userService;

    @PostMapping("/restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader(JwtConstant.JWT_HEADER) String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(req, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader(JwtConstant.JWT_HEADER) String jwt,
                                                       @PathVariable Long id) throws Exception {

        userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id, req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
                                                       @RequestHeader(JwtConstant.JWT_HEADER) String jwt,
                                                       @PathVariable Long id) throws Exception {

        userService.findUserByJwtToken(jwt);
         restaurantService.deleteRestaurant(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("Restaurant deleted Successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/restaurants/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
                                                            @RequestHeader(JwtConstant.JWT_HEADER) String jwt,
                                                            @PathVariable Long id) throws Exception {

        userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/restaurants/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
            @RequestHeader(JwtConstant.JWT_HEADER) String jwt
           ) throws Exception {

       User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
