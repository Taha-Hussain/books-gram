package com.ticktech.booksgram.model;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Taha on 04/06/2017.
 */

public class Genres {

    private String genere_id;
    private CheckBox checkBox;
    private String genere_Name;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Genres() {}
    public Genres(CheckBox checkBox ) {
        this.checkBox = checkBox ;

    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
        public String getGenere_id() {
        return genere_id;
    }

    public void setGenere_id(String genere_id) {
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
