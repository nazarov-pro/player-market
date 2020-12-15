package com.shahinnazarov.player.container;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Base repository for Repositories
 * @param <T> - Entity class type
 * @param <K> - Primary key class type of the entity class
 */
@NoRepositoryBean
public interface BaseRepository<T, K> extends PagingAndSortingRepository<T, K> {
}