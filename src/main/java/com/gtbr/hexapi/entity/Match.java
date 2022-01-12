package com.gtbr.hexapi.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(generator = "UUID generator")
    @GenericGenerator(name = "UUID generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID matchUUID;

    @Column
    private Long matchShortId;

    @ManyToOne
    @JoinColumn
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "game_mode_ID")
    private GameMode gameMode;

    @Column
    private Long score;

    @Column
    private LocalDateTime at;

    @Column
    private Long coin;

    @Column
    private Integer dificulty;

    @Column
    private Boolean watchedAd;
}
