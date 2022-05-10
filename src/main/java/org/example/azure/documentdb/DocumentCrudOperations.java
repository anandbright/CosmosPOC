package org.example.azure.documentdb;

import com.microsoft.azure.documentdb.*;

public class DocumentCrudOperations {
    private final String databaseId = "PrinterLocation";
    private final String collectionId = "airportPrinters";
    private final String partitionKeyFieldName = "airportCode";
    private final String partitionKeyPath = "/" + partitionKeyFieldName;

    private DocumentClient client;

    public void initialize() throws DocumentClientException {

        client = new DocumentClient(AccountCredentials.HOST, AccountCredentials.MASTER_KEY, null, null);
    }

    public void createDocument() throws DocumentClientException {
        String collectionLink = String.format("/dbs/%s/colls/%s", databaseId, collectionId);
        Document documentDef = new Document(
          "{" +
                  "   \"id\" : \"PRINTER105\"," +
                  "   \"airportCode\" : \"SEA\"," +
                  "   \"gateNo\" : D20" +
                  "}"
        );

        ResourceResponse<Document> response = client.createDocument(collectionLink, documentDef, null, false);
        System.out.println(response + "Document created");
//        response.getRequestCharge();
    }

    public void readDocument() throws DocumentClientException {
        String documentLink = String.format("/dbs/%s/colls/%s/docs/%s", databaseId, collectionId, "PRINTER101");

        RequestOptions options = new RequestOptions();
        options.setPartitionKey(new PartitionKey("PHX"));

        ResourceResponse<Document> response = client.readDocument(documentLink, options);
        Document readDocument = response.getResource();

        System.out.println(client.readDocument(documentLink, options));

        System.out.println("The above document is read");
    }

    public void createDocWithProgDef() throws DocumentClientException {
        String collectionLink = String.format("/dbs/%s/colls/%s", databaseId, collectionId);
        Document docDef = new Document();

        docDef.setId("PRINTER108");
        docDef.set("airportCode", "BOM");
        docDef.set("gateNo", "A14");

        client.createDocument(collectionLink, docDef, null, false);
    }

    public void readDocWithProgDef() throws DocumentClientException {
        String collectionLink = String.format("/dbs/%s/colls/%s/docs/%s", databaseId, collectionId, "PRINTER108");
        RequestOptions options = new RequestOptions();
        options.setPartitionKey(new PartitionKey("BOM"));

        ResourceResponse<Document> response = client.readDocument(collectionLink, options);
        Document readDocument = response.getResource();

        System.out.println(readDocument.getId());
        System.out.println(readDocument.getString("airportCode"));
        System.out.println(readDocument.getString("gateNo"));
    }
}
