/**
 * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com).
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

package org.wso2.cds.test.framework.constant

/**
 * Enum class for keeping account Profiles Eg: Business and Individual.
 */
enum AUAccountProfile {

    ORGANIZATION_A("Organization A"),
    ORGANIZATION_B("Organization B"),
    INDIVIDUAL("Individual"),

    private String value

    AUAccountProfile(String value) {
        this.value = value
    }

    String getProfileString() {
        return this.value
    }
}