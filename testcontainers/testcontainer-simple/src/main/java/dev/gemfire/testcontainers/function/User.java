package dev.gemfire.testcontainers.function;

import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("User")
public record User(String name, int age) {
}
