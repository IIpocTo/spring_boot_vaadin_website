package favourites.rest.controllers;

import favourites.rest.resources.DomainResource;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface RESTOperations {

    String BASE_URI = "/favourites/{entityType}";
    String RESOURCE_URI = "/favourites/{entityType}/{uid}";

    DomainResource readById(@NotNull String id, @NotNull String entityType);
    @NotNull List<DomainResource> readAll(@NotNull String entityType);
    void create(@NotNull String entityType, @NotNull DomainResource resource);
    @NotNull DomainResource update(@NotNull String entityType, @NotNull DomainResource resource);
    void delete(@NotNull String entityType, @NotNull String uid);

}
