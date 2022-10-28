package br.com.schoolapi.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schools {

	private List<School> schools;
}
