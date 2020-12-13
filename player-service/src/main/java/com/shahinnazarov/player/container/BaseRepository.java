package com.shahinnazarov.player.container;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<T, K> extends PagingAndSortingRepository<T, K> {
}