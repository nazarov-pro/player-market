package com.shahinnazarov.team.utils.converters;

import com.shahinnazarov.common.container.BaseConverter;
import com.shahinnazarov.team.container.dto.TeamResponse;
import com.shahinnazarov.team.container.entities.TeamEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * TeamResponseConverter - converting from entity to response
 */
@Component
@RequiredArgsConstructor
public class TeamResponseConverter implements BaseConverter<TeamEntity, TeamResponse> {

    @Override
    public TeamResponse convert(TeamEntity item) {
        TeamResponse response = new TeamResponse();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setBudget(item.getBudget());
        response.setCommissionPercentage(item.getCommissionPercentage());
        return response;
    }

    @Override
    public boolean validate(TeamEntity item) {
        return item != null;
    }
}
