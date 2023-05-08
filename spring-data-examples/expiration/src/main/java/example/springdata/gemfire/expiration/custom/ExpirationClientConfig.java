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

import org.apache.geode.cache.CustomExpiry;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableExpiration;
import org.springframework.data.gemfire.expiration.ExpirationActionType;

/**
 * @author Patrick Johnson
 */
@Configuration
@EnableClusterConfiguration(useHttp = true, requireHttps = false)
@EnableExpiration(policies = {
		@EnableExpiration.ExpirationPolicy(timeout = 2, action = ExpirationActionType.INVALIDATE,
				regionNames = { "Customers" }, types = { EnableExpiration.ExpirationType.TIME_TO_LIVE }),
		@EnableExpiration.ExpirationPolicy(timeout = 1, action = ExpirationActionType.INVALIDATE,
				regionNames = { "Customers" }, types = { EnableExpiration.ExpirationType.IDLE_TIMEOUT }) })
public class ExpirationClientConfig {

	@Bean("IDLE")
	CustomExpiry<Long, Product> createIdleExpiration() {
		return new CustomProductExpiry(2);
	}

	@Bean("TTL")
	CustomExpiry<Long, Product> createTtlExpiration() {
		return new CustomProductExpiry(4);
	}

	@Bean("Products")
	public ClientRegionFactoryBean<Long, Product> createProductRegion(GemFireCache gemFireCache,
			@Qualifier("IDLE") CustomExpiry<Long, Product> idleExpiry,
			@Qualifier("TTL") CustomExpiry<Long, Product> ttlExpiry) {
		ClientRegionFactoryBean<Long, Product> regionFactoryBean = new ClientRegionFactoryBean<>();
		regionFactoryBean.setCache(gemFireCache);
		regionFactoryBean.setShortcut(ClientRegionShortcut.CACHING_PROXY);
		regionFactoryBean.setName("Products");
		regionFactoryBean.setCustomEntryIdleTimeout(idleExpiry);
		regionFactoryBean.setCustomEntryTimeToLive(ttlExpiry);
		return regionFactoryBean;
	}

	@Bean("Customers")
	public ClientRegionFactoryBean<Long, Customer> createCustomerRegion(GemFireCache gemFireCache) {
		ClientRegionFactoryBean<Long, Customer> regionFactoryBean = new ClientRegionFactoryBean<>();
		regionFactoryBean.setCache(gemFireCache);
		regionFactoryBean.setShortcut(ClientRegionShortcut.CACHING_PROXY);
		regionFactoryBean.setName("Customers");
		return regionFactoryBean;
	}
}
