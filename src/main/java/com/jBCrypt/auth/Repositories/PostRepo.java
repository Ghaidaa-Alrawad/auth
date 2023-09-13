package com.jBCrypt.auth.Repositories;

import com.jBCrypt.auth.model.PostModel;
import com.jBCrypt.auth.model.SiteUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<PostModel, Long> {
}
//    PostModel findById(SiteUserModel userId);
