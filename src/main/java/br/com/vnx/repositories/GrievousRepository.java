package br.com.vnx.repositories;

import br.com.vnx.dynamodb.DynamoDBConfig;
import br.com.vnx.model.entities.Grievous;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.utils.Logger;

public class GrievousRepository {
	private final Logger LOG = Logger.loggerFor(this.getClass());
	
	private DynamoDbEnhancedClient ddb;
	private DynamoDbTable<Grievous> dynamoDbTable;

	public GrievousRepository() {
		ddb = DynamoDBConfig.getDynamoDbEnhancedClient();
		dynamoDbTable = ddb.table("Grievous", TableSchema.fromBean(Grievous.class));
	}

	/**
	 * Find item by key.
	 * @param key String
	 * @return  grievous Grievous
	 */
	public Grievous findByKey(String key) {
		Grievous grievous = null;

        try {
            grievous = dynamoDbTable.getItem(Key.builder().partitionValue(key).build());
        } catch (DynamoDbException e) {
        	LOG.error(() -> "Erro ao buscar item na base.", e);
            System.err.println(e.getMessage());
            System.exit(1);
        }
        
        return grievous;
	}
	
	/**
	 * Save item.
	 * @param grievous Grievous
	 */
	public void save(Grievous grievous) {
		try {
			dynamoDbTable.putItem(grievous);
        } catch (DynamoDbException e) {
        	LOG.error(() -> "Erro ao salvar item na base.", e);
            System.err.println(e.getMessage());
            System.exit(1);
        }
	}
	
	/**
	 * Update item.
	 * @param grievous Grievous
	 */
	public void update(Grievous grievous) {
		try {
			dynamoDbTable.updateItem(grievous);
        } catch (DynamoDbException e) {
        	LOG.error(() -> "Erro ao atualizar item na base.", e);
            System.err.println(e.getMessage());
            System.exit(1);
        }
	}
	
	/**
	 * Delete item.
	 * @param key String
	 */
	public void delete(String key) {
		try {
			dynamoDbTable.deleteItem(Key.builder().partitionValue(key).build());
        } catch (DynamoDbException e) {
        	LOG.error(() -> "Erro ao excluir item na base.", e);
            System.err.println(e.getMessage());
            System.exit(1);
        }
	}
}
