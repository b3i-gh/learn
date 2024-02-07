package com.b3i.monsterspring.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Monster implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String imageUrl;
    private Boolean owned;
    private Long size;
    private String lang;

    public Monster() {
    }

    public Monster(Long id, String name, String imageUrl, Boolean owned, Long size, String lang) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.owned = owned;
        this.size = size;
        this.lang = lang;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getOwned() {
        return owned;
    }

    public void setOwned(Boolean owned) {
        this.owned = owned;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", owned=" + owned +
                ", size=" + size +
                ", lang=" + lang +
                '}';
    }
}

