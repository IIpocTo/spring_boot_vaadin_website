package favourites.rest.controllers;

import favourites.dao.DomainOperations;
import favourites.domain.EntityType;
import favourites.domain.Favourite;
import favourites.notification.NotificationSender;
import favourites.rest.resources.DomainResource;
import favourites.rest.resources.FavouriteResource;
import favourites.rest.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RESTOperationsImpl implements RESTOperations {

    private final DomainOperations domainOperations;
    private final NotificationSender sender;

    @Autowired
    public RESTOperationsImpl(DomainOperations domainOperations, NotificationSender sender) {
        this.domainOperations = domainOperations;
        this.sender = sender;
    }

    @Override
    public DomainResource readById(String id, String entityType) {
        return DomainResource.convertToResource(domainOperations.findById(EntityType.value(entityType), id));
    }

    @Override
    public List<DomainResource> readAll(String entityType, String username) {
        if (EntityType.value(entityType) != EntityType.FAVOURITE)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        return domainOperations.findAll(username).stream()
                .map(fv -> new FavouriteResource((Favourite) fv))
                .collect(Collectors.toList());
    }

    @Override
    public void create(String entityType, DomainResource resource, String username) {
        domainOperations.save(resource.convertToDomainObject(true, username));
        if (EntityType.value(entityType) == EntityType.USER) {
            UserResource userResource = (UserResource) resource;
            sender.successRegistrationNotificationSend(userResource.getEmail(), username, userResource.getPassword());
        }
    }

    @Override
    public DomainResource update(String entityType, DomainResource resource) {
        domainOperations.update(EntityType.value(entityType), resource.convertToDomainObject(false, null));
        return this.readById(resource.getUid(), entityType);
    }

    @Override
    public void delete(String entityType, String uid) {
        domainOperations.remove(EntityType.valueOf(entityType), uid);
    }

}
