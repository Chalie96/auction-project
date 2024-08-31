package com.auction.domain;


import com.auction.domain.posts.Posts;
import com.auction.domain.posts.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostsJpaTest {
    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void clean_up(){
        postsRepository.deleteAll();
    }

    @Test
    void register_post() {
        //given
        String writer = "chalie";
        String title = "hi";
        String content = "have a nice day";
        // when
        postsRepository.save(Posts.builder().title(title).content(content).writer(writer).build());
        Posts post = postsRepository.findById(1L).get();
        // then
        assertThat(post.getId()).isEqualTo(1L);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getWriter()).isEqualTo(writer);
    }

    @Test
    void update() {
        //given
        String writer = "chalie";
        String posted_title = "hi";
        String posted_content = "have a nice day";
        postsRepository.save(Posts.builder().title(posted_title).content(posted_content).writer(writer).build());
        Posts post = postsRepository.findById(1L).get();

        // when
        String new_title = "v2 hi";
        String new_content = "v2 have a nice day";
        post.update(new_title,new_content);
        // then
        assertThat(post.getId()).isEqualTo(1L);
        assertThat(post.getTitle()).isEqualTo(new_title);
        assertThat(post.getContent()).isEqualTo(new_content);
        assertThat(post.getWriter()).isEqualTo(writer);
    }
    @Test
    void like_post() {
        // given
        String writer = "chalie";
        String title = "hi";
        String content = "have a nice day";
        // when
        postsRepository.save(Posts.builder().title(title).content(content).writer(writer).build());
        Posts post = postsRepository.findById(1L).get();
        // then

        assertThat(post.getId()).isEqualTo(1L);
        System.out.println(post.getLike_post());
        post.updateLike(true);
        assertThat(post.getLike_post()).isEqualTo(1L);
    }

    @Test
    void unlike_post() {
        // given
        String writer = "chalie";
        String title = "hi";
        String content = "have a nice day";
        // when
        postsRepository.save(Posts.builder().title(title).content(content).writer(writer).build());
        Posts post = postsRepository.findById(1L).get();
        // then

        assertThat(post.getId()).isEqualTo(1L);
        System.out.println(post.getLike_post());
        post.updateLike(false);
        assertThat(post.getLike_post()).isEqualTo(-1L);
    }

    @Test
    public void BaseTimeEntityTest_register() {
        //given
        LocalDateTime past = LocalDateTime.of(2024,8,30,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .writer("writer")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getCreatedDate()).isAfter(past);
        assertThat(posts.getModifiedDate()).isAfter(past);
    }
}
