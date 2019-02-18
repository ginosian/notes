package com.margin.disqo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Note extends AbstractEntity{

    @Column(length = 50)
    private String title;

    @Column(length = 1000)
    private String note;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_note_fk"))
    private ApiUser user;
}
