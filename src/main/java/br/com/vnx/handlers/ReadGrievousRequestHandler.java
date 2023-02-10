package br.com.vnx.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.vnx.model.dtos.GrievousDTO;
import br.com.vnx.model.entities.Grievous;
import br.com.vnx.repositories.GrievousRepository;

public class ReadGrievousRequestHandler implements RequestHandler<GrievousDTO, String> {

	@Override
	public String handleRequest(GrievousDTO grievousDTO, Context context) {
		Grievous grievous = new GrievousRepository().findByKey(grievousDTO.getName());
		return grievous.toString();
	}
}
