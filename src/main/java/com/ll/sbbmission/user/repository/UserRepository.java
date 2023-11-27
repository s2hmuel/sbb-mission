package com.ll.sbbmission.user.repository;

import com.ll.sbbmission.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long> {
}
