package com.snip.enum;

import java.util.Arrays;

/**
 * Enum representing the fields that can be used for sorting.
 */
public enum SortField {
    /**
     * Alias of the snip.
     */
    ALIAS,
    /**
     * Date and time when the snip was created.
     */
    CREATED_AT,
    /**
     * Total number of clicks on the snip.
     */
    TOTAL_CLICKS;

    /**
     * Returns all the sort fields.
     * @return an array of all sort fields
     */
    public static SortField[] getAllSortFields() {
        return values();
    }

    /**
     * Returns the sort field from the given string.
     * @param sortField the string representation of the sort field
     * @return the sort field, or null if not found
     */
    public static SortField getSortField(String sortField) {
        return Arrays.stream(values())
                .filter(field -> field.name().equalsIgnoreCase(sortField))
                .findFirst()
                .orElse(null);
    }
}