package org.example.azure.documentdb;

import java.util.ArrayList;
import java.util.Collection;

import com.microsoft.azure.documentdb.DataType;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.IncludedPath;
import com.microsoft.azure.documentdb.Index;
import com.microsoft.azure.documentdb.IndexingPolicy;
import com.microsoft.azure.documentdb.PartitionKeyDefinition;
import com.microsoft.azure.documentdb.RequestOptions;

public class CollectionCrudOperations {
    private final String databaseID = "PrinterLocation";

    private  DocumentClient client;

    public void initialize() throws DocumentClientException {
        client = new DocumentClient(AccountCredentials.HOST, AccountCredentials.MASTER_KEY, null, null);
    }

    public void createSinglePartitionCollectiob() throws DocumentClientException {

        String databaseLink = String.format("/dbs/%s", databaseID);
        String collectionID = "printerCollection";

        DocumentCollection documentCollection = new DocumentCollection();
        documentCollection.setId(collectionID);

        client.createCollection(databaseLink, documentCollection, null);
    }

    public void createCustomMultiPartitionCollection() throws DocumentClientException {
        String databaseLink = String.format("/dbs/%s", databaseID);
        String collectionID = "airportPrinters";

        DocumentCollection documentCollection = new DocumentCollection();
        documentCollection.setId(collectionID);

        // set airportcode as [artition key path
        PartitionKeyDefinition partitionKeyDefinition = new PartitionKeyDefinition();
        Collection<String> paths = new ArrayList<String>();
        paths.add("/airportCode");
        partitionKeyDefinition.setPaths(paths);
        documentCollection.setPartitionKey(partitionKeyDefinition);

        // Throughput set as 1000
        RequestOptions options = new RequestOptions();
        options.setOfferThroughput(400);

        documentCollection.setDefaultTimeToLive(100);

        // create collection
        client.createCollection(databaseLink, documentCollection, options);

        //create a new document in collection
        String collectionLink = String.format("/dbs/%s/colls/%s", databaseID, collectionID);
        Document documentDefinition = new Document(
                "{" +
                        "   \"id\": \"PRINTER105\"," +
                        "   \"airportCode\" : \"PHX\"" +
                        "   \"gateNo\" : \"C9\"" +
                        "} ") ;

        client.createDocument(collectionLink, documentDefinition, options, false);
    }

//    public void deleteDocument() {
//        client.deleteDocument(collectionLink, options);
//    }

    public void deleteDatabase() {
        try {
            client.deleteDatabase(String.format("/dbs/%s", databaseID), null);
        } catch (Exception e) {
        }
    }
}
