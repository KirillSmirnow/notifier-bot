package org.smirnowku.notifier.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Subscription extends BaseModel {

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Channel channel;
}
