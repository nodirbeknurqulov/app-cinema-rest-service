package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import uz.pdp.appcinemarestservice.entity.User;
import uz.pdp.appcinemarestservice.payload.LoginDto;
import uz.pdp.appcinemarestservice.payload.UserDto;
import uz.pdp.appcinemarestservice.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

// Nurkulov Nodirbek 4/6/2022  9:33 AM
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginController {

    private static final String authorizationRequestBaseUri = "oauth2/authorization";

    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    private final ClientRegistrationRepository clientRegistrationRepository;

    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("error")
    public String getErrorPage() {
        return "error";
    }

    @GetMapping("success-url")
    public String getSuccessPage(Model model, UsernamePasswordAuthenticationToken authenticationToken){
        User user =(User) authenticationToken.getPrincipal();
        model.addAttribute("name",user.getFirstName()+" "+user.getLastName());
        return "success-page";
    }

    @GetMapping("success-url-oauth")
    public String getSuccessPage(Model model, OAuth2AuthenticationToken authentication) {

        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());

        String userInfoEndpointUri = client.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUri();

        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                    .getTokenValue());

            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity<Map> response = restTemplate
                    .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            Map userAttributes = response.getBody(); // User haqidagi ma'lumotlar

            // TODO: 4/7/2022 userni db ga save qilish;

            assert userAttributes != null;
            model.addAttribute("name", userAttributes.get("name"));
            model.addAttribute("imgUrl", userAttributes.get("picture"));
            System.out.println(userAttributes.get("email"));
        }
        return "success-page";
    }

    @GetMapping("login")
    public String getLoginPage(Model model) {

        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        assert clientRegistrations != null;
        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);
        model.addAttribute("test", "Working!!!");
        return "login";
    }
}
