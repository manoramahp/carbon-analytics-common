/*
 * Copyright (c) 2005-2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.event.output.adapter.websocket.local;


import org.wso2.carbon.event.output.adapter.core.*;
import org.wso2.carbon.event.output.adapter.websocket.local.internal.util.WebsocketLocalEventAdapterConstants;
import org.wso2.carbon.utils.CarbonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebsocketLocalEventAdapterFactory extends OutputEventAdapterFactory {
    private int httpPort;
    private int httpsPort;
    private int portOffset;

    public WebsocketLocalEventAdapterFactory() {
        portOffset = getPortOffset();
        httpPort = WebsocketLocalEventAdapterConstants.DEFAULT_HTTP_PORT + portOffset;
        httpsPort = WebsocketLocalEventAdapterConstants.DEFAULT_HTTPS_PORT + portOffset;
    }

    @Override
    public String getType() {
        return WebsocketLocalEventAdapterConstants.ADAPTER_TYPE_WEBSOCKET_LOCAL;
    }

    @Override
    public List<String> getSupportedMessageFormats() {
        List<String> supportedMessageFormats = new ArrayList<String>();
        supportedMessageFormats.add(MessageType.TEXT);
        supportedMessageFormats.add(MessageType.XML);
        supportedMessageFormats.add(MessageType.JSON);
        return supportedMessageFormats;
    }

    @Override
    public List<Property> getStaticPropertyList() {
        return null;
    }

    @Override
    public List<Property> getDynamicPropertyList() {
        return null;
    }

    @Override
    public String getUsageTips() {
        return WebsocketLocalEventAdapterConstants.ADAPTOR_USAGE_TIPS_PREFIX + httpPort + WebsocketLocalEventAdapterConstants.ADAPTER_USAGE_TIPS_MID + httpsPort + WebsocketLocalEventAdapterConstants.ADAPTER_USAGE_TIPS_POSTFIX;
    }

    @Override
    public OutputEventAdapter createEventAdapter(OutputEventAdapterConfiguration eventAdapterConfiguration, Map<String, String> globalProperties) {
        return new WebsocketLocalEventAdapter(eventAdapterConfiguration, globalProperties);
    }

    private int getPortOffset() {
        return CarbonUtils.getPortFromServerConfig(WebsocketLocalEventAdapterConstants.CARBON_CONFIG_PORT_OFFSET_NODE) + 1;
    }
}
