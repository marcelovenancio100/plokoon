package br.com.vnx.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.vnx.context.ContextResponseCustom;
import br.com.vnx.model.dtos.GrievousDTO;
import br.com.vnx.model.entities.Grievous;
import br.com.vnx.model.wrappers.GrievousWrapper;
import br.com.vnx.repositories.GrievousRepository;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.utils.Logger;

public class UpdateGrievousRequestHandler implements RequestHandler<GrievousDTO, ContextResponseCustom> {

	private final Logger LOG = Logger.loggerFor(this.getClass());
	
	@Override
	public ContextResponseCustom handleRequest(GrievousDTO grievousDTO, Context context) {
		try {
			Grievous grievous = new GrievousWrapper().convert2(grievousDTO);
			new GrievousRepository().update(grievous);
			return ContextResponseCustom.builder().setStatusCode(200).setObjBody(grievous).build();
		} catch (SdkException e) {
			LOG.error(() -> "Erro ao atualizar registro.", e);
			return ContextResponseCustom.builder().setStatusCode(500).setObjBody(grievousDTO).build();
		}
	}
}
