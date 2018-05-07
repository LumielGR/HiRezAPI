package hirezapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AbstractItem extends Model {
  @JsonProperty("DeviceName")
  private String itemName;
  @JsonProperty("IconId")
  private int iconId;
  @JsonProperty("ItemId")
  private int itemId;
  @JsonProperty("Price")
  private int price;
  @JsonProperty("itemIcon_URL")
  private String itemImage;
}