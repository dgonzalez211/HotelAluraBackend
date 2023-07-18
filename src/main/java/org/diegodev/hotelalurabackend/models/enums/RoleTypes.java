package org.diegodev.hotelalurabackend.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {

    USER("USER"),
    ADMIN("ADMIN"),
    ANONYMOUS("ANONYMOUS");

    private final String roleName;

    @Override
    public String toString() {
        return roleName;
    }
}
