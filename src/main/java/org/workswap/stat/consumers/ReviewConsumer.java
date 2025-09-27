package org.workswap.stat.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.workswap.common.dto.ReviewDTO;
import org.workswap.core.services.command.StatisticCommandService;
import org.workswap.core.services.query.ListingQueryService;
import org.workswap.core.services.query.UserQueryService;
import org.workswap.datasource.central.model.Listing;
import org.workswap.datasource.central.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewConsumer {
    
    private final ListingQueryService listingQueryService;
    private final UserQueryService userQueryService;
    private final StatisticCommandService statisticCommandService;

    @RabbitListener(queues = "reviewsQueue")
    public void reviewsQueue(ReviewDTO dto) {
        Listing listing = listingQueryService.findListing(dto.getListingId().toString());
        User profile = userQueryService.findUser(dto.getProfileId().toString());

        if (listing != null) {
            statisticCommandService.updateRatingForListing(listing);
        } else if (profile != null) {
            statisticCommandService.updateRatingForUser(profile);
        }
    }
}
