package org.diegodev.hotelalurabackend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class adds support for entity listener.
 * It will track the creation date and modification date of the entity extending this class.
 */
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"})
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity extends BaseEntity implements Serializable {

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP", updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastModifiedDate;

}