package com.relecotech.androidsparsh_tiptop.utils;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

/**
 * Created by Relecotech on 01-02-2018.
 */

public class AzureConfiguration {

    public static String DEVELOPER_KEY = "AIzaSyDZEpCIRqY3mrXcZCp1y74ifi9upWssi0U";

    public static String SenderId = "644969156269";
    public static String HubName = "NewTipTop2018";
    public static String HubListenConnectionString = "Endpoint=sb://newtiptop2018ns.servicebus.windows.net/;SharedAccessKeyName=DefaultFullSharedAccessSignature;SharedAccessKey=fZDiazzofk+vhYgiIfpNlKPobh0VNmmJGJgNK/WRLKc=";

    public static String containerName = "newtiptop2018ontainer";
    public static String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=newtiptop2018;AccountKey=BWHfmJlGZLjHgthMrmzk9Fqv2vjKIrA1Y58uotFijMStkdZkAj6qRAr9CLbY3fPDrBVdDbnsuDDzWUe4Lbx8gg==;EndpointSuffix=core.windows.net";
    public static String Storage_url = "https://newtiptop2018.blob.core.windows.net/newtiptop2018ontainer/";


    public static CloudBlobContainer getContainer() throws Exception {
        // Retrieve storage account from connection-string.

        CloudStorageAccount storageAccount = CloudStorageAccount.parse(AzureConfiguration.storageConnectionString);

        // Create the blob client.
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
        // Get a reference to a container.
        // The container name must be lower case
        CloudBlobContainer container = blobClient.getContainerReference(AzureConfiguration.containerName);

        return container;
    }
}
