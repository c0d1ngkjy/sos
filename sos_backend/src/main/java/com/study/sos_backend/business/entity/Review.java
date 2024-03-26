package com.study.sos_backend.business.entity;

import com.study.sos_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "BUSINESS_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Business business;

    private String title;

    private String content;

    private Float rating;

    // 이미지 넣나?

    @Builder
    public Review(User user, Business business, String title, String content, Float rating) {
        this.user = user;
        this.business = business;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
}
