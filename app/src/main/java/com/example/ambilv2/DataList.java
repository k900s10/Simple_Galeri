package com.example.ambilv2;

public class DataList {
    private String namaArtist;
    private String namaLagu;
    private int image;

    public DataList(String namaArtist, String namaLagu, int image) {
        this.namaArtist = namaArtist;
        this.namaLagu = namaLagu;
        this.image = image;
    }

    public String getNamaArtist() {
        return namaArtist;
    }

    public void setNamaArtist(String namaArtist) {
        this.namaArtist = namaArtist;
    }

    public String getNamaLagu() {
        return namaLagu;
    }

    public void setNamaLagu(String namaLagu) {
        this.namaLagu = namaLagu;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
