package org.smirnowku.notifier.model;

import lombok.Getter;
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
@ToString(callSuper = true)
public class Channel extends BaseModel {

    @ManyToOne
    private User admin;

    @NotNull
    @Pattern(regexp = "[a-z-]{3,50}")
    private String name;

    private String token;
    private String subscription_code;
}
