package ru.smirnovv.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
public class Organization extends AbstractPersistable<Long> {

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_organization")
    private Organization headOrganization;
}
