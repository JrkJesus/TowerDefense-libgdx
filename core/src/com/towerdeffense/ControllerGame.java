package com.towerdeffense;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.util.Constants;

/**
 * Created by jesus on 05/04/2017.
 */

public class ControllerGame {

    private int dificulty;
    private Array<Vector2> path;
    private int score,
            money,
            life,
            wave,
            isUpgrading;
    private Vector2 lastTouch;
    private boolean isBuilding;
    private Map mapa;
    private Array<Enemy> enemies;
    private Texture[] people,
            tank,
            plane,
            antiTank,
            missiles,
            machineGun,
            btnMissile,
            btnMachineGun,
            btnAntiTank,
            btnLevelUp;
    private Texture btnSell;

    public ControllerGame(int wave, int dificulty) {
        path = Constants.PATH_FASE1();
        initTextures();
        this.dificulty = dificulty;
        score = 0;
        money = 150;
        life = 25 - 5 * (dificulty - 1);
        lastTouch = new Vector2();
        enemies = new Array<Enemy>();
        mapa = new Map();
        this.wave = wave;
        newWave(dificulty);
        isUpgrading = 0;
        isBuilding = false;
    }

    public ControllerGame(int dificulty) {
        this(0, dificulty);
    }

    private void initTextures() {
        people = new Texture[]{
                new Texture(Gdx.files.internal("Textures\\people (" + (Constants.rnd.nextInt(4) + 1) + ").png")),
                new Texture(Gdx.files.internal("Textures\\peopleDead.png"))
        };
        tank = new Texture[]{
                new Texture(Gdx.files.internal("Textures\\tank.png")),
                new Texture(Gdx.files.internal("Textures\\tankDead.png"))
        };
        plane = new Texture[]{
                new Texture(Gdx.files.internal("Textures\\plane.png")),
                new Texture(Gdx.files.internal("Textures\\planeDead.png"))
        };
        antiTank = new Texture[]{
                new Texture(Gdx.files.internal("Textures\\antiTankLv1.png")),
                new Texture(Gdx.files.internal("Textures\\antiTankLv2.png")),
                new Texture(Gdx.files.internal("Textures\\antiTankLv3.png"))
        };
        missiles = new Texture[]{
                new Texture(Gdx.files.internal("Textures\\missilesLv1.png")),
                new Texture(Gdx.files.internal("Textures\\missilesLv2.png")),
                new Texture(Gdx.files.internal("Textures\\missilesLv3.png"))
        };
        machineGun = new Texture[]{
                new Texture(Gdx.files.internal("Textures\\machineGunLv1.png")),
                new Texture(Gdx.files.internal("Textures\\machineGunLv2.png")),
                new Texture(Gdx.files.internal("Textures\\machineGunLv3.png"))
        };
        btnMissile = new Texture[]{
                new Texture(Gdx.files.internal("Buttons\\btnMissile.png")),
                new Texture(Gdx.files.internal("Buttons\\btnMissile.png"))
        };
        btnMachineGun = new Texture[]{
                new Texture(Gdx.files.internal("Buttons\\btnMachineGun.png")),
                new Texture(Gdx.files.internal("Buttons\\btnMachineGun.png"))
        };
        btnAntiTank = new Texture[]{
                new Texture(Gdx.files.internal("Buttons\\btnAntiTank.png")),
                new Texture(Gdx.files.internal("Buttons\\btnAntiTank.png"))
        };
        btnLevelUp = new Texture[]{
                new Texture(Gdx.files.internal("Buttons\\levelUp.png")),
                new Texture(Gdx.files.internal("Buttons\\levelUp.png"))
        };
        btnSell = new Texture(Gdx.files.internal("Buttons\\sell.png"));

    }

    public void newWave(int dificulty) {
        for (int i = 0; i < 5 + (wave * 2 / 5) * dificulty; i++) {
            enemies.add(new Enemy(people, Constants.PEOPLE, path, people[0].getWidth() * i));
        }
        for (int i = 0; i < 0 + wave * dificulty / 4; i++) {
            enemies.add(new Enemy(tank, Constants.TANK, path, people[0].getWidth() * (5 + (wave * 2 / 5) * dificulty) + tank[0].getWidth() * i));
        }
        for (int i = 0; i < 0 + wave * dificulty / 5; i++) {
            enemies.add(new Enemy(plane, Constants.PLANE, Constants.PATH_PLANE(), (5 + (wave * 2 / 5) * dificulty) + plane[0].getWidth() * i));
        }
        wave++;
    }

    public void draw(Batch batch) {

        mapa.update(this);
        mapa.draw(batch);
        for (Enemy enemy : enemies) {
            enemy.draw(batch);
        }
//        Pixmap pm=new Pixmap(50,50, Pixmap.Format.RGBA8888);
//        pm.setColor(Color.BLUE);
//        pm.drawCircle(pm.getWidth()/2,pm.getHeight()/2,pm.getHeight()/5);
//        Texture textura=new Texture(pm);
//        for(Vector2 point:path)
//        {
//            batch.draw(textura,point.x*Constants.GRID_RESIZE_X+Constants.GRID_RESIZE_X/2-25,point.y*Constants.GRID_RESIZE_Y+Constants.GRID_RESIZE_Y/2-25);
//        }
//        textura.dispose();
//        pm.dispose();
    }

    public void addScore() {
        addScore((int) (score * 0.1));
    }

    public void addScore(int valor) {
        score += valor;
    }

    public void addMoney(int valor) {
        money += valor;
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public int getLife() {
        return life;
    }

    public int getMoney() {
        return money;
    }

    public int getScore() {
        return score;
    }

    public boolean isWinner() {
        return false;
    }

    public boolean isLoser() {
        return false;
    }


    public void update() {
        for (Enemy enemy : enemies) {
            if(enemy.getX()>Gdx.graphics.getWidth())
            {
                life--;
                enemy.dispose();
                enemies.removeValue(enemy,false);
            }
        }
    }

    public void dispose() {
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
        mapa.dispose();
    }
}
