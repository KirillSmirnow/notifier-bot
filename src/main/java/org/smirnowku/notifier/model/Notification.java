package org.smirnowku.notifier.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Notification extends BaseModel {

    @ManyToOne
    private Channel channel;

    @NotNull
    @NotBlank
    @Size(max = 4096)
    private String message;
}
