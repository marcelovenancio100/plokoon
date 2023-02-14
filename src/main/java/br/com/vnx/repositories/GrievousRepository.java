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
	private final Logger logger = Logger.loggerFor(this.getClass());
	
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
        	logger.info(() -> "BUSCANDO ITEM ... ");
            grievous = dynamoDbTable.getItem(Key.builder().partitionValue(key).build());
        } catch (DynamoDbException e) {
        	logger.error(() -> "ERRO AO BUSCAR ITEM NA BASE: ", e);
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
			logger.info(() -> "SALVANDO ITEM ... ");
			dynamoDbTable.putItem(grievous);
        } catch (DynamoDbException e) {
        	logger.error(() -> "ERRO AO SALVAR ITEM NA BASE: ", e);
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
			logger.info(() -> "ATUALIZANDO ITEM ... ");
			dynamoDbTable.updateItem(grievous);
        } catch (DynamoDbException e) {
        	logger.error(() -> "ERRO AO ATUALIZAR ITEM NA BASE: ", e);
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
			logger.info(() -> "EXCLUINDO ITEM ... ");
			dynamoDbTable.deleteItem(Key.builder().partitionValue(key).build());
        } catch (DynamoDbException e) {
        	logger.error(() -> "ERRO AO EXCLUIR ITEM NA BASE: ", e);
            System.err.println(e.getMessage());
            System.exit(1);
        }
	}
}
