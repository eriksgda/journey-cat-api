package com.project.journey_cat.link;

import com.project.journey_cat.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    public LinkCreateResponse registerLink(LinkRequest request, Trip trip){
        Link newLink = new Link(request.title(), request.url(), trip);

        this.repository.save(newLink);

        return new LinkCreateResponse(newLink.getId());
    }

    public List<LinkData> getAllLinks(UUID trip_id){
        return this.repository.findByTripId(trip_id).stream().map(links -> new LinkData(links.getId(), links.getTitle(), links.getUrl())).toList();
    }
}
