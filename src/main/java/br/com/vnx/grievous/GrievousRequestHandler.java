package br.com.vnx.grievous;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.vnx.model.Grievous;
import br.com.vnx.repository.GrievousRepository;

public class GrievousRequestHandler implements RequestHandler<Grievous, String> {

	public String handleRequest(Grievous grievousDTO, Context context) {
		Grievous grievous = new GrievousRepository().findByName("general");
		return grievous.toString();
	}
}
