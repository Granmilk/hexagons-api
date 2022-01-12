package com.gtbr.hexapi.entity;

import com.gtbr.hexapi.entity.enums.UserStatus;
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
public class UserProfile {

    @Id
    @GeneratedValue(generator = "UUID generator")
    @GenericGenerator(name = "UUID generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID userUUID;

    @Column
    private Long shortId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column
    private String deviceId;

    @Column
    private Long coin;

    @Column
    private LocalDateTime registeredAt;
}
