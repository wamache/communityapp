package com.communityapp.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SideEffectRepository extends JpaRepository<SideEffect, Long> {

}
