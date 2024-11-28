package es.deusto.sd.facebook.service;

import es.deusto.sd.facebook.entity.FacebookUser;

import es.deusto.sd.facebook.repository.FacebookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class FacebookAuthService {

    @Autowired
    private FacebookUserRepository facebookUserRepository;

    public boolean validate(String email, String password) {
        FacebookUser user = facebookUserRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}
