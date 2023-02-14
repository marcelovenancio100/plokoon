package br.com.vnx.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.vnx.context.ContextResponseCustom;
import br.com.vnx.model.dtos.GrievousDTO;
import br.com.vnx.model.entities.Grievous;
import br.com.vnx.model.wrappers.GrievousWrapper;
import br.com.vnx.repositories.GrievousRepository;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.utils.Logger;

public class CreateGrievousRequestHandler implements RequestHandler<GrievousDTO, ContextResponseCustom> {
	
	private final Logger logger = Logger.loggerFor(this.getClass());
	
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Override
	public ContextResponseCustom handleRequest(GrievousDTO grievousDTO, Context context) {
		logger.info(() -> "DTO: " + gson.toJson(grievousDTO));
		logger.info(() -> "DTO TYPE: " + gson.toJson(grievousDTO.getClass().toString()));
		logger.info(() -> "CONTEXT: " + gson.toJson(context));
		
		ContextResponseCustom contextResponseCustom;
		
		try {
			Grievous grievous = new GrievousWrapper().convert2(grievousDTO);
			new GrievousRepository().save(grievous);
			contextResponseCustom = ContextResponseCustom.builder().setStatusCode(201).setBody(grievous).build();
		} catch (SdkException e) {
			logger.error(() -> "ERRO AO CRIAR REGISTRO: ", e);
			contextResponseCustom = ContextResponseCustom.builder().setStatusCode(500).setBody(grievousDTO).build();
		}
		
		String response = gson.toJson(contextResponseCustom);
		logger.info(() -> "RESPONSE: " + response);
		return contextResponseCustom;
	}
}
