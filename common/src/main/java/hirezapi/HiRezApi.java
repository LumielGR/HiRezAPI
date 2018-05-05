package hirezapi;

import hirezapi.endpoints.*;
import hirezapi.rest.RestClient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class HiRezApi<G extends GameEndpoint> {
    protected final Configuration configuration;
    protected final RestTemplate restClient;

    public UserEndpoint userEndpoint() {
        return new UserEndpoint(this);
    }

    public SessionEndpoint sessionEndpoint() {
        return new SessionEndpoint(this);
    }

    public MatchesEndpoint matchesEndpoint() {
        return new MatchesEndpoint(this);
    }

    public TeamsEndpoint teamsEndpoint() {
        return new TeamsEndpoint(this);
    }

    public abstract G gameEndpoint();
}
