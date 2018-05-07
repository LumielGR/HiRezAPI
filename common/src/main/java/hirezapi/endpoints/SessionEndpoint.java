package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.json.DataUsage;
import hirezapi.json.Ping;
import hirezapi.json.SessionCreation;
import hirezapi.json.SessionTest;
import hirezapi.session.EnvironmentalSessionStorage;
import hirezapi.session.SessionCreationException;
import hirezapi.session.SessionStorage;
import lombok.Getter;
import lombok.Setter;

public class SessionEndpoint extends AbstractEndpoint {
  @Getter
  @Setter
  private SessionStorage sessionStorage;

  public SessionEndpoint(HiRezApi api, SessionStorage sessionStorage) {
    super(api);
    this.sessionStorage = sessionStorage;
  }

  public SessionEndpoint(HiRezApi api) {
    super(api);
    setSessionStorage(new EnvironmentalSessionStorage());
  }

  /**
   * Pinging into API for checking API status.
   * @return ping Response
   */
  public Ping ping() {
    return new Ping(api.getRestController().request(buildUrl("ping"), String.class));
  }

  /**
   * Creating session for starting query into API. It is required to using more endpoints.
   * using {@link #ping()} is not applicable for creation session.
   * This method automatically saving into {@link SessionStorage session storage}.
   * @return a Session creation context
   */
  public SessionCreation create() {
    SessionCreation sessionCreation = api.getRestController()
          .request(buildUrl("createsession"), SessionCreation.class);
    if (!sessionCreation.getReturnedMessage().equals("Approved")) {
      throw new SessionCreationException(sessionCreation);
    } else {
      getLog().info("Session Created for {}", api.getConfiguration().getPlatform().toString());
      sessionStorage.set(api.getConfiguration().getPlatform(), sessionCreation);
    }

    return sessionCreation;
  }

  public SessionTest test() {
    return new SessionTest(api.getRestController().request(buildUrl("testsession"), String.class));
  }

  public DataUsage getDataUsage() {
    return api.getRestController().request(buildUrl("getdataused"), DataUsage[].class)[0];
  }
}