/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package zowe.client.sdk.zosfiles.uss.methods;

import org.json.simple.JSONObject;
import zowe.client.sdk.core.ZosConnection;
import zowe.client.sdk.rest.JsonPutRequest;
import zowe.client.sdk.rest.Response;
import zowe.client.sdk.rest.ZoweRequest;
import zowe.client.sdk.rest.ZoweRequestFactory;
import zowe.client.sdk.rest.type.ZoweRequestType;
import zowe.client.sdk.utility.FileUtils;
import zowe.client.sdk.utility.RestUtils;
import zowe.client.sdk.utility.ValidateUtils;
import zowe.client.sdk.zosfiles.ZosFilesConstants;
import zowe.client.sdk.zosfiles.uss.input.ChangeTagParams;
import zowe.client.sdk.zosfiles.uss.types.ChangeTagAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides Unix System Services (USS) chtag functionality
 * <p>
 * <a href="https://www.ibm.com/docs/en/zos/2.4.0?topic=interface-zos-unix-file-utilities">z/OSMF REST API</a>
 *
 * @author James Kostrewski
 * @version 2.0
 */
public class UssChangeTag {
    private final ZosConnection connection;
    private ZoweRequest request;

    /**
     * UssChangeTag Constructor
     *
     * @param connection connection information, see ZosConnection object
     * @author James Kostrewski
     */
    public UssChangeTag(ZosConnection connection) {
        ValidateUtils.checkConnection(connection);
        this.connection = connection;
    }

    /**
     * Alternative UssChangeTag constructor with ZoweRequest object. This is mainly used for internal code
     * unit testing with mockito, and it is not recommended to be used by the larger community.
     *
     * @param connection connection information, see ZosConnection object
     * @param request    any compatible ZoweRequest Interface object
     * @throws Exception processing error
     * @author James Kostrewski
     */
    public UssChangeTag(ZosConnection connection, ZoweRequest request) throws Exception {
        ValidateUtils.checkConnection(connection);
        ValidateUtils.checkNullParameter(request == null, "request is null");
        this.connection = connection;
        if (!(request instanceof JsonPutRequest)) {
            throw new Exception("request type must be PUT");
        }
        this.request = request;
    }

    /**
     * Change tag of a USS object
     *
     * @param targetPath path to the target object
     * @param action the file tag action
     */
    public Response change(String targetPath, ChangeTagAction action) throws Exception {
        return change(targetPath, new ChangeTagParams.Builder().action(action).build());
    }

    /**
     * Change tag of a USS object
     *
     * @param targetPath path to the target object
     * @param params parameters for the change tag request
     * @return response from the server
     * @throws Exception processing error
     */
    public Response change(String targetPath, ChangeTagParams params) throws Exception {
        ValidateUtils.checkNullParameter(targetPath == null, "targetPath is null");
        ValidateUtils.checkIllegalParameter(targetPath.trim().isEmpty(), "targetPath not specified");
        ValidateUtils.checkNullParameter(params == null, "params is null");

        final String url = "https://" + connection.getHost() + ":" + connection.getZosmfPort() +
                ZosFilesConstants.RESOURCE + ZosFilesConstants.RES_USS_FILES + FileUtils.validatePath(targetPath);

        final Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("request", "chtag");
        jsonMap.put("action", params.getAction().orElseThrow(() -> new Exception("action not specified")));
        params.getType().ifPresent(type -> jsonMap.put("type", type));
        params.getCodeset().ifPresent(codeset -> jsonMap.put("codeset", codeset));
        if (!params.isRecursive()) {
            jsonMap.put("recursive", "false");
        }

        if (request == null) {
            request = ZoweRequestFactory.buildRequest(connection, ZoweRequestType.PUT_JSON);
        }
        request.setUrl(url);
        request.setBody(new JSONObject(jsonMap).toString());

        return RestUtils.getResponse(request);
    }
    
}
