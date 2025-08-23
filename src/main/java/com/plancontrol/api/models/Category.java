package com.plancontrol.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import com.plancontrol.api.models.base.IdBase;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "categories", schema = "plan_control")
@Getter
@Setter
@RequiredArgsConstructor
public class Category extends IdBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = true, updatable = true)
    private LocalDateTime updatedAt;
    
    @Column(name = "user_update_id")
    private UUID userUpdateId;
}
