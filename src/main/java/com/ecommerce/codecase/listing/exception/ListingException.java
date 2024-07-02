package com.ecommerce.codecase.listing.exception;

public class ListingException extends RuntimeException{
    public ListingException(ListingExceptionMessage message){
        super(message.getMessage());
    }
}
