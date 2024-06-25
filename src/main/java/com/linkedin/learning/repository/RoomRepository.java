package com.linkedin.learning.repository;

import com.linkedin.learning.entity.RoomEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

    RoomEntity getById(Long id);
}
