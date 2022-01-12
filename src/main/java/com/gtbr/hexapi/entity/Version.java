package com.gtbr.hexapi.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Version {

    @Id
    @Column
    private String placeholder;

    @Column
    private LocalDateTime launchedAt;

    @Column
    private LocalDateTime endedAt;
}
