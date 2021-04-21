package com.plaidcamp.mealogram.post.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MEALOGRAM_PHOTO")
public class MealogramPhoto {

    @Id
    private String post_id;

    @Id
    private String photo_id;

    private String user_id;

    private String path;

    private int size;

    private LocalDateTime ctime;

    private LocalDateTime utime;

    public MealogramPhoto(String post_id, String photo_id, String user_id, String path, int size, LocalDateTime ctime,
            LocalDateTime utime) {
        this.post_id = post_id;
        this.photo_id = photo_id;
        this.user_id = user_id;
        this.path = path;
        this.size = size;
        this.ctime = ctime;
        this.utime = utime;
    }

    @PrePersist
    public void createdAt() {
        this.ctime = LocalDateTime.now();
    }
}
