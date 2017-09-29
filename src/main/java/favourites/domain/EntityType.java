package favourites.domain;

import javax.validation.constraints.NotNull;

public enum EntityType {
    FAVOURITE("favourite"),
    USER("user_t");

    private final String name;

    EntityType(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public static EntityType value(@NotNull String name) {
        if (name.equalsIgnoreCase(FAVOURITE.name)) return FAVOURITE;
        else if (name.equalsIgnoreCase(USER.name)) return USER;
        else return EntityType.valueOf(name.toUpperCase());
    }

}
