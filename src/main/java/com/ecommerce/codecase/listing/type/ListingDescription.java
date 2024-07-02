package com.ecommerce.codecase.listing.type;
import com.google.re2j.Pattern;
import com.google.re2j.Matcher;
import com.ecommerce.codecase.listing.filter.BadWordsFilter;
import com.ecommerce.codecase.listing.exception.BadWordFoundException;
import com.ecommerce.codecase.listing.exception.ListingExceptionMessage;

import java.io.IOException;


public class ListingDescription {
    private String description;

    // The listing title should contain [20-200] characters
    private static final Pattern pattern = Pattern.compile("^.{20,200}$");

    public ListingDescription(String description){
        if (!isValid(description)) {
            throw new IllegalArgumentException(ListingExceptionMessage.INVALID_LISTING_DESCRIPTION.getMessage());
        }
        try {
            if (BadWordsFilter.descriptionContainsBadWords(description)){
                throw new BadWordFoundException(ListingExceptionMessage.DESCRIPTION_CONTAINS_BAD_WORD);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.description = description;
    }

    public ListingDescription() {
        this.description = "";
    }

    private boolean isValid(String value) {
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        if (!isValid(description)){
            throw new IllegalArgumentException(ListingExceptionMessage.INVALID_LISTING_DESCRIPTION.getMessage());
        }
        return description;
    }

    public void setDescription(String description) {
        if (!isValid(description)){
            throw new IllegalArgumentException(ListingExceptionMessage.INVALID_LISTING_DESCRIPTION.getMessage());
        }
        this.description = description;
    }
}
