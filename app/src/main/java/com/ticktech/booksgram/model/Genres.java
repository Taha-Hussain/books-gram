package com.ticktech.booksgram.model;

/**
 * Created by Taha on 04/06/2017.
 */

public class Genres {

    private int genere_id;
    private String genere_Name;

    public int getGenere_id() {
        return genere_id;
    }

    public void setGenere_id(int genere_id) {
        this.genere_id = genere_id;
    }

    public String getGenere_Name() {
        return genere_Name;
    }

    public void setGenere_Name(String category_Name) {
        this.genere_Name = category_Name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
}
