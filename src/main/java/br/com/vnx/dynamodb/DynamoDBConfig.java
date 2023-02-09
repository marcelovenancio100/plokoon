package br.com.vnx.dynamodb;

import java.net.URI;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDBConfig {
	private static DynamoDbClient dynamoDbClient;

	private static DynamoDbClient getDynamoDbClient() {
//		SDK V1
//		BasicAWSCredentials credentials = new BasicAWSCredentials("local", "local");
//		AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(credentials);
//
//		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//				.withCredentials(awsCredentialsProvider)
//				.withEndpointConfiguration(
//						new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
//				.build();

//		SDK V2
		DynamoDbClient client = DynamoDbClient.builder()
			    .endpointOverride(URI.create("http://localhost:8000"))
			    .region(Region.US_WEST_2)
			    .credentialsProvider(StaticCredentialsProvider.create(
			    AwsBasicCredentials.create("local", "local")))
			    .build();

		return client;
	}
	
	public static DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
		if (dynamoDbClient == null) {
			dynamoDbClient = getDynamoDbClient();
		}
		return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();
	}

//	SDK V1
//	public static DynamoDBMapper dynamoDBMapper() {
//		if (amazonDynamoDB == null) {
//			amazonDynamoDB = getAmazonDynamoDB();
//		}
//		return new DynamoDBMapper(amazonDynamoDB);
//	}
}
