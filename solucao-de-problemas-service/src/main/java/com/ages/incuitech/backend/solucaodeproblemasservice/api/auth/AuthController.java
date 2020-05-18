package com.ages.incuitech.backend.solucaodeproblemasservice.api.auth;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.auth.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final AuthService facebookService;

  public AuthController(AuthService facebookService) {
    this.facebookService = facebookService;
  }

  @GetMapping("/getUrlAuthorization")
  public String getAuthorizationUrl() {
    return facebookService.createFacebookAuthorizationURL();
  }

  @GetMapping("/facebook")
  public void createFacebookAccessToken(@RequestParam("code") String code) {
    facebookService.createFacebookAccessToken(code);
  }

  @GetMapping("/getPageAccessToken")
  public String getPageAccessToken() {
    return facebookService.getPageAccessToken();
  }
}
