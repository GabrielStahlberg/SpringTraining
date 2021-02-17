package br.com.gstahlberg.spring_training.model.enumeration;

public enum EnumCategory {
    RACKET("Racket"),
    RACKET_BAG("Racket bag"),
    BALLS("Balls"),
    CLOTHES("Clothes"),
    SHOES("Shoes"),
    ACCESSORIES("Accessories"),
    OTHERS("Others");

    private final String value;

    EnumCategory(String optionValue) {
        value = optionValue;
    }

    public String getValue() {
        return value;
    }
}
