package com.ecommerce.codecase.log.controller;

import com.ecommerce.codecase.log.dto.StatusLogDto;
import com.ecommerce.codecase.log.service.StatusLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard/listings/logs")
public class LogController {
    private final StatusLogService statusLogService;

    @GetMapping ("/{id}")
    ResponseEntity<List<StatusLogDto>> getStatusLogsByListing(@PathVariable("id") Long id){
        List<StatusLogDto> list = statusLogService.findLogs(id);
        return ResponseEntity.ok(list);
    }
}
