package hirez.api.object.statuspage;

import hirez.api.object.interfaces.IStatusPage;
import lombok.Data;

import java.util.List;

@Data
public class Components implements IStatusPage {
    private final Page page;
    private final List<Component> components;
}
