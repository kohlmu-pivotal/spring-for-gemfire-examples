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
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;

/**
 * A customer used for examples.
 *
 * @author Udo Kohlmeyer
 * @author Patrick Johnson
 */
@Region(name = "Customers")
public class Customer implements PdxSerializable {

    private Long id;
    private EmailAddress emailAddress;
    private String firstName;
    private String lastName;


    public Customer(Long id, EmailAddress emailAddress, String firstName, String lastName) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public void toData(PdxWriter pdxWriter) {
        pdxWriter.writeLong("id", id)
                .writeObject("emailAddress", emailAddress)
                .writeString("firstName", firstName)
                .writeString("lastName", lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void fromData(PdxReader pdxReader) {
        id = pdxReader.readLong("id");
        emailAddress = (EmailAddress) pdxReader.readObject("emailAddress");
        firstName = pdxReader.readString("firstName");
        lastName = pdxReader.readString("lastName");
    }

}
