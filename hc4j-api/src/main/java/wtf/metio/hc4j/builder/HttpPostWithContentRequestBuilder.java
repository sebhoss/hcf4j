/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.builder;

import wtf.metio.hc4j.HttpRequest;

/**
 *
 *
 */
public interface HttpPostWithContentRequestBuilder {

    /**
     * @param mediaType
     *            The media type to use.
     * @return The configured {@link HttpRequest}.
     */
    HttpRequest mediaType(String mediaType);

}