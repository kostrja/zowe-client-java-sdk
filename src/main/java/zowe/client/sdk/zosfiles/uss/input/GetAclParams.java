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
 * Parameter container class for Unix System Services (USS) getfacl operation
 *
 * @author James Kostrewski
 * @version 2.0
 */
public class GetAclParams {

    /**
     * The default is 'access', displays the access ACL entries for a file or directory (getfacl -a).
     * 'dir' displays the directory default ACL entries (getfacl -d).
     * If the target is not a directory, a warning is issued.
     */
    private final Optional<String> type;

    /**
     * The user ID or UID (as a JSON string),
     * displays only the ACL entries for the specified types of access control lists (getfacl -a, -d, -f)
     * which affects the specified user's access (getfacl -e user).
     */
    private final Optional<String> user;

    /**
     * The default is 'false'.
     * When true, displays each ACL entry, using commas to separate the ACL entries instead of newlines.
     */
    private final boolean useCommas;

    /**
     * The default is 'false'.
     * When true, the comment header (the first three lines of each file's output) is not to be displayed (getfacl -m)
     */
    private final boolean suppressHeader;

    /**
     * The default is 'false'.
     * When true, displays only the extended ACL entries. Does not display the base ACL entries (getfacl -o).
     */
    private final boolean suppressBaseAcl;

    /**
     * GetAclParams constructor
     *
     * @param builder GetAclParams.Builder builder
     * @author James Kostrewski
     */
    public GetAclParams(final GetAclParams.Builder builder) {
        this.type = Optional.ofNullable(builder.type);
        this.user = Optional.ofNullable(builder.user);
        this.useCommas = builder.useCommas;
        this.suppressHeader = builder.suppressHeader;
        this.suppressBaseAcl = builder.suppressBaseAcl;
    }

    /**
     * Retrieve type value
     *
     * @return type value
     */
    public Optional<String> getType() {
        return type;
    }

    /**
     * Retrieve user value
     *
     * @return user value
     */
    public Optional<String> getUser() {
        return user;
    }

    /**
     * Retrieve useCommas value
     *
     * @return useCommas value
     */
    public boolean getUseCommas() {
        return useCommas;
    }

    /**
     * Retrieve suppressHeader value
     *
     * @return suppressHeader value
     */
    public boolean getSuppressHeader() {
        return suppressHeader;
    }

    /**
     * Retrieve suppressBaseAcl value
     *
     * @return suppressBaseAcl value
     */
    public boolean getSuppressBaseAcl() {
        return suppressBaseAcl;
    }

    /**
     * Return string value representing GetAclParams object
     *
     * @return string representation of GetAclParams
     */
    @Override
    public String toString() {
        return "GetAclParams{" +
                "type=" + type +
                ", user=" + user +
                ", useCommas=" + useCommas +
                ", suppressHeader=" + suppressHeader +
                ", suppressBaseAcl=" + suppressBaseAcl +
                '}';
    }

    /**
     * Builder class for GetAclParams
     */
    public static class Builder {

        /**
         * The default is 'access', displays the access ACL entries for a file or directory (getfacl -a).
         * 'dir' displays the directory default ACL entries (getfacl -d).
         * If the target is not a directory, a warning is issued.
         */
        private String type;

        /**
         * The user ID or UID (as a JSON string),
         * displays only the ACL entries for the specified types of access control lists (getfacl -a, -d, -f)
         * which affects the specified user's access (getfacl -e user).
         */
        private String user;

        /**
         * The default is 'false'.
         * When true, displays each ACL entry, using commas to separate the ACL entries instead of newlines.
         */
        private boolean useCommas = false;

        /**
         * The default is 'false'.
         * When true, the comment header (the first three lines of each file's output) is not to be displayed
         * (getfacl -m)
         */
        private boolean suppressHeader = false;

        /**
         * The default is 'false'.
         * When true, displays only the extended ACL entries. Does not display the base ACL entries (getfacl -o).
         */
        private boolean suppressBaseAcl = false;

        /**
         * Builder constructor
         */
        public Builder() {
        }

        /**
         * Set type value
         *
         * @param type String value representing type
         * @return Builder this object
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Set user value
         *
         * @param user String value representing user
         * @return Builder this object
         */
        public Builder user(String user) {
            this.user = user;
            return this;
        }

        /**
         * Set useCommas value
         *
         * @param useCommas boolean value representing useCommas
         * @return Builder this object
         */
        public Builder useCommas(boolean useCommas) {
            this.useCommas = useCommas;
            return this;
        }

        /**
         * Set suppressHeader value
         *
         * @param suppressHeader boolean value representing suppressHeader
         * @return Builder this object
         */
        public Builder suppressHeader(boolean suppressHeader) {
            this.suppressHeader = suppressHeader;
            return this;
        }

        /**
         * Set suppressBaseAcl value
         *
         * @param suppressBaseAcl boolean value representing suppressBaseAcl
         * @return Builder this object
         */
        public Builder suppressBaseAcl(boolean suppressBaseAcl) {
            this.suppressBaseAcl = suppressBaseAcl;
            return this;
        }

        /**
         * Return GetAclParams object based on Builder this object
         *
         * @return GetAclParams object
         */
        public GetAclParams build() {
            return new GetAclParams(this);
        }

    }

}
