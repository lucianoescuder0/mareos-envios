package com.mareosenvios.repositories;

import com.mareosenvios.entities.ShippingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingItemRepository extends JpaRepository<ShippingItem, Integer> {
}
