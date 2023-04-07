package com.communityapp.reposirory;

import com.communityapp.model.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UtilitiesRepository extends JpaRepository<Utilities, Integer> {

}
