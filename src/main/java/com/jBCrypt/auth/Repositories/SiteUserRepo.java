package com.jBCrypt.auth.Repositories;

import com.jBCrypt.auth.model.SiteUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteUserRepo extends JpaRepository<SiteUserModel, Long> {
    SiteUserModel findByUsername(String username);

}
