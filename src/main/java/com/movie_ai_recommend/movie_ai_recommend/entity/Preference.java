package com.movie_ai_recommend.movie_ai_recommend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유저가 선호하는 정보에 대한 Entity
 */

@Entity
@Data
@NoArgsConstructor
public class Preference extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long id;

    @OneToOne
    private User user;

    /*
    유저가 선호하는 영화장르 EnumType
     */
    @Column(name = "favorite_genre")
    @Enumerated(EnumType.STRING)
    private Genre favoriteGenre;

    /*
    유저가 선호하는 영화배우
     */
    @Column(name = "favorite_actor")
    private String favoriteActor;

    /*
    유저가 선호하는 영화감독
     */
    @Column(name = "favorite_director")
    private String favoriteDirector;

    /*
    유저가 최근 재미있게 본 작품
     */
    @Column(name = "recent_watched")
    private String recentWatched;

    /*
    선호도 Constructor
     */
    public Preference(User user, Genre favoriteGenre, String favoriteActor, String favoriteDirector, String recentWatched) {
        this.user = user;
        this.favoriteGenre = favoriteGenre;
        this.favoriteActor = favoriteActor;
        this.favoriteDirector = favoriteDirector;
        this.recentWatched = recentWatched;
    }
}
