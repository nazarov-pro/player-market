package com.shahinnazarov.team.utils.converters;

import com.shahinnazarov.common.container.BaseConverter;
import com.shahinnazarov.team.container.dto.TeamRequest;
import com.shahinnazarov.team.container.entities.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamEntityConverter implements BaseConverter<TeamRequest, TeamEntity> {

    @Override
    public TeamEntity convert(TeamRequest item) {
        TeamEntity entity = new TeamEntity();
        entity.setId(item.getId());
        entity.setName(item.getName());
        return entity;
    }

    @Override
    public boolean validate(TeamRequest item) {
        return item != null;
    }
}
