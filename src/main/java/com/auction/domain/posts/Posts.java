package com.auction.domain.posts;


import com.auction.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long like_post;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(length = 500)
    private String uploaded_image;

    @Builder
    public Posts(String title, String content, String writer) {
        this.writer = writer;
        this.title = title;
        this.like_post = 0L;
        this.content = content;
    }

    public void upload(String image_url){
        this.uploaded_image = image_url;
    }

    public void updateLike(boolean isLike){
        if(isLike){
            this.like_post +=1L;
        }else {
            this.like_post -=1L;
        }
    }
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
