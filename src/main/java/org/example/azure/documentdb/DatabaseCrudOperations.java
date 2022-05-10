package org.example.azure.documentdb;

import com.fasterxml.jackson.core.JsonParseException;
import com.microsoft.azure.documentdb.*;

public class DatabaseCrudOperations {
    private final String databaseID = "PrinterLocation";

    private RequestOptions options = new RequestOptions();

    private DocumentClient client;

    public void initialize() throws DocumentClientException {

        client = new DocumentClient(AccountCredentials.HOST, AccountCredentials.MASTER_KEY, null, null);
    }

    public void dbDefinitionAndOptionInitiaze() {

        Database databaseDefinition = new Database();
        databaseDefinition.setId(databaseID);
        RequestOptions options = new RequestOptions();
    }

    public void databaseRead() throws DocumentClientException {

        Database databaseDefinition = new Database();
        databaseDefinition.setId(databaseID);
        String databaseLink = String.format("/dbs/%s", databaseID);

        ResourceResponse<Database> response = client.readDatabase(databaseLink, options);
        Database readDB = response.getResource();

        System.out.println(readDB);
    }

    private void deleteDatabase() {
        try {
            client.deleteDatabase(String.format("/dbs/%s", databaseID), null);
        } catch (Exception e) {
        }
    }
}
