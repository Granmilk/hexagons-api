package com.gtbr.hexapi.entity;

import lombok.*;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long itemId;

    @Column
    private String name;

    @Column
    private Long value;

}
