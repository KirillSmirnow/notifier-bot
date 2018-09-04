package org.smirnowku.notifier.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Subscription extends BaseModel {

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Channel channel;
}
