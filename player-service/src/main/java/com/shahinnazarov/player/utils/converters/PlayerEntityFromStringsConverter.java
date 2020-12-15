package com.shahinnazarov.player.utils.converters;

import com.shahinnazarov.common.container.BaseConverter;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.utils.Constants;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * PlayerEntityFromStringsConverter - converting list of strings to entity
 */
@Component
public class PlayerEntityFromStringsConverter implements BaseConverter<List<String>, PlayerEntity> {
    @Override
    public PlayerEntity convert(List<String> item) {
        PlayerEntity entity = new PlayerEntity();
        entity.setFirstname(item.get(0));
        entity.setLastname(item.get(1));
        entity.setMonthsOfExperience(Short.valueOf(item.get(2)));
        entity.setDateOfBirth(LocalDate.parse(item.get(3), Constants.DATE_FORMATTER));
        return entity;
    }

    @Override
    public boolean validate(List<String> item) {
        return item != null && item.size() == 4;
    }
}
