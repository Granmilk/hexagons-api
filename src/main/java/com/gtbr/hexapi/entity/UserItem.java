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
    @GeneratedValue(generator = "seq_user_item")
    @SequenceGenerator(sequenceName = "user_item_id_seq", name = "seq_user_item", allocationSize = 1)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private LocalDateTime registeredAt;
}
