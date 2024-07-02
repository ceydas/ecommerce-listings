package com.ecommerce.codecase.listing.service;

import com.ecommerce.codecase.listing.entity.Listing;
import com.ecommerce.codecase.listing.dto.CreateListingRequestDto;
import com.ecommerce.codecase.listing.dto.ListingDto;
import com.ecommerce.codecase.listing.dto.UpdateListingRequestDto;
import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import com.ecommerce.codecase.listing.service.entityservice.ListingEntityService;
import com.ecommerce.codecase.log.dto.StatusLogDto;
import com.ecommerce.codecase.log.entity.StatusLog;
import com.ecommerce.codecase.log.service.StatusLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingEntityService listingEntityService;
    private final StatusLogService statusLogService;

    public ListingDto findById(Long id){
        Listing listing =  listingEntityService.findById(id);
        ListingDto listingDto = listingEntityService.convertToDto(listing);
        return listingDto;
    }

    public List<ListingDto> findAll(){
        List<Listing> listings = listingEntityService.findAll();
        List<ListingDto> listingDtos = new ArrayList<>(listings.size());
        for (Listing listing : listings){
            ListingDto listingDto = listingEntityService.convertToDto(listing);
            listingDtos.add(listingDto);
        }
        return listingDtos;
    }

    public ListingDto createNewListing(CreateListingRequestDto dto){
        Listing listing = listingEntityService.convertToListing(dto);
        Listing savedListing = listingEntityService.saveListing(listing);

        return listingEntityService.convertToDto(savedListing);
    }

    @Transactional
    public ListingDto updateListing(UpdateListingRequestDto dto){
        Listing listing = listingEntityService.findById(dto.getListingId());
        EnumListingStatus oldStatus = listing.getListingStatus();
        EnumListingStatus currentStatus = dto.getListingStatus();
        listing.setListingStatus(currentStatus);
        listingEntityService.saveListing(listing);

        // Save new status update log.
        if (oldStatus != currentStatus) {
            createAndSaveStatusLog(listing, oldStatus);
        }
        return listingEntityService.convertToDto(listing);
    }

    public HashMap<String, Long> getListingStatusStatistics(){
        HashMap<String, Long> statistics = new HashMap<>();
        long total = 0;
        for (EnumListingStatus enumListingStatus : EnumListingStatus.values()){
            long statisticsForCategory = listingEntityService.countListingsByStatus(enumListingStatus);
            total = total + statisticsForCategory;
            statistics.put(enumListingStatus.toString(), statisticsForCategory );
        }
        statistics.put("TOTAL", total);
        return statistics;
    }

    private StatusLogDto createAndSaveStatusLog(Listing listing, EnumListingStatus oldStatus){
        StatusLogDto dto = new StatusLogDto(listing.getId(), oldStatus, listing.getListingStatus());
        StatusLog log = statusLogService.saveLog(dto);

        return statusLogService.convertToDto(log);
    }


}
