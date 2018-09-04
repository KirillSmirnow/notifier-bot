package org.smirnowku.notifier.repository;

import org.smirnowku.notifier.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, Long> {
}
