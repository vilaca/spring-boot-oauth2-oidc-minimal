package eu.vilaca.oidc.minimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
	public Map<String, String> getUserInfo() {
		final var ctx = SecurityContextHolder.getContext();
		final OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) ctx.getAuthentication();
		final var provider = auth.getAuthorizedClientRegistrationId();
		final var principal = auth.getPrincipal();
		final var id = principal.getName();
		return Map.of(
				"provider", provider,
				"id", id
		);
	}
}
