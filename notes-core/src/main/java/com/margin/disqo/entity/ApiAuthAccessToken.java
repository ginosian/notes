package com.margin.disqo.entity;

import com.margin.disqo.enums.TokenType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ApiAuthAccessToken extends AbstractEntity {
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column(name = "expires")
    private LocalDateTime expires;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "api_auth_access_token_api_user_detail_fk"))
    private ApiUserDetail apiUserDetail;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof ApiAuthAccessToken)) return false;

        final ApiAuthAccessToken that = (ApiAuthAccessToken) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(token, that.token)
                .append(tokenType, that.tokenType)
                .append(active, that.active)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(token)
                .append(tokenType)
                .append(active)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("token", token)
                .append("tokenType", tokenType)
                .append("active", active)
                .toString();
    }
}
