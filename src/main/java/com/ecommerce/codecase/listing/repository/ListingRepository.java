package com.ecommerce.codecase.listing.repository;

import com.ecommerce.codecase.listing.entity.Listing;
import com.ecommerce.codecase.listing.enums.EnumListingCategory;
import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    boolean existsByTitleAndDescriptionAndCategory(String title, String description, EnumListingCategory category);
    long countListingsByListingStatus(EnumListingStatus status);
}
