package com.mareosenvios.repositories;

import com.mareosenvios.entities.Shipping;
import com.mareosenvios.entities.ShippingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingItemRepository extends JpaRepository<ShippingItem, Integer> {

    List<ShippingItem> findAllByShipping(Shipping shipping);
}
