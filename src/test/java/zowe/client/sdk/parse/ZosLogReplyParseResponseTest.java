/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package zowe.client.sdk.parse;

import org.json.simple.JSONObject;
import org.junit.Test;
import zowe.client.sdk.parse.type.ParseType;
import zowe.client.sdk.zoslogs.response.ZosLogReply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Class containing unit tests for ZosLogReplyParseResponse.
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class ZosLogReplyParseResponseTest {

    @Test
    public void tstZosLogReplyParseJsonStopResponseNullFail() {
        String msg = "";
        try {
            JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY).setJsonObject(null);
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assertEquals("data is null", msg);
    }

    @Test
    public void tstZosLogReplyParseJsonStopResponseSingletonSuccess() {
        final JsonParse parser = JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY);
        final JsonParse parser2 = JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY);
        assertSame(parser, parser2);
    }

    @Test
    public void tstZosLogReplyParseJsonStopResponseSingletonWithDataSuccess() {
        final JsonParse parser =
                JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY).setJsonObject(new JSONObject());
        final JsonParse parser2 =
                JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY).setJsonObject(new JSONObject());
        assertSame(parser, parser2);
    }

    @Test
    public void tstUnixZfsParseJsonStopResponseResetDataModeFail() {
        String msg = "";
        try {
            JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY).setJsonObject(new JSONObject());
            JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY).parseResponse();
            JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY).parseResponse();
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assertEquals(ParseConstants.REQUIRED_ACTION_ZOS_LOG_ITEMS_MSG, msg);
        try {
            final ZosLogReplyJsonParse parser = (ZosLogReplyJsonParse)
                    JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY).setJsonObject(new JSONObject());
            parser.setZosLogItems(new ArrayList<>());
            parser.parseResponse();
            parser.parseResponse();
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assertEquals(ParseConstants.REQUIRED_ACTION_MSG, msg);
    }

    @Test
    public void tstZosLogReplyParseJsonStopResponseSuccess() {
        final Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("totalitems", 1L);
        jsonMap.put("source", "dev");
        final JSONObject json = new JSONObject(jsonMap);

        final ZosLogReplyJsonParse parser = (ZosLogReplyJsonParse)
                JsonParseFactory.buildParser(ParseType.ZOS_LOG_REPLY).setJsonObject(json);
        parser.setZosLogItems(new ArrayList<>());

        final ZosLogReply response = parser.parseResponse();
        assertEquals(Long.parseLong("1"), response.getTotalItems().orElse(-1L));
        assertEquals("dev", response.getSource().orElse("n\\a"));
    }

}
