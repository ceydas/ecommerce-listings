package com.ecommerce.codecase.listing.filter;

import com.google.re2j.Matcher;
import com.google.re2j.Pattern;
import com.ecommerce.codecase.listing.exception.BadWordFoundException;
import com.ecommerce.codecase.listing.exception.ListingExceptionMessage;
import com.ecommerce.codecase.listing.re2j.Re2jHelper;

import java.io.IOException;


public class BadWordsFilter {


    private static final String filePath = "Badwords.txt";
    private BadWordsFilter(){}
    public static void filterBadWordsFromDescription(String description) throws IOException {

        Pattern pattern = Re2jHelper.buildPatternFromFile(filePath);
        Matcher matcher = pattern.matcher(description);

        if (matcher.find()){
            System.out.println("Bad word " + matcher.group());
            throw new BadWordFoundException(ListingExceptionMessage.DESCRIPTION_CONTAINS_BAD_WORD);
        }

    }

    public static boolean descriptionContainsBadWords(String description) throws IOException {
        Pattern pattern = Re2jHelper.buildPatternFromFile(filePath);
        Matcher matcher = pattern.matcher(description);

        if (matcher.find()){
            System.out.println("Bad word " + matcher.group());
            return true;
        }
        return false;
    }

}
