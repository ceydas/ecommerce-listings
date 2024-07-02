package com.ecommerce.codecase.listing.entity;

import com.ecommerce.codecase.listing.enums.EnumListingCategory;
import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import com.ecommerce.codecase.listing.exception.ListingException;
import com.ecommerce.codecase.listing.exception.ListingExceptionMessage;
import com.ecommerce.codecase.listing.type.ListingDescription;
import com.ecommerce.codecase.listing.type.ListingTitle;

import javax.persistence.*;

@Entity
@Table(name = "listing")
public class Listing {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="listing_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    private EnumListingCategory category;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumListingStatus listingStatus;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return new ListingTitle(title).getTitle(); //Ensure that the title fits the constraints
    }

    public void setTitle(String title) {
        String validTitle = new ListingTitle(title).getTitle();
        this.title = validTitle;
    }

    public String getDescription() {
        return new ListingDescription(description).getDescription();
    }

    public void setDescription(String description) {
        String validDescription = new ListingDescription(description).getDescription();
        this.description = validDescription;
    }

    public EnumListingCategory getCategory() {
        return category;
    }

    public void setCategory(EnumListingCategory category) {
        this.category = category;
    }

    public EnumListingStatus getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(EnumListingStatus listingStatus) {

        if (this.listingStatus == null){
            this.listingStatus = listingStatus;
        }
        // Check if status is modifiable
        else if (!this.listingStatus.isModifiable()){
            throw new ListingException(ListingExceptionMessage.STATUS_CANNOT_BE_MODIFIED);
        }
        else {
            this.listingStatus = listingStatus;
        }
    }
}
