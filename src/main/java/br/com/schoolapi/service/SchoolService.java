package br.com.schoolapi.service;

import br.com.schoolapi.model.School;
import br.com.schoolapi.model.Schools;

public interface SchoolService {

	 School saveSchool(School school);
	 
	 School findSchoolForId(int id);
	 
	 School updateSchoolForId(int id, School school);
	 
	 void deleteSchoolFroId(int id, boolean softDelete);
	 
	 Schools findAllSchools(int page, int pageSize, String search); 
	 
}
