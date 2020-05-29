package com.ages.incuitech.backend.solucaodeproblemasservice.business.adm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AdministradoresService {
    @Value("${mail.receiver.admin}")
    private String emails;

    public List<String> getEmails() {
        return Arrays.asList(this.emails.split(","));
    }
}
