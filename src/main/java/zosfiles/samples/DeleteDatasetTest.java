/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package zosfiles.samples;

import core.ZOSConnection;
import zosfiles.ZosDsn;

public class DeleteDatasetTest {

    public static void main(String[] args) throws Exception {
        String hostName = "XXX";
        String port = "XXX";
        String userName = "XXX";
        String password = "XXX";
        String datasetMember = "XXX";

        ZOSConnection connection = new ZOSConnection(hostName, port, userName, password);

        new ZosDsn(connection).deleteDsn(datasetMember);
    }

}
