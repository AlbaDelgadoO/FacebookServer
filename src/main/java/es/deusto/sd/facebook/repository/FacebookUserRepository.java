package es.deusto.sd.facebook.repository;

import es.deusto.sd.facebook.entity.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacebookUserRepository extends JpaRepository<FacebookUser, String> {
    FacebookUser findByEmail(String email);
}