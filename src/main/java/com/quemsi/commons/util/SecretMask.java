package com.quemsi.commons.util;

/**
 * Mask secrets for logs. When credentials are env-var references ({@code useEnvVar=true}),
 * the env var <em>name</em> may be logged; resolved / literal values must never be.
 */
public final class SecretMask {
    public static final String MASKED = "*****";

    private SecretMask() {
    }

    public static String mask(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return MASKED;
    }

    /**
     * @param value       env var name when {@code useEnvVar}, otherwise literal secret
     * @param useEnvVar   when true, keep {@code value} (name only); when false, mask
     */
    public static String forLog(String value, boolean useEnvVar) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return useEnvVar ? value : MASKED;
    }

    /** Debug helper: log whether an env var is configured without printing its value. */
    public static String envStatus(String envVarName, boolean present) {
        if (envVarName == null || envVarName.isEmpty()) {
            return "(unset name)";
        }
        return envVarName + (present ? " is set" : " is not set");
    }
}
