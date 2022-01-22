package com.gtbr.hexapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(name = "match_uuid", updatable = false)
    private UUID matchUUID;

    @Column(insertable = false)
    private Long matchShortId;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "game_mode_id")
    private GameMode gameMode;

    @Column
    private Long score;

    @Column(insertable = false)
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private LocalDateTime registeredAt;

    @Column
    private Long coin;

    @Column
    private Integer lifeTaken;

    @Column
    private Integer difficulty;

    @Column
    private Boolean watchedAd;

}
