package com.example.busbookingonline.Repository;

import com.example.busbookingonline.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BusRepository extends JpaRepository<Bus, Long>,
        JpaSpecificationExecutor<Bus> {

}