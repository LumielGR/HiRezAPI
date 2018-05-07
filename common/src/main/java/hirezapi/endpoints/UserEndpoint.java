package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.json.Achievements;
import hirezapi.json.Player;
import hirezapi.json.PlayerStatus;

public class UserEndpoint extends AbstractEndpoint {
  public UserEndpoint(HiRezApi api) {
    super(api);
  }

  public Player getPlayer(String user) {
    return api.getRestController()
          .request(buildUrl("getplayer", user), Player[].class)[0];
  }

  public Achievements getPlayerAchievments(long id) {
    return api.getRestController()
          .request(buildUrl("getplayerachievements", Long.toString(id)), Achievements.class);
  }

  public PlayerStatus getPlayerStatus(String user) {
    return api.getRestController()
          .request(buildUrl("getplayerstatus", user), PlayerStatus[].class)[0];
  }
}