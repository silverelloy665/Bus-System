package com.busbooking.dao;

import com.busbooking.model.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {
}
