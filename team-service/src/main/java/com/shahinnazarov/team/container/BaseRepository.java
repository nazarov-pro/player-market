package com.shahinnazarov.team.container;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Base Repository for Repositories
 * @param <T> - entity class type
 * @param <K> - primary key type of the entity class type
 */
@NoRepositoryBean
public interface BaseRepository<T, K> extends PagingAndSortingRepository<T, K> {
}