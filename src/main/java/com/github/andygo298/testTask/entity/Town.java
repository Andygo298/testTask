package com.github.andygo298.testTask.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TOWN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Town {

    @Id
    @ApiModelProperty("Represents the ID which is unique to a town.")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "town_name")
    @ApiModelProperty("Represents the name of the town.")
    private String townName;

    @Column(name = "population")
    @ApiModelProperty("Represents the population of the town.")
    private Long population;

    @Column(name = "native_language", length = 64)
    @ApiModelProperty("Represents the native language of the town.")
    private String nativeLanguage;

    @Column(name = "town_info")
    @ApiModelProperty("Represents the information about the town.")
    private String townInfo;

}
