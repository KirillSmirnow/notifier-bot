package org.smirnowku.notifier.dto.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.smirnowku.notifier.model.Chat;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelCreate {

    private String name;
    private Chat admin;
    private boolean restricted;
}
