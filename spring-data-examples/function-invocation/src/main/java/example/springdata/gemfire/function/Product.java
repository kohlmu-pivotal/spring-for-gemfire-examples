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
package example.springdata.gemfire.function;

import org.apache.geode.pdx.PdxReader;
import org.apache.geode.pdx.PdxSerializable;
import org.apache.geode.pdx.PdxWriter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * A product used in the examples.
 *
 * @author Oliver Gierke
 * @author David Turanski
 * @author Udo Kohlmeyer
 * @author Patrick Johnson
 */
@Region("Products")
public class Product implements PdxSerializable {

	@Id
	private Long id;
	private String name;
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	private String description;

	public Product() {
	}

	@Transient
	private Map<String, String> attributes = new HashMap<>();

	public Product(Long id, String name, BigDecimal price, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	/**
	 * Sets the attribute with the given name to the given value.
	 *
	 * @param name  must not be null or empty.
	 * @param value
	 */
	public void addAttribute(String name, String value) {
		this.attributes.put(name, value);
	}

	@Override
	public void toData(PdxWriter pdxWriter) {
		pdxWriter.writeLong("id", id)
				.writeString("name", name)
				.writeObject("price", price)
				.writeString("description", description);
	}

	@Override
	public void fromData(PdxReader pdxReader) {
		id = pdxReader.readLong("id");
		name = pdxReader.readString("name");
		price = (BigDecimal) pdxReader.readObject("price");
		description = pdxReader.readString("description");
	}
}
