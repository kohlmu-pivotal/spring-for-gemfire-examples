/*
 * Copyright 2020-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.gemfire.expiration.entity;

import example.springdata.gemfire.expiration.Address;
import org.awaitility.Awaitility;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author Patrick Johnson
 */
@SpringBootApplication
public class EntityDefinedExpirationClient {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EntityDefinedExpirationClient.class).web(WebApplicationType.NONE).build().run(args);
    }

    @Bean
    public ApplicationRunner runner(OrderRepository orderRepository) {
        return args -> {
            System.out.println("--- Entity Defined Expiration ---");
            orderRepository.save(new Order(1L, 50L,
                    new Address("123 Main St", "Metropolis", "Narnia")));

            System.out.println("Starting TTL wait period: " + Instant.now());
            // Due to the constant "getting" of the entry, the idle expiry timeout will not be met and the time-to-live
            // will be used.
            Awaitility.await().pollInterval(1, TimeUnit.SECONDS).atMost(10, TimeUnit.SECONDS)
                    .until(() -> orderRepository.findById(1L).isEmpty());

            System.out.println("Ending TTL wait period: " + Instant.now());

            orderRepository.save(new Order(1L, 50L,
                    new Address("111 3rd Ave", "Townsville", "Mordor")));

            System.out.println("Starting Idle wait period: " + Instant.now());

            // Due to the delay in "getting" the entry, the idle timeout of 2s should delete the entry.
            Awaitility.await().pollDelay(2, TimeUnit.SECONDS).pollInterval(100, TimeUnit.MILLISECONDS)
                    .atMost(10, TimeUnit.SECONDS).until(() -> orderRepository.findById(1L).isEmpty());

            System.out.println("Ending Idle wait period: " + Instant.now());
        };
    }
}
