package com.ecommerce.codecase.listing.dto;

import com.ecommerce.codecase.listing.enums.EnumListingCategory;
import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListingDto {
    private Long listingId;
    private String listingTitle;
    private String listingDescription;
    private EnumListingCategory listingCategory;
    private EnumListingStatus listingStatus;
}
