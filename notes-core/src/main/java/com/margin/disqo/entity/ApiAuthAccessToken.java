package com.margin.disqo.entity;

import com.margin.disqo.enums.TokenType;
import lombok.Getter;
import lombok.Setter;

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
}
