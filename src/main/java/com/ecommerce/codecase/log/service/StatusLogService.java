package com.ecommerce.codecase.log.service;

import com.ecommerce.codecase.listing.entity.Listing;
import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import com.ecommerce.codecase.listing.service.entityservice.ListingEntityService;
import com.ecommerce.codecase.log.dto.StatusLogDto;
import com.ecommerce.codecase.log.entity.StatusLog;
import com.ecommerce.codecase.log.repository.StatusLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusLogService {

    private final StatusLogRepository statusLogRepository;
    private final ListingEntityService listingEntityService;
    public List<StatusLogDto> findLogs(Long listingId){
        List<StatusLog> statusLogs = statusLogRepository.findAllByListingId(listingId);
        List<StatusLogDto> statusLogDtoList = new ArrayList<>();
        for (StatusLog log: statusLogs){
            StatusLogDto logDto = convertToDto(log);
            statusLogDtoList.add(logDto);
        }
        return statusLogDtoList;
    }

    public StatusLog saveLog(StatusLogDto dto){
        Listing listing = listingEntityService.findById(dto.getListingId());
        StatusLog log = new StatusLog(dto.getLogId(), listing, dto.getInitialStatus(), dto.getChangedStatus());
        return statusLogRepository.save(log);
    }

    public StatusLogDto convertToDto(StatusLog log){
        Long logId = log.getId();
        Long listingId = log.getListing().getId();
        EnumListingStatus initialStatus = log.getInitialStatus();
        EnumListingStatus changedStatus = log.getChangedStatus();
        LocalDateTime updatedAt = LocalDateTime.now();
        StatusLogDto dto = new StatusLogDto(logId, listingId, initialStatus, changedStatus, updatedAt);
        return dto;
    }
}
