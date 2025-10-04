package org.workswap.stat.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.workswap.common.dto.stat.UsersStatSnapshotDTO;
import org.workswap.core.services.command.StatisticCommandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersStatConsumer {
    
    private final StatisticCommandService statisticCommandService;

    @RabbitListener(queues = "usersStatQueue")
    public void usersStatQueue(UsersStatSnapshotDTO dto) {
        
        statisticCommandService.saveUsersStatSnapshot(dto);
    }
}
