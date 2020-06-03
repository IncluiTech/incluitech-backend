package com.ages.incuitech.backend.solucaodeproblemasservice.business.adm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdministradoresService {
    @Value("${mail.receiver.admin}")
    private String emails;

    public List<String> getEmails() {
        return Arrays.asList(this.emails.split(","));
    }
}
