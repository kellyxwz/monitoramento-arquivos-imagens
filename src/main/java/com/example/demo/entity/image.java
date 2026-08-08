package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "imaegm_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class image {

    @Id
    @GeneratedValue(strategy = )

    private String imageName;
    private Instant createTime;
    private Long sizeBytes;

}
