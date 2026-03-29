package com.busbooking.dao;

import com.busbooking.model.AnomalyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnomalyLogRepository extends JpaRepository<AnomalyLog, Long> {}