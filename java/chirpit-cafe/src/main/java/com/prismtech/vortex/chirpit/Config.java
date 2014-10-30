/*
 * Copyright 2014 PrismTech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prismtech.vortex.chirpit;

public final class Config {
    /**
     * The bootstrap class name, used for DDS initialization with Vortex Cafe.
     */
    public static final String DDS_BOOTSTRAP_CLASS = "com.prismtech.cafe.core.ServiceEnvironmentImpl";

    public static final String LOGGING_TAG = "ChirpIt";

    public static final String LOGGING_TAG_IN = "ChirpIn";

    public static final String LOGGING_TAG_OUT = "ChirpOut";

    public static final int DEFAULT_DOMAIN_ID = 1;

    public static final String CHIRP_ACTION_TOPIC = "ChirpAction";
}
