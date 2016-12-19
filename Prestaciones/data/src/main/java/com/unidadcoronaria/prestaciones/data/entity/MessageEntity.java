package com.unidadcoronaria.prestaciones.data.entity;

import java.util.Date;

/**
 * Created by AGUSTIN.BALA on 11/10/2016.
 */

public class MessageEntity extends BaseEntity {

    private String text;
    private Date date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
