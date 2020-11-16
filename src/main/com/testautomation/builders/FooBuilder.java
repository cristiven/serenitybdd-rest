package com.testautomation.builders;

import com.google.inject.internal.cglib.core.$CodeGenerationException;
import com.testautomation.models.Foo;

public class FooBuilder {

    private String name;
    private String lastName;
    private String email;
    private int age;

    public FooBuilder(String name) {
        this.name = name;
        this.lastName = "defaultLastName";
        this.email = "defaultEmail";
        this.age = 56;
    }

    public static FooBuilder withName(String name){
        return new FooBuilder(name);
    }

    public FooBuilder andLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public FooBuilder andEmail(String email){
        this.email = email;
        return this;
    }

    public FooBuilder andAge(int age){
        this.age = age;
        return this;
    }

    public Foo build(){
        return new Foo(name,lastName, email, age);

    }
}
