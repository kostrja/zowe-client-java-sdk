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
import zowe.client.sdk.utility.ValidateUtils;
import zowe.client.sdk.zosfiles.uss.response.UnixZfs;

/**
 * Extract UNIX zfs from json response
 *
 * @author Frank Giordano
 * @version 2.0
 */
public final class UnixZfsJsonParse implements JsonParse {

    /**
     * Represents one singleton instance
     */
    private static JsonParse INSTANCE;

    private String modeStr = "";

    /**
     * JSON data value to be parsed
     */
    private JSONObject data;

    /**
     * Private constructor defined to avoid public instantiation of class
     *
     * @author Frank Giordano
     */
    private UnixZfsJsonParse() {
    }

    /**
     * Get singleton instance
     *
     * @return UnixZfsParseResponse object
     * @author Frank Giordano
     */
    public synchronized static JsonParse getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UnixZfsJsonParse();
        }
        return INSTANCE;
    }

    /**
     * Transform data into UnixZfs object
     *
     * @return UssZfsItem object
     * @author Frank Giordano
     */
    @Override
    public UnixZfs parseResponse() {
        ValidateUtils.checkNullParameter(data == null, ParseConstants.REQUIRED_ACTION_MSG);
        ValidateUtils.checkNullParameter(modeStr.isBlank(), ParseConstants.REQUIRED_ACTION_MODE_STR_MSG);
        final UnixZfs unixZfs = new UnixZfs.Builder()
                .name(data.get("name") != null ? (String) data.get("name") : null)
                .mountpoint(data.get("mountpoint") != null ? (String) data.get("mountpoint") : null)
                .fstname(data.get("fstname") != null ? (String) data.get("fstname") : null)
                .status(data.get("status") != null ? (String) data.get("status") : null)
                .mode(modeStr)
                .dev(data.get("dev") != null ? (Long) data.get("dev") : null)
                .fstype(data.get("fstype") != null ? (Long) data.get("fstype") : null)
                .bsize(data.get("bsize") != null ? (Long) data.get("bsize") : null)
                .bavail(data.get("bavail") != null ? (Long) data.get("bavail") : null)
                .blocks(data.get("blocks") != null ? (Long) data.get("blocks") : null)
                .sysname(data.get("sysname") != null ? (String) data.get("sysname") : null)
                .readibc(data.get("readibc") != null ? (Long) data.get("readibc") : null)
                .writeibc(data.get("writeibc") != null ? (Long) data.get("writeibc") : null)
                .diribc(data.get("diribc") != null ? (Long) data.get("diribc") : null)
                .returnedRows(data.get("returnedRows") != null ? (Long) data.get("returnedRows") : null)
                .totalRows(data.get("totalRows") != null ? (Long) data.get("totalRows") : null)
                .moreRows(data.get("moreRows") != null && (boolean) data.get("moreRows"))
                .build();
        data = null;
        modeStr = "";
        return unixZfs;
    }

    /**
     * Special field that holds a String comma delimited with mode permission values.
     * Value is parsed by parent calling and set here to be included in main parse object
     * done in parseResponse() method.
     *
     * @param modeStr mode permission(s) string value
     * @author Frank Giordano
     */
    public void setModeStr(final String modeStr) {
        ValidateUtils.checkNullParameter(modeStr == null, "modeStr is null");
        this.modeStr = modeStr;
    }

    /**
     * Set the data to be parsed
     *
     * @param data json data to parse
     * @return JsonParseResponse this object
     * @author Frank Giordano
     */
    @Override
    public JsonParse setJsonObject(final JSONObject data) {
        ValidateUtils.checkNullParameter(data == null, ParseConstants.DATA_NULL_MSG);
        this.data = data;
        return this;
    }

}
