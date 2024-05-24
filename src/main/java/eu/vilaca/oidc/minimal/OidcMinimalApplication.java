package eu.vilaca.oidc.minimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class OidcMinimalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OidcMinimalApplication.class, args);
	}

	@GetMapping("/")
	public Map<String, Object> index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
									 @AuthenticationPrincipal OAuth2User oauth2User) {
		return Map.of("userName", oauth2User.getName(),
				"clientName", authorizedClient.getClientRegistration().getClientName(),
				"userAttributes", oauth2User.getAttributes());
	}
}
