package com.busbooking.controller;

import com.busbooking.dao.LostItemRepository;
import com.busbooking.model.LostItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lost-and-found")
@CrossOrigin(origins = "*")
@SuppressWarnings("null")
public class LostAndFoundController {

    @Autowired
    private LostItemRepository lostItemRepository;

    @PostMapping
    public ResponseEntity<LostItem> reportItem(@RequestBody LostItem item) {
        item.setStatus("REPORTED");
        return ResponseEntity.ok(lostItemRepository.save(item));
    }

    @GetMapping
    public ResponseEntity<List<LostItem>> getAllItems() {
        return ResponseEntity.ok(lostItemRepository.findAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody java.util.Map<String, String> body) {
        return lostItemRepository.findById(id).map(item -> {
            item.setStatus(body.get("status"));
            return ResponseEntity.ok(lostItemRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }
}