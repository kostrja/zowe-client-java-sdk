/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package zostso.zosmf;

import java.util.Optional;

/**
 * TSO Response interface for one of TSO/E messages
 *
 * @author Frank Giordano
 * @version 1.0
 */
public class TsoResponseMessage {

    /**
     * JSON version for message format
     */
    private Optional<String> version;

    /**
     * Description of the data type
     */
    private Optional<String> data;

    /**
     * TsoResponseMessage constructor
     *
     * @author Frank Giordano
     */
    public TsoResponseMessage() {
    }

    /**
     * TsoResponseMessage constructor
     *
     * @param version JSON version for message format
     * @param data    description of the data type
     * @author Frank Giordano
     */
    public TsoResponseMessage(Optional<String> version, Optional<String> data) {
        this.version = version;
        this.data = data;
    }

    /**
     * Retrieve version specified
     *
     * @return version value
     * @author Frank Giordano
     */
    public Optional<String> getVersion() {
        return version;
    }

    /**
     * Assign version value
     *
     * @param version JSON version for message format
     * @author Frank Giordano
     */
    public void setVersion(Optional<String> version) {
        this.version = version;
    }

    /**
     * Retrieve data specified
     *
     * @return data value
     * @author Frank Giordano
     */
    public Optional<String> getData() {
        return data;
    }

    /**
     * Assign version value
     *
     * @param data description os the data type
     * @author Frank Giordano
     */
    public void setData(Optional<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TsoResponseMessage{" +
                "version=" + version +
                ", data=" + data +
                '}';
    }

}
