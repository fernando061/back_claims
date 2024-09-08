package com.pe.claims.core.ValueObject;

import java.util.UUID;

public class UUIDValueObject {
    private final UUID id;

    public UUIDValueObject(String uuid) {
        this.id = parseUUID(uuid);
    }

    private UUID parseUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + uuid);
        }
    }

    public UUID getId() {
        return id;
    }
}
