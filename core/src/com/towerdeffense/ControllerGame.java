package com.towerdeffense;


import com.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.util.Constants;
import com.util.Tuple;

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
            lastTouchPosition,
            deltaTime;
    private Vector2 lastTouch;
    private Map mapa;
    private Array<Enemy> enemies;
    private Texture[] people,
            tank,
            plane,
            antiTank,
            missiles,
            machineGun;
    private Texture btn1, btn2, btn3;

    public ControllerGame(int wave, int dificulty) {
        path = Constants.PATH_FASE1();
        initTextures();
        this.dificulty = dificulty;
        score = 0;
        money = 150;
        life = 25 - 5 * (dificulty - 1);
        lastTouch = null;
        enemies = new Array<Enemy>();
        mapa = new Map();
        this.wave = wave;
        newWave(dificulty);
        lastTouchPosition = -1;
        deltaTime=0;
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


    public void verifyButtonPress(int deltaTime) {
        if (Gdx.input.justTouched() && deltaTime > 100) {
            System.out.println("Acaba de tocar");
            int posX = Gdx.input.getX() / Constants.GRID_RESIZE_X,
                    posY = Gdx.input.getY() / Constants.GRID_RESIZE_Y;
            if (btn2 == null) { //Estamos mejorando torre
                if (posX - 1 == lastTouch.x && posY + 1 == lastTouch.y) {
                    if (mapa.upgradeCost(lastTouchPosition) <= money) {
                        mapa.lvlUpTurret(this, lastTouchPosition);
                    }
                } else if (posX + 1 == lastTouch.x && posY + 1 == lastTouch.y) {
                    mapa.sellTurret(this, lastTouchPosition);
                }
            } else { //Estamos construyendo torre
                if (posX - 1 == lastTouch.x && posY + 1 == lastTouch.y) {
                    if (Constants.MACHINE_COST <= money) {
                        mapa.buildTurret(machineGun, lastTouchPosition, Constants.MACHINEGUN);
                    }
                } else if (posX == lastTouch.x && posY + 1 == lastTouch.y) {
                    if (Constants.PLANE_COST <= money) {
                        mapa.buildTurret(missiles, lastTouchPosition, Constants.ANTIAIR);
                    }
                } else if (posX == lastTouch.x && posY + 1 == lastTouch.y) {
                    if (Constants.TANK_COST <= money) {
                        mapa.buildTurret(antiTank, lastTouchPosition, Constants.ANTITANK);
                    }
                }
            }
            deleteBtns();
        }
    }

    public void deleteBtns() {
        lastTouch = null;
        lastTouchPosition = -1;
        btn1.dispose();
        btn2.dispose();
        btn3.dispose();
        btn1 = null;
        btn2 = null;
        btn3 = null;
        deltaTime=0;
    }

    public void verifyTouch() {
        Tuple<Vector2, Boolean, Integer> posicionBotones = mapa.verifyTouch();
        if (posicionBotones != null) {
            lastTouch = posicionBotones.item1;
            lastTouchPosition = posicionBotones.item3;
            if (posicionBotones.item2) {    //Mejora
                System.out.println("Mejorando");
                if (mapa.upgradeCost(posicionBotones.item3) < money) {
                    btn1 = new Texture(Gdx.files.internal("Buttons\\levelUp.png"));
                } else {
                    btn1 = new Texture(Gdx.files.internal("Buttons\\noLevelUp.png"));
                }
                btn2 = null;
                btn3 = new Texture(Gdx.files.internal("Buttons\\sell.png"));
            } else {                        //Construccion
                System.out.println("Construyendo");
                if (Constants.MACHINE_COST <= money) {
                    btn1 = new Texture(Gdx.files.internal("Buttons\\btnMachineGun.png"));
                } else {
                    btn1 = new Texture(Gdx.files.internal("Buttons\\noBtnMachineGun.png"));
                }
                if (Constants.PLANE_COST <= money) {
                    btn2 = new Texture(Gdx.files.internal("Buttons\\btnMissile.png"));
                } else {
                    btn2 = new Texture(Gdx.files.internal("Buttons\\noBtnMissile.png"));
                }
                if (Constants.TANK_COST < money) {
                    btn3 = new Texture(Gdx.files.internal("Buttons\\btnAntiTank.png"));
                } else {
                    btn3 = new Texture(Gdx.files.internal("Buttons\\noBtnAntiTank.png"));
                }
            }
        }
    }

    public void draw(Batch batch) {
        mapa.update(this);
        mapa.draw(batch);
        if (lastTouch == null) {
            verifyTouch();
            if (btn1 != null) {
                batch.draw(btn1, (lastTouch.x - 1) * Constants.GRID_RESIZE_X, (lastTouch.y + 1) * Constants.GRID_RESIZE_Y);
            }
            if (btn2 != null) {
                batch.draw(btn2, (lastTouch.x) * Constants.GRID_RESIZE_X, (lastTouch.y + 1) * Constants.GRID_RESIZE_Y);
            }
            if (btn3 != null) {
                batch.draw(btn3, (lastTouch.x + 1) * Constants.GRID_RESIZE_X, (lastTouch.y + 1) * Constants.GRID_RESIZE_Y);
            }
        } else {
            deltaTime+=Gdx.graphics.getDeltaTime();
            verifyButtonPress(deltaTime);
        }
        if (lastTouch != null)
            System.out.println("LastTouch.X:" + (lastTouch.x) * Constants.GRID_RESIZE_X + "  LastTouch.Y:" + (lastTouch.y + 1) * Constants.GRID_RESIZE_Y);
        for (Enemy enemy : enemies) {
            enemy.draw(batch);
        }
        update();
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
            if (enemy.getX() > Gdx.graphics.getWidth()) {
                life--;
                enemy.dispose();
                enemies.removeValue(enemy, false);
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
