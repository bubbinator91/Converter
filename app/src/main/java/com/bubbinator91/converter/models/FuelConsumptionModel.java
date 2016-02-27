package com.bubbinator91.converter.models;

/**
 * The data model for fuel consumption conversions
 */
public class FuelConsumptionModel {
    private String usmpg, ukmpg, kpl, l100km;

    public enum FuelConsumptionUnits {
        usmpg, ukmpg, kpl, l100km
    }

    public FuelConsumptionModel() {
        usmpg = "1";
        ukmpg = "1.2009499255398";
        kpl = "0.42514370749052";
        l100km = "1.609344";
    }

    public FuelConsumptionModel(String usmpg, String ukmpg, String kpl, String l100km) {
        this.usmpg = usmpg;
        this.ukmpg = ukmpg;
        this.kpl = kpl;
        this.l100km = l100km;
    }

    public String getUsmpg() {
        return usmpg;
    }

    public String getUkmpg() {
        return ukmpg;
    }

    public String getKpl() {
        return kpl;
    }

    public String getL100km() {
        return l100km;
    }

    public void setUsmpg(String usmpg) {
        this.usmpg = usmpg;
    }

    public void setUkmpg(String ukmpg) {
        this.ukmpg = ukmpg;
    }

    public void setKpl(String kpl) {
        this.kpl = kpl;
    }

    public void setL100km(String l100km) {
        this.l100km = l100km;
    }
}
