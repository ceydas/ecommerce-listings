package com.ecommerce.codecase.listing.exception;

public class BadWordFoundException extends RuntimeException{
    public BadWordFoundException(ListingExceptionMessage message) {
        super(message.getMessage());
    }
}
