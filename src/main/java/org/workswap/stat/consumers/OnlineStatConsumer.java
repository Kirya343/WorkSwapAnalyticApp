package org.workswap.stat.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.workswap.common.dto.stat.OnlineStatSnapshotDTO;
import org.workswap.core.services.command.StatisticCommandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OnlineStatConsumer {
    
    private final StatisticCommandService statisticCommandService;

    @RabbitListener(queues = "onlineStatQueue")
    public void onlineStatQueue(OnlineStatSnapshotDTO dto) {
        
        statisticCommandService.saveOnlineStatSnapshot(dto.getOnline(), dto.getTimestamp());
    }
}
