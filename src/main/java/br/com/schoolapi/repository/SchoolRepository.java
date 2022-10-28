package br.com.schoolapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.schoolapi.model.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {

	/// NAME///
	@Query("FROM School c " + "where c.name = :searchTerm ")
	Page<School> searchwithName(@Param("searchTerm") String searchTerm, Pageable pageable);

	@Query("FROM School c " + "where c.name <> :searchTerm ")
	Page<School> searchDifName(@Param("searchTerm") String searchTerm, Pageable pageable);

	@Query("FROM School c " + "where c.name like %:searchTerm% ")
	Page<School> searchLikeName(@Param("searchTerm") String searchTerm, Pageable pageable);

	
	/// ADDRESS///
	@Query("FROM School c " + "where c.address = :searchTerm ")
	Page<School> searchwithAddress(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	@Query("FROM School c " + "where c.address <> :searchTerm ")
	Page<School> searchDifAddress(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	@Query("FROM School c " + "where c.address like %:searchTerm% ")
	Page<School> searchLikeAddress(@Param("searchTerm") String searchTerm, Pageable pageable);

	
	// STATUS//
	@Query("FROM School c " + "where c.status = :searchTerm ")
	Page<School> searchwithstatus(@Param("searchTerm") String searchTerm, Pageable pageable);

	@Query("FROM School c " + "where c.status <> :searchTerm ")
	Page<School> searchDifstatus(@Param("searchTerm") String searchTerm, Pageable pageable);

	@Query("FROM School c " + "where c.status like %:searchTerm% ")
	Page<School> searchLikeStatus(@Param("searchTerm") String searchTerm, Pageable pageable);

	
	
	/// INEP///
	@Query("FROM School c " + "where c.inep = :searchTerm ")
	Page<School> searchwithInep(@Param("searchTerm") int searchTerm, Pageable pageable);

	@Query("FROM School c " + "where c.inep > :searchTerm ")
	Page<School> searchBiggerInep(@Param("searchTerm") int searchTerm, Pageable pageable);

	@Query("FROM School c " + "where c.inep < :searchTerm ")
	Page<School> searchSmallerInep(@Param("searchTerm") int searchTerm, Pageable pageable);

	@Query("FROM School c " + "where c.inep <> :searchTerm ")
	Page<School> searchDifInep(@Param("searchTerm") int searchTerm, Pageable pageable);

}
