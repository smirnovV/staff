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
 * Сведения о сотруднике
 */
@Getter
@Setter
@Entity
public class Staff extends AbstractPersistable<Long> {

    /**
     * Имя сотрудника
     */
    @Column
    private String name;

    /**
     * Организация
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Начальник
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boss")
    private Staff boss;

}
