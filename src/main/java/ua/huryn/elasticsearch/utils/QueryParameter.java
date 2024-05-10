package ua.huryn.elasticsearch.utils;

import java.util.*;
import java.util.stream.Collectors;

public class QueryParameter {
    public static Set<String> getStringSetFromQueryParameters(Map<String, List<String>> queryParams, String key) {
        if (queryParams.containsKey(key) && !queryParams.get(key).isEmpty()) {
            String param = queryParams.get(key).get(0);
            if (param != null && !param.trim().isEmpty()) {
                return Set.of(param.trim().split(",\\s*"));
            }
        }
        return Collections.emptySet();
    }

    public static Set<Integer> getIntegerSetFromQueryParameters(Map<String, List<String>> queryParams, String key) {
        Set<String> stringSet = getStringSetFromQueryParameters(queryParams, key);
        if (stringSet.isEmpty()) {
            return Collections.emptySet();
        }

        return stringSet.stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(s -> {
                    try {
                        return Integer.parseInt(s.trim());
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
