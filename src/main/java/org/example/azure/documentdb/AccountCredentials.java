package org.example.azure.documentdb;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;

public final class AccountCredentials {
        // Replace MASTER_KEY and HOST with values from your DocumentDB account.
        // The default values are credentials of the local emulator, which are not used in any production environment.
        // <!--[SuppressMessage("Microsoft.Security", "CS002:SecretInNextLine")]-->
        public static String MASTER_KEY =
                System.getProperty("ACCOUNT_KEY", StringUtils.defaultString(Strings.emptyToNull(System.getenv().get("ACCOUNT_KEY")),
                        " "));

        public static String HOST =
                System.getProperty("ACCOUNT_HOST",
                        StringUtils.defaultString(
                                Strings.emptyToNull(System.getenv().get("ACCOUNT_HOST")),
                                "https://ct-s-zeaus-boarding-db.documents.azure.com:443/"));

        static {
                System.out.println("host is " + HOST);
        }
}