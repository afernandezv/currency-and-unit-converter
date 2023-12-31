package io.github.afernandezv.converter.enums;

import java.util.Arrays;
import java.util.List;

public enum MassUnit {
    MICROGRAMO("Microgramo", "mcg"),
    MILIGRAMO("Miligramo", "mg"),
    GRAMO("Gramo", "g"),
    KILOGRAMO("Kilogramo", "kg"),
    TONELADA("Tonelada", "mt"),
    ONZA("Onza", "oz"),
    LIBRA("Libra", "lb"),
    ;

    private final String name;
    private final String symbol;

    MassUnit(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public static <T> List<T> getUnits(){
        return (List<T>) Arrays.asList(MassUnit.values());
    }

    @Override
    public String toString() {
        return name;
    }
}
