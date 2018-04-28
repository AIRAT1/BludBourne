package de.android.ayrathairullin.bludbourne.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MainGameScreen implements Screen{
    private static final String TAG = MainGameScreen.class.getSimpleName();

    private static class VIEWPORT {
        static float viewportWidth;
        static float viewportHeight;
        static float virtualWidth;
        static float virtualHeight;
        static float physicalWidth;
        static float physicalHeight;
        static float aspectRatio;
    }

    private PlayerController controller;
    private TextureRegion currentPlayerFrame;
    private Sprite currentPlayerSprite;

    private OrthogonalTiledMapRenderer mapRenderer = null;
    private OrthographicCamera camera = null;
    private static MapManager mapMgr;
    private static Entity player;

    public MainGameScreen() {
        mapMgr = new MapManager();
    }

    @Override
    public void show() {
        setupViewport(10, 10);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);
        mapRenderer = new OrthogonalTiledMapRenderer(mapMgr.getCurrentMap(), MapManager.UNIT_SCALE);
        mapRenderer.setView(camera);
        Gdx.app.debug(TAG, "Unit scale value is: " + mapRenderer.getUnitScale());
        player = new Entity();
        player.init(mapMgr.getPlayerStartUnitScaled().x, mapMgr.getPlayerStartUnitScaled().y);
        currentPlayerSprite = player.getFrameSprite();
        controller = new PlayerController(player);
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(currentPlayerSprite.getX(), currentPlayerSprite.getY(), 0);
        camera.update();
        currentPlayerFrame = player.getFrame();
        updatePortalLayerActivation(player.boundingBox);
        if (!isCollisionWithMapLayer(player.boundingBox)) {
            player.setNextPositionToCurrent();
        }
        controller.update(delta);
        mapRenderer.setView(camera);
        mapRenderer.render();

        mapRenderer.getBatch().begin();
        mapRenderer.getBatch().draw(currentPlayerFrame, currentPlayerSprite.getX(),
                currentPlayerSprite.getY(), 1, 1);
        mapRenderer.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
