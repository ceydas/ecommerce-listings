package com.ecommerce.codecase.listing.dto;

import com.ecommerce.codecase.listing.enums.EnumListingCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateListingRequestDto {
    private String listingTitle;
    private String listingDescription;
    private EnumListingCategory listingCategory;
}
