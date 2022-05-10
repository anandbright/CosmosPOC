package org.example.azure.documentdb;

import com.microsoft.azure.documentdb.DocumentClientException;

public class CosmosTestingMain {

    public static void main(String[] args) throws DocumentClientException {
//        CollectionCrudOperations collectionCrudOperations = new CollectionCrudOperations();
//        collectionCrudOperations.initialize();
//        System.out.println("Collection has been initialized");
//        collectionCrudOperations.createSinglePartitionCollectiob();
//        collectionCrudOperations.createCustomMultiPartitionCollection();
//        System.out.println("Multi partition collection has been created");
//        collectionCrudOperations.deleteDatabase();


//        DatabaseCrudOperations dbOperations = new DatabaseCrudOperations();
//        dbOperations.initialize();
//        dbOperations.databaseRead();

        DocumentCrudOperations documentCrudOperations = new DocumentCrudOperations();
        documentCrudOperations.initialize();
//        documentCrudOperations.createDocument();
//        documentCrudOperations.readDocument();
//        documentCrudOperations.createDocWithProgDef();
        documentCrudOperations.readDocWithProgDef();

    }
}
