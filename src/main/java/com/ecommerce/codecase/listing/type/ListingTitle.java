package com.ecommerce.codecase.listing.type;

import com.google.re2j.Pattern;
import com.google.re2j.Matcher;

import com.ecommerce.codecase.listing.exception.ListingExceptionMessage;


public class ListingTitle {
    private String title;

    // The listing title should start with a number between [0-9] OR with [aA-zZ] including Turkish characters ğ,ü,ş,İ,ö,ç
    // The listing title should contain [10-50] characters
    private static final Pattern pattern = Pattern.compile("^[0-9a-zA-ZğüşıöçĞÜŞİÖÇ].{9,49}$");

    public ListingTitle(String title)  throws IllegalArgumentException{
        if (isValid(title)) {
            this.title = title;
        } else {
            throw new IllegalArgumentException(ListingExceptionMessage.INVALID_LISTING_TITLE.getMessage());
        }
    }

    public ListingTitle() {
        this.title = "";
    }

    private boolean isValid(String value) {
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return title;
    }

    public String getTitle(){
        if (!isValid(title)){
            throw new IllegalArgumentException(ListingExceptionMessage.INVALID_LISTING_TITLE.getMessage());
        }
        return title;
    }

    public void setTitle(String title) {
        if (!isValid(title)){
            throw new IllegalArgumentException(ListingExceptionMessage.INVALID_LISTING_TITLE.getMessage());
        }
        this.title = title;
    }
}
