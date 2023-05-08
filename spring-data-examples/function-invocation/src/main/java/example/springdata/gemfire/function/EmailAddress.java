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
 * Value object to represent email addresses.
 *
 * @author Udo Kohlmeyer
 * @author Patrick Johnson
 */
public class EmailAddress implements PdxSerializable {
    private String value;

    public EmailAddress() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EmailAddress(String value) {
        this.value = value;
    }

    @Override
    public void toData(PdxWriter pdxWriter) {
        pdxWriter.writeString("value", value);
    }

    @Override
    public void fromData(PdxReader pdxReader) {
        value = pdxReader.readString("value");
    }
}