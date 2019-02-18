package com.margin.disqo.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof ApiUserDetail)) return false;

        final Note that = (Note) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(title, that.title)
                .append(note, that.note)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(title)
                .append(note)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("title", title)
                .append("note", note)
                .toString();
    }
}
