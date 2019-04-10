/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

/**
 * KERBEROS authentication scheme.
 *
 * @since 4.2
 */
@NotThreadSafe
public class KerberosScheme extends GGSSchemeBase {

    private static final String KERBEROS_OID = "1.2.840.113554.1.2.2";

    /**
     * @since 4.4
     */
    public KerberosScheme(final boolean stripPort, final boolean useCanonicalHostname) {
        super(stripPort, useCanonicalHostname);
    }

    public KerberosScheme(final boolean stripPort) {
        super(stripPort);
    }

    public KerberosScheme() {
        super();
    }

    @Override
    public String getSchemeName() {
        return "Kerberos";
    }

    /**
     * Produces KERBEROS authorization Header based on token created by
     * processChallenge.
     *
     * @param credentials not used by the KERBEROS scheme.
     * @param request The request being authenticated
     *
     * @throws AuthenticationException if authentication string cannot
     *   be generated due to an authentication failure
     *
     * @return KERBEROS authentication Header
     */
    @Override
    public Header authenticate(
            final Credentials credentials,
            final HttpRequest request,
            final HttpContext context) throws AuthenticationException {
        return super.authenticate(credentials, request, context);
    }

    @Override @SuppressWarnings("deprecation")
    protected byte[] generateToken(final byte[] input, final String authServer) throws GSSException {
        return super.generateToken(input, authServer);
    }

    @Override
    protected byte[] generateToken(final byte[] input, final String authServer, final Credentials credentials) throws GSSException {
        return generateGSSToken(input, new Oid(KERBEROS_OID), authServer, credentials);
    }

    /**
     * There are no valid parameters for KERBEROS authentication so this
     * method always returns {@code null}.
     *
     * @return {@code null}
     */
    @Override
    public String getParameter(final String name) {
        Args.notNull(name, "Parameter name");
        return null;
    }

    /**
     * The concept of an authentication realm is not supported by the Negotiate
     * authentication scheme. Always returns {@code null}.
     *
     * @return {@code null}
     */
    @Override
    public String getRealm() {
        return null;
    }

    /**
     * Returns {@code true}. KERBEROS authentication scheme is connection based.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isConnectionBased() {
        return true;
    }

}
