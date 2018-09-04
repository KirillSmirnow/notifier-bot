package org.smirnowku.notifier.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(indexes = {@Index(columnList = "name", unique = true),
        @Index(columnList = "token", unique = true)})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Channel extends BaseModel {

    @ManyToOne
    private Chat admin;

    @NotNull
    @Pattern(regexp = "[a-z-]{3,50}")
    private String name;

    private String token;
    private String subscriptionCode;

    public static Channel createPublic(Chat admin, String name, String token) {
        return new Channel(admin, name, token, null);
    }

    public static Channel createPrivate(Chat admin, String name, String token, String subscriptionCode) {
        return new Channel(admin, name, token, subscriptionCode);
    }

    private Channel(Chat admin, String name, String token, String subscriptionCode) {
        this.admin = admin;
        this.name = name;
        this.token = token;
        this.subscriptionCode = subscriptionCode;
    }
}
