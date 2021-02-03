package com.github.andygo298.testTask.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@Table(name = "TOWN")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Town {

    @Id
    @ApiModelProperty("Represents the ID which is unique to a town.")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "town_name")
    @ApiModelProperty("Represents the name of the town.")
    String townName;

    @Column(name = "population")
    @ApiModelProperty("Represents the population of the town.")
    Long population;

    @Column(name = "native_language", length = 64)
    @ApiModelProperty("Represents the native language of the town.")
    String nativeLanguage;

    @Column(name = "town_info")
    @ApiModelProperty("Represents the information about the town.")
    String townInfo;

}
