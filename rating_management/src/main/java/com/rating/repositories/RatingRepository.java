package com.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rating.model.RatingForm;


public interface RatingRepository extends JpaRepository<RatingForm,Integer> {

	public List<RatingForm> findAllByEmailDriver(String email_driver);
	 public Long countByEmailDriver(String email_driver);
	public Boolean existsByIdPackage(int id_package);
	public Boolean existsByEmailDriver(String email_driver);

}
