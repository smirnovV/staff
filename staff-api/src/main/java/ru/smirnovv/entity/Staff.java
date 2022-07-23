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
@Entity
public class Staff extends AbstractPersistable<Long> {

    /**
     * Имя сотрудника
     */
    @Getter
    @Setter
    @Column
    private String name;

    /**
     * Организация
     */
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Начальник
     */
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boss")
    private Staff boss;

}
