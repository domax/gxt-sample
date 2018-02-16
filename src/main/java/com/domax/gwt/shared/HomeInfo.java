package com.domax.gwt.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class HomeInfo {
    private Long id;
    private List<String> lines;
    private Date timestamp;
}
