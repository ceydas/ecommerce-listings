package com.ecommerce.codecase.log.entity;

import com.ecommerce.codecase.listing.entity.Listing;
import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StatusLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "listing_id", referencedColumnName = "listing_id")
    private Listing listing;

    private EnumListingStatus initialStatus;
    private EnumListingStatus changedStatus;

    private LocalDateTime updatedAt;

    public StatusLog(Long id, Listing listing, EnumListingStatus initialStatus, EnumListingStatus changedStatus) {
        this.id = id;
        this.listing = listing;
        this.initialStatus = initialStatus;
        this.changedStatus = changedStatus;
    }

    @PrePersist
    protected void onCreate() {
        updatedAt = LocalDateTime.now();
    }
}
