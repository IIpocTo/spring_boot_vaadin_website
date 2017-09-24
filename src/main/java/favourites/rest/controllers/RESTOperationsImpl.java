package favourites.rest.controllers;

import favourites.rest.resources.DomainResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RESTOperationsImpl implements RESTOperations {

    @Override
    public DomainResource readById(String id, String entityType) {
        return null;
    }

    @Override
    public List<DomainResource> readAll(String entityType) {
        return null;
    }

    @Override
    public void create(String entityType, DomainResource resource) {

    }

    @Override
    public DomainResource update(String entityType, DomainResource resource) {
        return null;
    }

    @Override
    public void delete(String entityType, String uid) {

    }

}
