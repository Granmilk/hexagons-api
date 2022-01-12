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
    @JoinColumn
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn
    private Item item;

    @Column
    private LocalDateTime at;
}
