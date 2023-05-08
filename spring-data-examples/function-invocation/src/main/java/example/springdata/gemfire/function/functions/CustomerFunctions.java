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

import example.springdata.gemfire.function.Customer;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Patrick Johnson
 */
public class CustomerFunctions implements Function {

	@Override
	public void execute(FunctionContext functionContext) {
		Object[] args = (Object[]) functionContext.getArguments();
		List<String> emailAddresses = List.of((String[])args[0]);
		List<Customer> collect = functionContext.getCache().getRegion("Customers").values()
				.parallelStream()
				.map((obj) -> (Customer) obj)
				.filter((customer) -> emailAddresses.contains(customer.getEmailAddress().getValue()))
				.collect(Collectors.toList());

		functionContext.getResultSender().lastResult(collect);
	}

	@Override
	public String getId() {
		return "listConsumersForEmailAddressesFnc";
	}

	@Override
	public boolean optimizeForWrite() {
		return true;
	}
}
