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
import java.math.BigDecimal;

/**
 * A LineItem used in the examples
 *
 * @author Udo Kohlmeyer
 * @author Patrick Johnson
 */
public class LineItem implements PdxSerializable {

	private Product product;
	private Integer amount;

	public LineItem() {
	}

	public LineItem(Product product, Integer amount) {
		this.product = product;
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal calcTotal() {
		return product.getPrice().multiply(BigDecimal.valueOf(amount));
	}

	@Override
	public void toData(PdxWriter pdxWriter) {
		pdxWriter.writeObject("product", product)
				.writeInt("amount", amount);
	}

	@Override
	public void fromData(PdxReader pdxReader) {
		product = (Product) pdxReader.readObject("product");
		amount = pdxReader.readInt("amount");
	}
}
