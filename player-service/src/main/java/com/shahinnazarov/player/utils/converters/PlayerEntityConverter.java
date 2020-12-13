package com.shahinnazarov.player.utils.converters;

import com.shahinnazarov.common.container.BaseConverter;
import com.shahinnazarov.player.container.dto.PlayerRequest;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.container.enums.GeneralExceptions;
import com.shahinnazarov.player.container.exceptions.GeneralException;
import com.shahinnazarov.player.utils.Constants;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;

@Component
public class PlayerEntityConverter implements BaseConverter<PlayerRequest, PlayerEntity> {

    @Override
    public PlayerEntity convert(PlayerRequest item) {
        try {
            LocalDate birthDay = LocalDate.parse(item.getDateOfBirth(), Constants.DATE_FORMATTER);
            PlayerEntity entity = new PlayerEntity();
            entity.setId(item.getId());
            entity.setFirstname(item.getFirstname());
            entity.setLastname(item.getLastname());
            entity.setMonthsOfExperience(item.getMonthOfExperience());
            entity.setDateOfBirth(birthDay);
            return entity;
        } catch (DateTimeException ex) {
            throw GeneralException.of(
                    GeneralExceptions.DATE_TIME_FORMATTING_PROBLEM,
                    item.getDateOfBirth()
            );
        }
    }

    @Override
    public boolean validate(PlayerRequest item) {
        return item != null;
    }
}
