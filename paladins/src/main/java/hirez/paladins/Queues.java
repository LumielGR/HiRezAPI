package hirez.paladins;

import hirez.api.object.interfaces.Queue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Queues implements Queue {
    SIEGE(424, "Siege"),
    TDM(469, "Team Deathmatch"),
    ONSLAUGHT(452, "Onslaught"),
    COMPETITIVE_KBM(486, "Competitive"),
    TDM_PRACTICE(470, "Team Deathmatch (Practice)"),
    SIEGE_PRACTICE(425, "Siege (Practice)"),
    COMPETITIVE_GAMEPAD(428, "Competitive (Controller)"),
    ONSLAUGHT_PRACTICE(453, "Onslaught (Practice)"),
    CRAZY_KING(488, "Crazy King"),
    TEST_MAPS(445, "Test Maps"),

    THRONE_DM(480, "Throne (TDM)"),
    ARCHIVES_DM(472, "Magistrate's Archives (TDM)"),
    TRADE_DISTRICT_DM(468, "Trade District (TDM)"),
    FOREMAN_DM(471, "Foreman's Rise (TDM)"),
    DRAGON_ARENA_DM(484, "Dragon Arena(TDM)"),
    STONE_KEEP_SIEGE(423, "Stone Keep (Siege)"),
    BRIGHTMARSH_SIEGE(458, "Brightmarsh (Siege)"),
    ABYSS_DM(479, "Abyss (TDM)"),
    FROG_ISLE_SIEGE(433, "Frog Isle (Siege)"),
    JAGUAR_FALLS_SIEGE(438, "Jaguar Falls (Siege)"),
    SNOWFALL_JUNCTION_DM(454, "Snowfall Junction (TDM)"),
    ARCHIVES_ONSLAUGHT(464, "Magistrate's Archives (Onslaught)"),
    SERPENT_BEACH_SIEGE(440, "Serpent Beach (Siege)"),
    FISH_MARKET_SIEGE(431, "Fish Market (Siege)"),
    TIMBER_MILL_SIEGE(430, "Timber Mill (Siege)"),
    SPLITSTONE_QUARRY_SIEGE(459, "Splitstone Quarry (Siege)"),
    WARDERS_GATE_SIEGE(485, "Warders Gate (Siege)"),
    FOREMAN_ONSLAUGHT(462, "Foreman's Rise (Onslaught)"),
    FROZEN_GUARD_SIEGE(432, "Frozen Guard (Siege)"),
    MARAUDERS_PORT_ONSLAUGHT(483, "Marauder's Port (Onslaught)"),
    PRIMAL_COURT_ONSLAUGHT(455, "Primal Court (Onslaught)"),
    ASCENSION_PEAK_SIEGE(473, "Ascension Peak (Siege)"),
    ICE_MINES_SIEGE(439, "Ice Mines (Siege)"),
    CRAZY_KING_CUSTOM(489, "Crazy King (Custom)"),
    SHATTERED_DESERT_SIEGE(487, "Shattered Desert (Siege)");

    @Getter
    private final Integer id;
    private final String named;

    public static Queues fromId(int id) throws IllegalAccessException {
        for (Queues queue : values()) {
            if (queue.id == id) {
                return queue;
            }
        }

        throw new IllegalAccessException("Unknown Queue ID: " + id);
    }

    @Override
    public String getName() {
        return named;
    }

    @Override
    public boolean isRanked() {
        return name().startsWith("COMPETITIVE_");
    }
}
