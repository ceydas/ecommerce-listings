package com.ecommerce.codecase.listing.controller;

import com.ecommerce.codecase.listing.dto.CreateListingRequestDto;
import com.ecommerce.codecase.listing.dto.ListingDto;
import com.ecommerce.codecase.listing.dto.UpdateListingRequestDto;
import com.ecommerce.codecase.listing.enums.EnumListingStatus;
import com.ecommerce.codecase.listing.service.ListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard/listings")
public class ListingController {
    private final ListingService listingService;
    @GetMapping("/{id}")
    public ResponseEntity<ListingDto> getListingById(@PathVariable Long id){
        ListingDto listingDto = listingService.findById(id);
        return ResponseEntity.ok(listingDto);
    }

    @GetMapping()
    public ResponseEntity<List<ListingDto>> getAllListings(){
        List<ListingDto> listingDtos = listingService.findAll();
        return ResponseEntity.ok(listingDtos);
    }

    @GetMapping("/stats/status")
    public ResponseEntity<HashMap<String, Long>> getStatusStatistics(){
        return ResponseEntity.ok(listingService.getListingStatusStatistics());
    }

    @PutMapping()
    public ResponseEntity<ListingDto> updateListingStatus(@RequestBody UpdateListingRequestDto dto){
        ListingDto listingDto = listingService.updateListing(dto);
        return ResponseEntity.ok(listingDto);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ListingDto> activateListing(@PathVariable("id") Long id){
        UpdateListingRequestDto dto = new UpdateListingRequestDto(id, EnumListingStatus.ACTIVE);
        return updateListingStatus(dto);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ListingDto> deactivateListing(@PathVariable("id") Long id){
        UpdateListingRequestDto dto = new UpdateListingRequestDto(id, EnumListingStatus.DEACTIVATED);
        return updateListingStatus(dto);
    }
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Creates new listing",
                    content = @Content(
                            mediaType = "application/json",
                            schema= @Schema(
                                    implementation = CreateListingRequestDto.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "Merchandise Listing",
                                            summary = "New Merchandise Listing",
                                            value = "{\n" +
                                                    "  \"listingTitle\": \"Oyuncak Bebek Koleksiyonu\",\n" +
                                                    "  \"listingDescription\": \"98 adet bebekten oluşmaktadır 1995-2006 yıllarında alınmıştır.\",\n" +
                                                    "  \"listingCategory\": \"MERCHANDISE\"\n" +
                                                    "}"
                                    ),
                                    @ExampleObject(
                                            name = "Vehicle Listing",
                                            summary = "New Vehicle Listing",
                                            value = "{\n" +
                                                    "  \"listingTitle\": \"15 Yaşında Araç\",\n" +
                                                    "  \"listingDescription\": \"15 yaşını doldurmuştur. Takas olunur. Araca iyi baktım ancak 48.000 TL'lik hasar kaydı bulunmakta. Sadece ciddi alıcılar lütfen 054814238948.\",\n" +
                                                    "  \"listingCategory\": \"VEHICLES\"\n" +
                                                    "}"
                                    ),
                                    @ExampleObject(
                                            name = "Real Estate Listing",
                                            summary = "New Real Estate Listing",
                                            value = "{\n" +
                                                    "  \"listingTitle\": \"İstanbul'un Merkezinde Zor Bulunan Müthiş Daire\",\n" +
                                                    "  \"listingDescription\": \"İçinde mutfak, pencere, duş ve tuvalet yoktur. Bodrum -3. kat. Metroya 2dk yürüme mesafesi. \",\n" +
                                                    "  \"listingCategory\": \"REAL_ESTATE\"\n" +
                                                    "}"
                                    ),
                                    @ExampleObject(
                                            name = "Misc. Listing",
                                            summary = "New Misc. Listing",
                                            value = "{\n" +
                                                    "  \"listingTitle\": \"Dostoyevski Tüm Kitaplar\",\n" +
                                                    "  \"listingDescription\": \"1. Basım Rusça ve Orijinaldir. Türkiyede ilk ve tek full Dostoyevski eserleri koleksiyonudur 📕. \",\n" +
                                                    "  \"listingCategory\": \"MISC\"\n" +
                                                    "}"
                                    ),
                                    @ExampleObject(
                                            name = "Listing with Invalid Title",
                                            summary = "",
                                            value = "{\n" +
                                                    "  \"listingTitle\": \"ACİL SATILIK ARABA !! ACİL NAKİT İHTİYACIM VAR! YALNIZCA CİDDİ ALICILAR ULAŞSIN lÜTFEN!\",\n" +
                                                    "  \"listingDescription\": \"Hoca Hanımdan acil satılıktır.\",\n" +
                                                    "  \"listingCategory\": \"VEHICLES\"\n" +
                                                    "}"
                                    ),

                                    @ExampleObject(
                                            name = "Listing with Invalid Description : Bad Word : avbq.",
                                            summary = "",
                                            value = "{\n" +
                                                    "  \"listingTitle\": \"Tertemiz Bebek Arabası\",\n" +
                                                    "  \"listingDescription\": \"Chico'dan 2022 yılında alındı. Hiç avbq kullanılmamıştır. Etiketli.\",\n" +
                                                    "  \"listingCategory\": \"MISC\"\n" +
                                                    "}"
                                    ),

                            }
                    )
            )
    )

    @PostMapping()
    public ResponseEntity<ListingDto> createNewListing(@RequestBody CreateListingRequestDto dto){
        ListingDto listingDto = listingService.createNewListing(dto);
        return ResponseEntity.ok(listingDto);
    }
}
