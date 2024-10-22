package com.mareosenvios.repositories;

import com.mareosenvios.dto.ProductDTO;
import com.mareosenvios.entities.Shipping;
import com.mareosenvios.entities.ShippingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingItemRepository extends JpaRepository<ShippingItem, Integer> {

    List<ShippingItem> findAllByShipping(Shipping shipping);

    @Query("SELECT new com.mareosenvios.dto.ProductDTO (p.description, SUM(si.productCount)) " +
            "FROM ShippingItem si " +
            "JOIN si.product p " +
            "GROUP BY p.id " +
            "ORDER BY SUM(si.productCount) DESC")
    List<ProductDTO> findTopProducts();

}
