package br.com.schoolapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolapi.model.ErrorResponse;
import br.com.schoolapi.model.School;
import br.com.schoolapi.model.Schools;
import br.com.schoolapi.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("${server.api.version}/school")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	@Operation(summary = "${school.create}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${school.create.info}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = School.class)) }),
			@ApiResponse(responseCode = "500", description = "${school.error}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<School> postSchool(@Valid @RequestBody School school) {
		School schoolSaved = schoolService.saveSchool(school);
		return new ResponseEntity<School>(schoolSaved, HttpStatus.OK);
	}

	@Operation(summary = "${school.find.id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${school.find.id.info}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = School.class)) }),
			@ApiResponse(responseCode = "500", description = "${school.error}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<School> getSchoolById(
			@Parameter(description = "${school.find.id.parameter}") @PathVariable("id") int id) {
		School schoolSearch = schoolService.findSchoolForId(id);
		return new ResponseEntity<School>(schoolSearch, HttpStatus.OK);
	}

	@Operation(summary = "${school.findall.msg}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${school.findall.info}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Schools.class)) }),
			@ApiResponse(responseCode = "500", description = "${school.error}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Schools> getSchool(
			@Parameter(description = "${school.findall.parameter.page}") @RequestParam(required = true) int page,
			@Parameter(description = "${school.findall.parameter.pageSize}") @RequestParam(required = true) int pagesize,
			@Parameter(description = "${school.findall.parameter.search}:\r\n" + "                      \r\n"
					+ "  |  Operador        |                    Descrição                                                              | Exemplo                      |\r\n"
					+ "  | :--------------  |                   :----------:                                                            |    -------------------:      |\r\n"
					+ "  | **:** |   Operador utilizado para testar igualdade entre valores                                             | name:Instituto Xavier        |\r\n"
					+ "  | **>** |   Operador utilizado para testar superioridade entre valores                                         | inep>10000049                |\r\n"
					+ "  | **<** |   Operador utilizado para testar inferioridade entre valores                                         | inep<10000049                |\r\n"
					+ "  | **~** |   Operador utilizado para avaliar a presença de uma substring enquanto valor do atributo especificado| name~Dolores                 |\r\n"
					+ "  | **!** |   Operador utilizado para filtrar a partir da negação do valor especificado                          | inep!10000049                |") @RequestParam(required = false) String search) {
		Schools schools = schoolService.findAllSchools(page, pagesize, search);
		return new ResponseEntity<Schools>(schools, HttpStatus.OK);
	}

	@Operation(summary = "${school.update.id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "school.update.id.info", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = School.class)) }),
			@ApiResponse(responseCode = "500", description = "${school.error}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<School> putSchoolById(
			@Parameter(description = "${school.update.id.parameter}") @PathVariable("id") int id,
			@Valid @RequestBody School school) {
		School schoolUpdate = schoolService.updateSchoolForId(id, school);
		return new ResponseEntity<School>(schoolUpdate, HttpStatus.OK);
	}

	@Operation(summary = "${school.delete.id.info}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${school.delete.id}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = School.class)) }),
			@ApiResponse(responseCode = "500", description = "${school.error}", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<String> deleteSchoolById(
			@Parameter(description = "${school.delete.id.parameter.id}") @PathVariable("id") int id,
			@Parameter(description = "${school.delete.id.parameter.softDelete}") @RequestParam(required = false) Boolean softDelete) {
		schoolService.deleteSchoolFroId(id, (softDelete == null ? false : softDelete));
		return new ResponseEntity<String>("${school.delete.sucess}", HttpStatus.OK);
	}

}
