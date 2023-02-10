package br.com.vnx.model.wrappers;

import br.com.vnx.model.dtos.GrievousDTO;
import br.com.vnx.model.entities.Grievous;

public class GrievousWrapper {

	public Grievous convert2(GrievousDTO grievousDTO) {
		Grievous grievous = new Grievous();
		grievous.setName(grievousDTO.getName());
		grievous.setEmail(grievousDTO.getEmail());
		grievous.setBatles(grievousDTO.getBatles());
		grievous.setVictories(grievousDTO.getVictories());
		grievous.setDefeats(grievousDTO.getDefeats());
		return grievous;
	}
	
	public GrievousDTO convert2(Grievous grievous) {
		GrievousDTO grievousDTO = new GrievousDTO();
		grievousDTO.setName(grievous.getName());
		grievousDTO.setEmail(grievous.getEmail());
		grievousDTO.setBatles(grievous.getBatles());
		grievousDTO.setVictories(grievous.getVictories());
		grievousDTO.setDefeats(grievous.getDefeats());
		return grievousDTO;
	}
}
