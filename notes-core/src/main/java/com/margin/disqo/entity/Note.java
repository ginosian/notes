package com.margin.disqo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Note extends AbstractEntity{
    private String title;
    private String note;
}
