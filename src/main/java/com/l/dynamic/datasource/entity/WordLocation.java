package com.l.dynamic.datasource.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class WordLocation {
    private String top;
    private String left;
    private String width;
    private String height;
}
