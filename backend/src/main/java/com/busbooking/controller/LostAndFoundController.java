package com.busbooking.controller;

import com.busbooking.dao.LostItemRepository;
import com.busbooking.model.LostItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lost-and-found")
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
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return lostItemRepository.findById(id).map(item -> {
            item.setStatus(status);
            return ResponseEntity.ok(lostItemRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }
}
