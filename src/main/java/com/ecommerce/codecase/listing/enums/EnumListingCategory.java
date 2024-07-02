package com.ecommerce.codecase.listing.enums;


public enum EnumListingCategory {
    REAL_ESTATE("Real Estate", EnumListingStatus.getDefaultListingStatus()),
    VEHICLES("Vehicles", EnumListingStatus.getDefaultListingStatus()),

    MERCHANDISE("Merchandise", EnumListingStatus.ACTIVE),

    MISC("Miscellaneous", EnumListingStatus.getDefaultListingStatus());

    private final String categoryName;
    private final EnumListingStatus listingStatus;

     EnumListingCategory(String categoryName, EnumListingStatus listingStatus) {
        this.categoryName = categoryName;
        this.listingStatus = listingStatus;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public EnumListingStatus getInitialListingStatus(){
         return listingStatus;
    }

    public String getCategoryName(){
         return categoryName;
    }
}
