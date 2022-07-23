package ru.smirnovv.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Сведения об организации
 */
@Entity
public class Organization extends AbstractPersistable<Long> {

    /**
     * Название организации
     */
    @Getter
    @Setter
    @Column
    private String name;

    /**
     * Головная организация
     */
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_organization")
    private Organization headOrganization;

}
