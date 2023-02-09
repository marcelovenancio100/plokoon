package br.com.vnx.grievous;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.vnx.dao.GrievousDAO;
import br.com.vnx.model.Grievous;

public class GrievousRequestHandler implements RequestHandler<Grievous, String> {

	public String handleRequest(Grievous grievousDTO, Context context) {
		GrievousDAO grievousDAO = new GrievousDAO();
		Grievous grievous = grievousDAO.findByName("general");
		return grievous.getEmail();
	}
}
