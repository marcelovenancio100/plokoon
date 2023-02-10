package br.com.vnx.dynamodb;

import java.net.URI;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
	
public class DynamoDBConfig {
	private static DynamoDbClient dynamoDbClient;
	private static DynamoDbEnhancedClient dynamoDbEnhancedClient;

	private static DynamoDbClient createDynamoDbClient() {
		DynamoDbClient client = DynamoDbClient.builder()
				.endpointOverride(URI.create("http://host.docker.internal:8000"))
				.region(Region.US_WEST_2)
				.credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("local", "local")))
				.build();

		return client;
	}
	
	public static DynamoDbClient getDynamoDbClient() {
		if (dynamoDbClient == null) {
			dynamoDbClient = createDynamoDbClient();
		}
		return dynamoDbClient;
	}

	public static DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
		if (dynamoDbEnhancedClient == null) {
			dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(getDynamoDbClient()).build();
		}
		return dynamoDbEnhancedClient;
	}
}
