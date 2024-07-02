package com.ecommerce.codecase.listing.service.entityservice;

import com.ecommerce.codecase.listing.entity.Listing;
import com.ecommerce.codecase.listing.dto.CreateListingRequestDto;
import com.ecommerce.codecase.listing.dto.ListingDto;
import com.ecommerce.codecase.listing.enums.EnumListingCategory;
import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import com.ecommerce.codecase.listing.exception.ListingException;
import com.ecommerce.codecase.listing.exception.ListingExceptionMessage;
import com.ecommerce.codecase.listing.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListingEntityService {
    private final ListingRepository listingRepository;
    public Listing findById(Long id){
        Optional<Listing> optionalListing =  listingRepository.findById(id);
        if (!optionalListing.isPresent()){
            throw new ListingException(ListingExceptionMessage.LISTING_ID_DOES_NOT_EXIST);
        }
        return optionalListing.get();
    }

    public List<Listing> findAll(){
        List<Listing> listings =  listingRepository.findAll();
        if (listings.isEmpty()){
            throw new ListingException(ListingExceptionMessage.NO_LISTINGS_FOUND);
        }

        return listings;
    }

    public Listing saveListing(Listing listing){
        return listingRepository.save(listing);
    }

    /**
     * Converts Listing object to DTO.
     * */
    public ListingDto convertToDto(Listing listing){
        ListingDto listingDto = new ListingDto(listing.getId(), listing.getTitle(), listing.getDescription(), listing.getCategory(), listing.getListingStatus());
        return listingDto;
    }

    public Listing convertToListing(ListingDto dto){
        Listing listing = new Listing();
        listing.setTitle(dto.getListingTitle());
        listing.setDescription(dto.getListingDescription());
        listing.setCategory(dto.getListingCategory());

        listing.setListingStatus(dto.getListingStatus());
        return listing;
    }

    /**
     * Converts DTO to Listing object on _create_
     * Includes dynamic status determination.
     * */
    public Listing convertToListing(CreateListingRequestDto dto){
        Listing listing = new Listing();
        listing.setTitle(dto.getListingTitle());
        listing.setDescription(dto.getListingDescription());
        EnumListingCategory listingCategory = dto.getListingCategory();
        listing.setCategory(listingCategory);

        // Determine listing status based on listing category, i.e. duplicate -> duplicate,
        // merchandise -> active,
        // otherwise -> pending approval

        EnumListingStatus listingStatus = determineInitialListingStatus(listing);
        listing.setListingStatus(listingStatus);
        return listing;
    }

    private boolean isDuplicate(Listing listing) {
        return listingRepository.existsByTitleAndDescriptionAndCategory(
                listing.getTitle(), listing.getDescription(), listing.getCategory());
    }

    public long countListingsByStatus(EnumListingStatus listingStatus){
        return listingRepository.countListingsByListingStatus(listingStatus);
    }

    /**
     * Determines the listing status when a listing object is first created.
     * Example:
     * duplicate -> DUPLICATE,
     * merchandise -> ACTIVE,
     * otherwise -> PENDING_APPROVAL
     * **/
    private EnumListingStatus determineInitialListingStatus(Listing listing){
        // If it's a duplicate, assign status = "DUPLICATE".
        if (isDuplicate(listing)){
            return EnumListingStatus.DUPLICATE;
        }
        else {
            EnumListingCategory listingCategory = listing.getCategory();
            EnumListingStatus listingStatus = listingCategory.getInitialListingStatus();
            return listingStatus;
        }
    }
}
