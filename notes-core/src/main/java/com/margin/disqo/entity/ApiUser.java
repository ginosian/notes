package com.margin.disqo.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ApiUser extends AbstractEntity{

    @Column
    private String name;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof ApiUser)) return false;

        final ApiUser that = (ApiUser) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("name", name)
                .toString();
    }
}
