package com.shahinnazarov.player.repositories;

import com.shahinnazarov.player.container.BaseRepository;
import com.shahinnazarov.player.container.entities.PlayerEntity;

import java.util.Collection;
import java.util.List;

/**
 * Player Repository
 */
public interface PlayerRepository extends BaseRepository<PlayerEntity, Long> {

    Collection<PlayerEntity> findAllByIdIn(Collection<Long> ids);
}
