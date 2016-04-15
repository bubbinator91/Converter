package com.bubbinator91.converter.models;

/**
 * The data model for acceleration conversions
 */
public class AccelerationModel {
    private String cmpss, fpss, mpss, sg;

    public enum AccelerationUnits {
        cmpss, fpss, mpss, sg
    }

    public AccelerationModel() {
        cmpss = "1";
        fpss = "0.03280839895";
        mpss = "0.01";
        sg = "0.001019716213";
    }

    public AccelerationModel(String cmpss, String fpss, String mpss, String sg) {
        this.cmpss = cmpss;
        this.fpss = fpss;
        this.mpss = mpss;
        this.sg = sg;
    }

    public String getCmpss() {
        return cmpss;
    }

    public String getFpss() {
        return fpss;
    }

    public String getMpss() {
        return mpss;
    }

    public String getSg() {
        return sg;
    }

    public void setCmpss(String cmpss) {
        this.cmpss = cmpss;
    }

    public void setFpss(String fpss) {
        this.fpss = fpss;
    }

    public void setMpss(String mpss) {
        this.mpss = mpss;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }
}
