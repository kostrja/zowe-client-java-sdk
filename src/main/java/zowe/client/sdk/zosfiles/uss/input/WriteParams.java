/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */
package zowe.client.sdk.zosfiles.uss.input;

import java.util.Optional;

/**
 * Parameter container class for Unix System Services (USS) write to file object
 * <p>
 * <a href="https://www.ibm.com/docs/en/zos/2.4.0?topic=interface-write-data-zos-unix-file">z/OSMF REST API</a>
 *
 * @author Frank Giordano
 * @version 2.0
 */
public class WriteParams {

    /**
     * Text content for file write
     */
    private final Optional<String> textContent;

    /**
     * Binary byte array content for file write
     */
    private final Optional<byte[]> binaryContent;

    /**
     * Can be used to specify an alternate EBCDIC code page. The default code page is IBM-1047.
     */
    private final Optional<String> fileEncoding;

    /**
     * Can be used to control whether each input text line is terminated with a carriage return line feed (CRLF),
     * rather than a line feed (LF), which is the default. Set to true to turn off default.
     */
    private final boolean crlf;

    /**
     * If true perform binary write instead of text.
     */
    private final boolean binary;

    /**
     * WriteParams constructor
     *
     * @params WriteParams.Builder builder
     * @author Frank Giordano
     */
    public WriteParams(WriteParams.Builder builder) {
        this.textContent = Optional.ofNullable(builder.textContent);
        this.binaryContent = Optional.ofNullable(builder.binaryContent);
        this.fileEncoding = Optional.ofNullable(builder.fileEncoding);
        this.crlf = builder.crlf;
        this.binary = builder.binary;
    }

    /**
     * Retrieve textContent value
     *
     * @return textContent value
     * @author Frank Giordano
     */
    public Optional<String> getTextContent() {
        return textContent;
    }

    /**
     * Retrieve binaryContent value
     *
     * @return binaryContent value
     * @author Frank Giordano
     */
    public Optional<byte[]> getBinaryContent() {
        return binaryContent;
    }

    /**
     * Retrieve fileEncoding value
     *
     * @return fileEncoding value
     * @author Frank Giordano
     */
    public Optional<String> getFileEncoding() {
        return fileEncoding;
    }

    /**
     * Retrieve crlf value
     *
     * @return crlf value
     * @author Frank Giordano
     */
    public boolean isCrlf() {
        return crlf;
    }

    /**
     * Retrieve binary value
     *
     * @return binary value
     * @author Frank Giordano
     */
    public boolean isBinary() {
        return binary;
    }

    @Override
    public String toString() {
        return "WriteParams{" +
                "textContent=" + textContent +
                ", binaryContent=" + binaryContent +
                ", fileEncoding=" + fileEncoding +
                ", crlf=" + crlf +
                ", binary=" + binary +
                '}';
    }

    public static class Builder {

        private String textContent;
        private byte[] binaryContent;
        private String fileEncoding;
        private boolean crlf = false;
        private boolean binary = false;

        public WriteParams build() {
            return new WriteParams(this);
        }

        public WriteParams.Builder textContent(String textContent) {
            this.textContent = textContent;
            return this;
        }

        public WriteParams.Builder binaryContent(byte[] binaryContent) {
            this.binaryContent = binaryContent;
            return this;
        }

        public WriteParams.Builder fileEncoding(String fileEncoding) {
            this.fileEncoding = fileEncoding;
            return this;
        }

        public WriteParams.Builder crlf(boolean crlf) {
            this.crlf = crlf;
            return this;
        }

        public WriteParams.Builder binary(boolean binary) {
            this.binary = binary;
            return this;
        }

    }

}
