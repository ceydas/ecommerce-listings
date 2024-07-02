package com.ecommerce.codecase.listing.enums;

public enum EnumListingStatus {

    PENDING_APPROVAL(true),
    ACTIVE(true),
    DUPLICATE(false),
    DEACTIVATED(false);

    private final boolean isModifiable;

    EnumListingStatus(boolean isModifiable) {
        this.isModifiable = isModifiable;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static EnumListingStatus getDefaultListingStatus(){
        return PENDING_APPROVAL;
    }

    public boolean isModifiable(){
        return isModifiable;
    }

}
