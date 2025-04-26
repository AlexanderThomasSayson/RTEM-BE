package com.ats.rtem.common.utils;

import com.ats.rtem.domain.entity.User;

import java.util.StringJoiner;

public class NameUtils {

    private NameUtils() {
        // private constructor to prevent instantiation
    }

    public static String buildFullName(User user) {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(user.getFirstName());
        if (user.getMiddleName() != null && !user.getMiddleName().isBlank()) {
            joiner.add(user.getMiddleName());
        }
        joiner.add(user.getLastName());
        return joiner.toString();
    }
}
