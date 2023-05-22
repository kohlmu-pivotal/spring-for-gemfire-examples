/*
 * Copyright 2017-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package example.app.caching.near.client.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * {@link Person} models a person (human being).
 *
 * @author John Blum
 * @since 1.1.0
 */
// tag::class[]
@Getter
@EqualsAndHashCode(of = "name")
@ToString(of = { "name", "email", "phoneNumber" })
@RequiredArgsConstructor(staticName = "newPerson")
public class Person {

	@NonNull
	private String name;

	private String email;
	private String phoneNumber;

	public Person withEmail(String email) {
		this.email = email;
		return this;
	}

	public Person withPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
}
// end::class[]
