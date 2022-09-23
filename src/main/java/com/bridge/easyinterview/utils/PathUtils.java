package com.bridge.easyinterview.utils;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PathUtils {

    public static final String SLASH = "/";

    public static final String RESOURCE_CANDIDATES = "candidates";
    public static final String RESOURCE_INTERVIEWERS = "interviewers";
    public static final String RESOURCE_PERIODS = "periods";

    public static String joinStringsURL(String... sections) {
        return Arrays.stream(sections).map(Object::toString).collect(Collectors.joining(SLASH));
    }

}
