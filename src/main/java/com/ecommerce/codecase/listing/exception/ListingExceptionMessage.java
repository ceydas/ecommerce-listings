package com.ecommerce.codecase.listing.exception;

public enum ListingExceptionMessage {

    INVALID_FILE("Invalid bad words file"),
    INVALID_LISTING_TITLE("Invalid title. Must be 10-50 characters long and start with a Turkish character or a number."),
    INVALID_LISTING_DESCRIPTION("Invalid description. Must be 20-200 characters long."),
    DESCRIPTION_CONTAINS_BAD_WORD("Description contains bad words."),

    STATUS_CANNOT_BE_MODIFIED("Listing status cannot be modified."),

    NO_LISTINGS_FOUND("No listing records found."),

    LISTING_ID_DOES_NOT_EXIST("Listing with the given ID does not exist.");
    private String message;


    ListingExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getMessage() {
        return message;
    }
}
