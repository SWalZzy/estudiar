package com.santiagolandeta.estudiar.Entities;

public class Star {
    private final String id;
    private final String hip;
    private final String bf;
    private final String proper;
    private final String ra;
    private final String dec;
    private final String dist;
    private final String mag;
    private final String spect;

    public Star(String id, String hip, String bf, String proper, String ra, String dec, String dist, String mag, String spect) {
        this.id = id;
        this.hip = hip;
        this.bf = bf;
        this.proper = proper;
        this.ra = ra;
        this.dec = dec;
        this.dist = dist;
        this.mag = mag;
        this.spect = spect;
    }

    public String getId() {
        return id;
    }

    public String getHip() {
        return hip;
    }

    public String getBf() {
        return bf;
    }

    public String getProper() {
        return proper;
    }

    public String getRa() {
        return ra;
    }

    public String getDec() {
        return dec;
    }

    public String getDist() {
        return dist;
    }

    public String getMag() {
        return mag;
    }

    public String getSpect() {
        return spect;
    }
}
