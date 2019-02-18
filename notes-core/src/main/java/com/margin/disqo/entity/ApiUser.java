package com.margin.disqo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ApiUser extends AbstractEntity{

    @Column
    private String name;
}
