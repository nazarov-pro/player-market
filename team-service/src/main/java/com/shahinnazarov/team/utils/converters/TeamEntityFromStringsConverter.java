package com.shahinnazarov.team.utils.converters;

import com.shahinnazarov.common.container.BaseConverter;
import com.shahinnazarov.team.container.entities.TeamEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * TeamEntityFromStringsConverter - converting from list of strings to entity
 */
@Component
public class TeamEntityFromStringsConverter implements BaseConverter<List<String>, TeamEntity> {
    @Override
    public TeamEntity convert(List<String> item) {
        TeamEntity entity = new TeamEntity();
        entity.setName(item.get(0));
        entity.setBudget(new BigDecimal(item.get(1)));
        entity.setCommissionPercentage(new BigDecimal(item.get(2)));
        return entity;
    }

    @Override
    public boolean validate(List<String> item) {
        return item != null && item.size() == 3;
    }
}
