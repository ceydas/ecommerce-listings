package com.ecommerce.codecase.log.dto;

import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StatusLogDto {
    private Long logId;
    private Long listingId;
    private EnumListingStatus initialStatus;
    private EnumListingStatus changedStatus;
    private LocalDateTime updatedAt;

    public StatusLogDto(Long listingId, EnumListingStatus initialStatus, EnumListingStatus changedStatus) {
        this.listingId = listingId;
        this.initialStatus = initialStatus;
        this.changedStatus = changedStatus;
    }

    public StatusLogDto(Long logId, Long listingId, EnumListingStatus initialStatus, EnumListingStatus changedStatus) {
        this.logId = logId;
        this.listingId = listingId;
        this.initialStatus = initialStatus;
        this.changedStatus = changedStatus;
    }
}
