package org.workswap.stat.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.workswap.common.dto.stat.ListingViewDTO;
import org.workswap.core.services.command.StatisticCommandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListingViewConsumer {
    
    private final StatisticCommandService statisticCommandService;

    @RabbitListener(queues = "listingViewQueue")
    public void listingViewQueue(ListingViewDTO dto) {

        statisticCommandService.addListingView(dto.getUserId(), dto.getListingId());

    }
}
