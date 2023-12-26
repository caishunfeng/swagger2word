/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.word.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * This class is used to get the properties from the classpath.
 */
@Slf4j
public class ImmutablePropertyDelegate {

    private static final String PROPERTIES_NAME = "i18n/messages_zh_CN.properties";

    private final Properties properties;

    public ImmutablePropertyDelegate() {
        properties = new Properties();
        Resource resource = new ClassPathResource(PROPERTIES_NAME);
        try (InputStream fis = resource.getInputStream()) {
            properties.load(new InputStreamReader(fis, StandardCharsets.UTF_8));
            properties.load(fis);
        } catch (IOException e) {
            log.error("Load property: {} error, please check if the file exist under classpath", PROPERTIES_NAME, e);
            System.exit(1);
        }
        printProperties();
    }

    public ImmutablePropertyDelegate(Properties properties) {
        this.properties = properties;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public Set<String> getPropertyKeys() {
        return properties.stringPropertyNames();
    }

    private void printProperties() {
        properties.forEach((k, v) -> log.info("Get property {} -> {}", k, v));
    }
}
