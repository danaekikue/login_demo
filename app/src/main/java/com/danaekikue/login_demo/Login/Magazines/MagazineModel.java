package com.danaekikue.login_demo.Login.Magazines;

import java.util.Date;

public class MagazineModel {

    private int id;
    private String title;
    private String img_url;
    private String date_released;
    private String pdf_url;

    public MagazineModel() { }

    public MagazineModel(int id, String title, String img_url, String date_released, String pdf_url) {
        this.id = id;
        this.title = title;
        this.img_url = img_url;
        this.date_released = date_released;
        this.pdf_url = pdf_url;
    }

    @Override
    public String toString() {
        return "{"+
                "id=" + id +
                ", title= '" + title + "'"  +
                ", img_url='" + img_url + "'"  +
                ", date_released='" + date_released + "'"  +
                ", pdf_url='" + pdf_url + "'" +
                "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDate_released() {
        return date_released;
    }

    public void setDate_released(String date_released) {
        this.date_released = date_released;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }
}
