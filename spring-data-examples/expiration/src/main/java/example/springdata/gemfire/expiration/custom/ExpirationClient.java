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
package example.springdata.gemfire.expiration.custom;

import example.springdata.gemfire.expiration.Address;
import org.awaitility.Awaitility;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author Patrick Johnson
 */
@SpringBootApplication
public class ExpirationClient {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ExpirationClient.class).web(WebApplicationType.NONE).build().run(args);
	}

	@Bean
	public ApplicationRunner runner(CustomerRepository customerRepository, ProductRepository productRepository) {
		return args -> {
			System.out.println("--- Cache Defined Expiration ---");
			cacheDefinedExpirationIsConfiguredCorrectly(customerRepository);

			System.out.println("--- Custom Expiration ---");
			customExpirationIsConfiguredCorrectly(productRepository);
		};
	}

	public void cacheDefinedExpirationIsConfiguredCorrectly(CustomerRepository customerRepository) {

		customerRepository.save(new Customer(1L, new EmailAddress("address@website.domain"),
				"John", "Smith",
				new Address("711 Larch Ct", "Paris", "Alderaan")));

		System.out.printf("Starting TTL wait period: %s%n", Instant.now());
		// Due to the constant "getting" of the entry, the idle expiry timeout will not be met and the time-to-live
		// will be used.
		Awaitility.await().pollInterval(1, TimeUnit.SECONDS).atMost(10, TimeUnit.SECONDS)
				.until(() -> customerRepository.findById(1L).isEmpty());

		System.out.printf("Ending TTL wait period: %s%n", Instant.now());

		customerRepository.save(new Customer(1L, new EmailAddress("email@address.com"),
				"Jane", "Doe",
				new Address("345 1st St", "Sparta", "Greece")));

		System.out.printf("Starting Idle wait period: %s%n", Instant.now());

		// Due to the delay in "getting" the entry, the idle timeout of 2s should delete the entry.
		Awaitility.await().pollDelay(2, TimeUnit.SECONDS).pollInterval(100, TimeUnit.MILLISECONDS)
				.atMost(10, TimeUnit.SECONDS).until(() -> customerRepository.findById(1L).isEmpty());

		System.out.printf("Ending Idle wait period: %s%n", Instant.now());
	}

	public void customExpirationIsConfiguredCorrectly(ProductRepository productRepository) {
		productRepository.save(new Product(3L, "MacBook Pro", BigDecimal.valueOf(20), "A cool computing device"));

		System.out.printf("Starting TTL wait period: %s%n", Instant.now());
		// Due to the constant "getting" of the entry, the idle expiry timeout will not be met and the time-to-live
		// will be used.
		Awaitility.await().pollInterval(1, TimeUnit.SECONDS).atMost(10, TimeUnit.SECONDS)
				.until(() -> productRepository.findById(3L).isEmpty());

		System.out.println("Ending TTL wait period: " + Instant.now());

		productRepository.save(new Product(3L, "MacBook Pro", BigDecimal.valueOf(20), "A cool computing device"));

		System.out.printf("Starting Idle wait period: %s%n", Instant.now());

		// Due to the delay in "getting" the entry, the idle timeout of 2s should delete the entry.
		Awaitility.await().pollDelay(2, TimeUnit.SECONDS).pollInterval(100, TimeUnit.MILLISECONDS)
				.atMost(10, TimeUnit.SECONDS).until(() -> productRepository.findById(3L).isEmpty());

		System.out.printf("Ending Idle wait period: %s%n", Instant.now());
	}
}
