package com.ecommerce.codecase.listing.dto;

import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateListingRequestDto {
    private Long listingId;
    private EnumListingStatus listingStatus;
}
