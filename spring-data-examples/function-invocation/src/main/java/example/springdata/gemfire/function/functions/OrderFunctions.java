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
package example.springdata.gemfire.function.functions;

import example.springdata.gemfire.function.Order;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Patrick Johnson
 */
public class OrderFunctions implements Function {

	@Override
	public void execute(FunctionContext functionContext) {
		Object[] args = (Object[]) functionContext.getArguments();
		Long orderId = (Long)args[0];
		BigDecimal total = ((Order) functionContext.getCache().getRegion("Orders").get(orderId)).calcTotal();

		functionContext.getResultSender().lastResult(total);
	}

	@Override
	public String getId() {
		return "sumPricesForAllProductsForOrderFnc";
	}
}
