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
package example.springdata.gemfire.function.client;

import example.springdata.gemfire.function.Customer;
import example.springdata.gemfire.function.Order;
import example.springdata.gemfire.function.Product;

import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnablePdx;

/**
 * Spring JavaConfig configuration class to setup a Spring container and infrastructure components.
 *
 * @author Udo Kohlmeyer
 * @author Patrick Johnson
 */
@Configuration
@EnableClusterConfiguration(useHttp = true, requireHttps = false)
public class FunctionInvocationClientConfig {

	@Bean("Products")
	public ClientRegionFactoryBean<Long, Product> createProductRegion(GemFireCache gemFireCache) {
		ClientRegionFactoryBean<Long, Product> regionFactoryBean = new ClientRegionFactoryBean<>();
		regionFactoryBean.setCache(gemFireCache);
		regionFactoryBean.setShortcut(ClientRegionShortcut.PROXY);
		regionFactoryBean.setName("Products");
		return regionFactoryBean;
	}

	@Bean("Customers")
	public ClientRegionFactoryBean<Long, Customer> createCustomerRegion(GemFireCache gemFireCache) {
		ClientRegionFactoryBean<Long, Customer> regionFactoryBean = new ClientRegionFactoryBean<>();
		regionFactoryBean.setCache(gemFireCache);
		regionFactoryBean.setShortcut(ClientRegionShortcut.PROXY);
		regionFactoryBean.setName("Customers");
		return regionFactoryBean;
	}

	@Bean("Orders")
	public ClientRegionFactoryBean<Long, Order> createOrderRegion(GemFireCache gemFireCache) {
		ClientRegionFactoryBean<Long, Order> regionFactoryBean = new ClientRegionFactoryBean<>();
		regionFactoryBean.setCache(gemFireCache);
		regionFactoryBean.setShortcut(ClientRegionShortcut.PROXY);
		regionFactoryBean.setName("Orders");
		return regionFactoryBean;
	}
}
