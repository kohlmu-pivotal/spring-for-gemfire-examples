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

import java.io.Serializable;

/**
 * An address used in the examples.
 *
 * @author Oliver Gierke
 * @author Udo Kohlmeyer
 * @author Patrick Johnson
 */
public class Address implements PdxSerializable {

	private String street;
	private String city;
	private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address(String street, String city, String country) {
		this.street = street;
		this.city = city;
		this.country = country;
	}

    public Address() {
    }

    @Override
    public void toData(PdxWriter pdxWriter) {
        pdxWriter.writeString("street", street)
                .writeString("city", city)
                .writeString("country", country);
    }

    @Override
    public void fromData(PdxReader pdxReader) {
        street = pdxReader.readString("street");
        city = pdxReader.readString("city");
        country = pdxReader.readString("country");
    }
}
