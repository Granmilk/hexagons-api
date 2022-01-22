package com.gtbr.hexapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(insertable = false)
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private LocalDateTime launchedAt;

    @Column(insertable = false)
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private LocalDateTime endedAt;
}
