package com.testautomation.models;

import lombok.*;

import java.util.Objects;


//@AllArgsConstructor
//@ToString
@Data
@EqualsAndHashCode(exclude = {"lastName"})
@Builder
@AllArgsConstructor
public class Foo {
    /*
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private int age;
    @Getter @Setter
    private String email;
    */

    private String name;
    private String lastName;
    private String email;
    private int age;


}
