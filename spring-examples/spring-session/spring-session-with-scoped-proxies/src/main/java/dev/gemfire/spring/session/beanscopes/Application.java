/*
 * Copyright 2015-present the original author or authors.
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
package dev.gemfire.spring.session.beanscopes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.session.data.gemfire.config.annotation.web.http.EnableGemFireHttpSession;
import dev.gemfire.spring.session.beanscopes.config.SessionCountConfiguration;

/**
 * A Spring Boot, Pivotal GemFire cache client, web application that reveals the current state of the HTTP Session.
 *
 * @see jakarta.servlet.http.HttpSession
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.session.data.gemfire.config.annotation.web.http.EnableGemFireHttpSession
 * @see org.springframework.stereotype.Controller
 * @see org.apache.geode.cache.client.ClientCache
 * @see org.apache.geode.cache.client.Pool
 */
@SuppressWarnings("unused")
@SpringBootApplication
@ClientCacheApplication(name = "SpringSessionDataGeodeBootSampleWithScopedProxiesClient",
        readTimeout = 15000, retryAttempts = 1, subscriptionEnabled = true)
@EnableGemFireHttpSession(poolName = "DEFAULT", maxInactiveIntervalInSeconds = 10)
@ComponentScan(basePackageClasses = SessionCountConfiguration.class)
public class Application {
    public static final String PING_RESPONSE = "PONG";
    public static final String INDEX_TEMPLATE_VIEW_NAME = "index";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
