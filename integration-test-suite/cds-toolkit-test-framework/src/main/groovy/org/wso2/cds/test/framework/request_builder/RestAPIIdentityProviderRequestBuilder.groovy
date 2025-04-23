/**
 * Copyright (c) 2025, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.cds.test.framework.request_builder

import com.nimbusds.oauth2.sdk.http.HTTPResponse
import io.restassured.response.Response
import org.json.JSONArray
import org.testng.Assert
import org.wso2.cds.test.framework.configuration.AUConfigurationService
import org.wso2.cds.test.framework.constant.AUConstants
import org.wso2.cds.test.framework.utility.AURestAsRequestBuilder

import java.nio.charset.Charset

/**
 * Class to build requests to create Identity Provider.
 */
class RestAPIIdentityProviderRequestBuilder {

    private AUConfigurationService auConfiguration = new AUConfigurationService()
    String baseURL = AUConstants.REST_API_GET_AUTHENTICATORS_ENDPOINT
    String authenticatorId

    def authToken = "${auConfiguration.getUserKeyManagerAdminName()}:${auConfiguration.getUserKeyManagerAdminPWD()}"
    String basicHeader = "Basic ${Base64.encoder.encodeToString(authToken.getBytes(Charset.defaultCharset()))}"

    String getAuthenticatorId() {

        URI getAuthenticatorEndPoint = new URI("${auConfiguration.getServerAuthorisationServerURL()}" +
                AUConstants.REST_API_GET_AUTHENTICATORS_ENDPOINT)

        String authenticatorName = AUConstants.SMS_OTP_AUTHENTICATOR_NAME;

        def response = AURestAsRequestBuilder.buildRequest()
                 .header(AUConstants.AUTHORIZATION_HEADER_KEY, basicHeader)
                .get(getAuthenticatorEndPoint.toString())

        Assert.assertEquals(response.statusCode(), HTTPResponse.SC_OK)

        JSONArray authenticators = new JSONArray(response.body().asString())
        for (int i=0; i<authenticators.length(); i++) {
            if (authenticators.getJSONObject(i).getString("name") == authenticatorName) {
                authenticatorId = authenticators.getJSONObject(i).getString("authenticatorId").toString()
            }
        }
    }

    Response createIdentityProvider() {

        URI createIDPEndpoint = new URI("${auConfiguration.getServerAuthorisationServerURL()}" +
                AUConstants.REST_API_GET_AUTHENTICATORS_ENDPOINT)
        return AURestAsRequestBuilder.buildRequest()
                .contentType(AUConstants.CONTENT_TYPE_APPLICATION_JSON)
                .header(AUConstants.AUTHORIZATION_HEADER_KEY, basicHeader)
                .body(getIDPCreationPayload("localhost", authenticatorId))
                .post(createIDPEndpoint.toString())
    }

    String getIDPCreationPayload(String hostName, String authenticatorId) {

        return """
            {
              "name": "SMSAuthentication",
              "description": "IDP for SMS OTP",
              "isPrimary": false,
              "isFederationHub": false,
              "homeRealmIdentifier": "$hostName",
              "alias": "https://$hostName:9446/oauth2/token",
              "federatedAuthenticators": {
                "defaultAuthenticatorId": "$authenticatorId",
                 "authenticators": [
                   {
                      "authenticatorId": "$authenticatorId",
                       "isEnabled": true,
                       "isDefault": true,
                        "properties": [
                         {
                            "key": "sms_url",
                            "value": "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0?username=miuperera&password=NZ5g7*CQZmYZ&2UY&message=\$ctx.msg&msisdn=\$ctx.num"
                         },
                         { 
                           "key": "http_method",
                           "value" : "POST"
                         },
                         {
                           "key" : "headers",
                           "value" : "Content-Type: application/x-www-form-urlencoded"
                         },
                         {
                            "key" : "http_response",
                            "value" : "200"
                         }
                        ]
                   }
                 ]
              }
            }
            """.stripIndent()

    }
}
