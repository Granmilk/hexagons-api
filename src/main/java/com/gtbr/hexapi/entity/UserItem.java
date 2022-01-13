package com.gtbr.hexapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserItem {

    @Id
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private LocalDateTime at;
}
