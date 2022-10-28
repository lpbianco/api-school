package br.com.schoolapi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.schoolapi.model.School;
import br.com.schoolapi.model.Schools;
import br.com.schoolapi.repository.SchoolRepository;
import br.com.schoolapi.util.util;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	public School saveSchool(School school) {
		school.setCreatedAt(util.stringDate(new Date()));
		school.getLevels().forEach(f -> f.setCreatedAt(util.stringDate(new Date())));
		return schoolRepository.save(school);
	}

	public School findSchoolForId(int id) {
		return schoolRepository.findById(id).get();
	}

	public School updateSchoolForId(int id, School school) {
		school.setId(id);
		school.setUpdatedAt(util.stringDate(new Date()));
		school.getLevels().forEach(f -> f.setUpdatedAt(util.stringDate(new Date())));
		return schoolRepository.save(school);
	}

	public Schools findAllSchools(int page, int pageSize, String search) {
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		Schools schools = null;
		schools = this.filterSchool(search, pageRequest, schools);
		return schools;
	}

	private Schools filterSchool(String search, PageRequest pageRequest, Schools schools) {
		if (search == null || search.equals("")) {
			schools = new Schools(schoolRepository.findAll(pageRequest).getContent());
		} else {
			if (search.contains("name:")) {
				schools = new Schools(schoolRepository.searchwithName(search.split(":")[1], pageRequest).getContent());
			}
			if (search.contains("name~")) {
				schools = new Schools(schoolRepository.searchLikeName(search.split("~")[1], pageRequest).getContent());
			}
			if (search.contains("name!")) {
				schools = new Schools(schoolRepository.searchDifName(search.split("!")[1], pageRequest).getContent());
			}
			if (search.contains("address:")) {
				schools = new Schools(
						schoolRepository.searchwithAddress(search.split(":")[1], pageRequest).getContent());
			}
			if (search.contains("address~")) {
				schools = new Schools(
						schoolRepository.searchLikeAddress(search.split("~")[1], pageRequest).getContent());
			}
			if (search.contains("address!")) {
				schools = new Schools(
						schoolRepository.searchDifAddress(search.split("!")[1], pageRequest).getContent());
			}
			if (search.contains("status:")) {
				schools = new Schools(
						schoolRepository.searchwithstatus(search.split(":")[1], pageRequest).getContent());
			}
			if (search.contains("status~")) {
				schools = new Schools(
						schoolRepository.searchLikeStatus(search.split("~")[1], pageRequest).getContent());
			}
			if (search.contains("status!")) {
				schools = new Schools(schoolRepository.searchDifstatus(search.split("!")[1], pageRequest).getContent());
			}
			if (search.contains("inep:")) {
				schools = new Schools(schoolRepository
						.searchwithInep(Integer.valueOf(search.split(":")[1]), pageRequest).getContent());
			}
			if (search.contains("inep>")) {
				schools = new Schools(schoolRepository
						.searchBiggerInep(Integer.valueOf(search.split(">")[1]), pageRequest).getContent());
			}
			if (search.contains("inep<")) {
				schools = new Schools(schoolRepository
						.searchSmallerInep(Integer.valueOf(search.split("<")[1]), pageRequest).getContent());
			}
			if (search.contains("inep!")) {
				schools = new Schools(schoolRepository.searchDifInep(Integer.valueOf(search.split("!")[1]), pageRequest)
						.getContent());
			}
		}
		return schools;
	}

	public void deleteSchoolFroId(int id, boolean softDelete) {
		if (softDelete) {
			this.deleteSchool(id);
		} else {
			this.deactivateSchool(id);
		}
	}

	private void deleteSchool(int id) {
		schoolRepository.deleteById(id);
	}

	private void deactivateSchool(int id) {
		School school = this.findSchoolForId(id);
		school.setDeletedAt(util.stringDate(new Date()));
		school.getLevels().forEach(f -> f.setDeletedAt(util.stringDate(new Date())));
		schoolRepository.save(school);
	}
}
