package com.busbooking.controller;

import com.busbooking.dao.FavoriteRouteRepository;
import com.busbooking.model.FavoriteRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
@SuppressWarnings("null")
public class FavoriteRouteController {

    @Autowired
    private FavoriteRouteRepository favoriteRouteRepository;

    @PostMapping
    public ResponseEntity<FavoriteRoute> addFavorite(@RequestBody FavoriteRoute favorite) {
        return ResponseEntity.ok(favoriteRouteRepository.save(favorite));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteRoute>> getUserFavorites(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteRouteRepository.findByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFavorite(@PathVariable Long id) {
        favoriteRouteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}