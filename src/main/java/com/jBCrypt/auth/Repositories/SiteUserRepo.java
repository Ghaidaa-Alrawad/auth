package com.jBCrypt.auth.Repositories;

import com.jBCrypt.auth.model.SiteUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepo extends JpaRepository<SiteUserModel, Long> {
}
