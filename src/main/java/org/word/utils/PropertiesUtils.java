package org.word.utils;

import org.word.config.ImmutablePropertyDelegate;

import com.google.common.base.Strings;

public class PropertiesUtils {

    private static final ImmutablePropertyDelegate propertyDelegate = new ImmutablePropertyDelegate();

    public static String getString(String key) {
        return propertyDelegate.get(key.trim());
    }

    public static String getString(String key, String defaultVal) {
        String val = getString(key);
        return Strings.isNullOrEmpty(val) ? defaultVal : val;
    }
}
