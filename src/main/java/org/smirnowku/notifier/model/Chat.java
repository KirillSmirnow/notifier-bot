package org.smirnowku.notifier.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Chat extends BaseModel {

    private long telegramId;
}
