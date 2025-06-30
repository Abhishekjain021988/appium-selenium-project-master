package com.test.channels.qa.framework.pojo;

import lombok.*;

import java.time.LocalDate;

/**
 * @author abhishekJain
 *
 */
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TestData {
    private String id;
    private LocalDate date;
    private String data;
}