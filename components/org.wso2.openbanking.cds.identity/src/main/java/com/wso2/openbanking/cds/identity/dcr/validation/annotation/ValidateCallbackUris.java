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
package org.wso2.openbanking.cds.identity.dcr.validation.annotation;

import org.wso2.openbanking.cds.identity.dcr.validation.impl.CallbackUrisValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation class for grant types validation.
 */
@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CallbackUrisValidator.class})
public @interface ValidateCallbackUris {

    String message() default "Invalid callback uris";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String registrationRequestProperty() default "registrationRequestProperty";

    String callbackUrisProperty() default "callbackUrisProperty";

    String ssa() default "ssa";
}
