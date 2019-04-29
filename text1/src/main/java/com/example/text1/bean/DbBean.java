package com.example.text1.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dell on 2019/4/26.
 */

@Entity
public class DbBean {

    @Id(autoincrement = true)
    private Long id;

    @Property
    private String url;

    @Property
    private String dase;

    @Generated(hash = 286857148)
    public DbBean(Long id, String url, String dase) {
        this.id = id;
        this.url = url;
        this.dase = dase;
    }

    @Generated(hash = 1953169116)
    public DbBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDase() {
        return this.dase;
    }

    public void setDase(String dase) {
        this.dase = dase;
    }

}
