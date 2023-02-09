package br.com.vnx.dao;

import br.com.vnx.dynamodb.DynamoDBConfig;
import br.com.vnx.model.Grievous;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class GrievousDAO {
	private DynamoDbEnhancedClient ddb;

	public GrievousDAO() {
		ddb = DynamoDBConfig.getDynamoDbEnhancedClient();
	}

	public Grievous findByName(String name) {
		Grievous grievous = null;
		
		try {
            DynamoDbTable<Grievous> table = ddb.table("Grievous", TableSchema.fromBean(Grievous.class));
            Key key = Key.builder().partitionValue(name).build();
            grievous = table.getItem((GetItemEnhancedRequest.Builder requestBuilder) -> requestBuilder.key(key));
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return grievous;
	}
}
