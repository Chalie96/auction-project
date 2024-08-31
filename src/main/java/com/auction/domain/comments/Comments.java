package com.auction.domain.comments;

import com.auction.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comments extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String content;
    @Column(length = 500, nullable = false)
    private String writer;
    @Column(nullable = false)
    private Long referred_post;
    private Long referred_writer_id;

    @Builder
    public Comments(String content, String writer, Long referred_post, Long referred_writer_id){
        this.content = content;
        this.writer = writer;
        this.referred_post = referred_post;
        this.referred_writer_id = referred_writer_id;
    }

    void update(String content){
        this.content = content;
    }
}
