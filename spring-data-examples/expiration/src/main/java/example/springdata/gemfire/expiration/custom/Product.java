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

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A product used in the examples.
 *
 * @author Oliver Gierke
 * @author David Turanski
 * @author Udo Kohlmeyer
 * @author Patrick Johnson
 */
@Region("Products")
public record Product (@Id Long id, String name, BigDecimal price, String description) implements Serializable {

}
