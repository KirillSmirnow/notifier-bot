package org.smirnowku.notifier.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "n_user")
@Getter
@Setter
@ToString(callSuper = true)
public class User extends BaseModel {
}
