package com.evanhiggins.farmruns.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AllotmentSelectorMode {
    None("None"),
    Both("Both");

    private final String name;

    @Override
    public String toString() { return name; }
}
