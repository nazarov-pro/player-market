package com.shahinnazarov.player.utils.converters;

import com.shahinnazarov.common.container.BaseConverter;
import com.shahinnazarov.player.container.dto.PlayerResponse;
import com.shahinnazarov.player.container.entities.PlayerEntity;
import com.shahinnazarov.player.services.TransferFeeCalculator;
import com.shahinnazarov.player.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerResponseConverter implements BaseConverter<PlayerEntity, PlayerResponse> {
    private final TransferFeeCalculator transferFeeCalculator;

    @Override
    public PlayerResponse convert(PlayerEntity item) {
        PlayerResponse response = new PlayerResponse();
        response.setDateOfBirth(item.getDateOfBirth().format(Constants.DATE_FORMATTER));
        response.setFirstname(item.getFirstname());
        response.setLastname(item.getLastname());
        response.setId(item.getId());
        response.setMonthsOfExperience(item.getMonthsOfExperience());
        response.setTransferFee(transferFeeCalculator.calculateTransferFee(item));
        return response;
    }

    @Override
    public boolean validate(PlayerEntity item) {
        return item != null;
    }
}
