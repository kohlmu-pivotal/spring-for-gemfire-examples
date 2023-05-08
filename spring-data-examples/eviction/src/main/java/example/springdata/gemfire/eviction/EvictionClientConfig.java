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
package example.springdata.gemfire.eviction;

import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableEviction;
import org.springframework.data.gemfire.eviction.EvictionActionType;
import org.springframework.data.gemfire.eviction.EvictionPolicyType;

/**
 * @author Patrick Johnson
 */
@Configuration
@EnableClusterConfiguration(useHttp = true, requireHttps = false)
@EnableEviction(policies = @EnableEviction.EvictionPolicy(regionNames = "Orders", maximum = 10,
		action = EvictionActionType.LOCAL_DESTROY, type = EvictionPolicyType.ENTRY_COUNT))
public class EvictionClientConfig {

	@Bean("Orders")
	public ClientRegionFactoryBean<Long, Order> createOrderRegion(GemFireCache gemFireCache) {
		ClientRegionFactoryBean<Long, Order> regionFactoryBean = new ClientRegionFactoryBean<>();
		regionFactoryBean.setCache(gemFireCache);
		regionFactoryBean.setName("Orders");
		regionFactoryBean.setShortcut(ClientRegionShortcut.CACHING_PROXY);
		return regionFactoryBean;
	}
}
