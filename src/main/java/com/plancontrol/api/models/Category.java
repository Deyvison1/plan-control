package com.plancontrol.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import com.plancontrol.api.models.base.IdBase;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "categories", schema = "plan_control")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends IdBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private String description;
}
