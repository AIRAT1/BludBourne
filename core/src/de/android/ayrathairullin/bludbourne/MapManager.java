package de.android.ayrathairullin.bludbourne;


import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

import java.util.Hashtable;

public class MapManager {
    private static final String TAG = MapManager.class.getSimpleName();

    private Hashtable<String, String> mapTable;
    private Hashtable<String, Vector2> playerStartLocationTable;

    private static final String TOP_WORLD = "TOP_WORLD";
    private static final String TOWN = "TOWN";
    private static final String CASTLE_OF_DOOM = "CASTLE_OF_DOOM";

    private static final String MAP_COLLISION_LAYER = "MAP_COLLISION_LAYER";
    private static final String MAP_SPAWNS_LAYER = "MAP_SPAWNS_LAYER";
    private static final String MAP_PORTAL_LAYER = "MAP_PORTAL_LAYER";
    private static final String PLAYER_START = "PLAYER_START";

    private Vector2 playerStartPositionRect, closestPlayerStartPosition, convertedUnits;
    private Vector2 playerStart;
    private TiledMap currentMap = null;
    private String currentMapName;
    private MapLayer collisionLayer = null, portalLayer = null, spawnsLayer = null;

    public static final float UNIT_SCALE = 1 / 16;

    public MapManager() {
        playerStart = new Vector2(0, 0);

        mapTable = new Hashtable<String, String>();
        mapTable.put(TOP_WORLD, "maps/topworld.tmx");
        mapTable.put(TOWN, "maps/town.tmx");
        mapTable.put(CASTLE_OF_DOOM, "maps/castle_of_doom.tmx");

        playerStartLocationTable = new Hashtable<String, Vector2>();
        playerStartLocationTable.put(TOP_WORLD, playerStart.cpy());
        playerStartLocationTable.put(TOWN, playerStart.cpy());
        playerStartLocationTable.put(CASTLE_OF_DOOM, playerStart.cpy());

        playerStartPositionRect = new Vector2(0, 0);
        closestPlayerStartPosition = new Vector2(0, 0);
        convertedUnits = new Vector2(0, 0);
    }
}
