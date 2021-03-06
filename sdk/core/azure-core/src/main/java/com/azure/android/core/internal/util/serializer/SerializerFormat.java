// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.core.internal.util.serializer;

import okhttp3.Headers;

/**
 * Supported serialization encoding formats.
 */
public enum SerializerFormat {
    /**
     * JavaScript Object Notation.
     */
    JSON,

    /**
     * Extensible Markup Language.
     */
    XML;

    /**
     * Determines the serializer format to use based on the Content-Type header.
     *
     * @param headers The headers to check the encoding for.
     * @return The serializer encoding to use for the body.
     */
    public static SerializerFormat fromHeaders(Headers headers) {
        String mimeContentType = headers.get("Content-Type");

        if (mimeContentType != null) {
            String[] parts = mimeContentType.split(";");

            if (parts[0].equalsIgnoreCase("application/xml")
                || parts[0].equalsIgnoreCase("text/xml")) {
                return XML;
            }
        }

        return JSON;
    }
}
