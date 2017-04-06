package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;
import com.models.Enemy;
import com.models.Turret;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

import java.util.Random;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {
    private Texture fondo;
    private Texture[] machineGun, antiAir, antiTank;
    private Button btnMachine, btnAntiAir, bntAntiTank, btnLvlUp, btnSell;
    private Button.ButtonStyle cssMachine, cssAntiAir, cssAntiTank, cssLvlUp, cssSell;
    private Sprite plane, tank, people;
    private Array<Enemy> enemies;
    private Array<Turret> turrets;
    private Stage stage;
    private int life, money;
    private Array<Vector2> path;
    private Vector2 lastTouch;
    private boolean isBuilding, isUpgrading;

    public PantallaJuego(MainTowerDeffense _mtd, int fase) {
        super(_mtd);
        isBuilding = false;
        isUpgrading = false;
        stage = new Stage();
        turrets = new Array<Turret>();
        enemies = new Array<Enemy>();
        path = Constants.PATH_FASE1();
        lastTouch = new Vector2(-1, -1);
        initTextures(fase);
        newWave(0, 10);
        life = 10;
        Gdx.input.setInputProcessor(stage);
        //TODO: 21/03/2017 la vida tiene que depender de la dificultad elegida.
    }

    private void newWave(int initTime, int wave) {
        // TODO: 04/04/2017 enemigos encima de actores
        RandomXS128 rnd = new RandomXS128();
        for (int i = 0; i < 5 + (wave * 2 / 5); i++) {
            enemies.add(new Enemy(this, people, path, 30 * i, Constants.PEOPLE));
        }
        for (int i = 0; i < 0 + wave / 4; i++) {
            enemies.add(new Enemy(this, tank, path, 20 * (5 + (wave * 2 / 5)) + 70 * i, Constants.TANK));
        }
        for (int i = 0; i < 0 + wave / 5; i++) {
            enemies.add(new Enemy(this, plane, Constants.PATH_PLANE(), 20 * (5 + (wave * 2 / 5)) + 500 * i, Constants.PLANE));
        }
    }

    public void initTextures(int fase) {
        fondo = new Texture(Gdx.files.internal("Paths\\path_stage" + fase + ".png"));

        plane = new Sprite(new Texture(Gdx.files.internal("Textures\\plane.png")));
        //                     new Texture(Gdx.files.internal("Textures\\planeDead.png")) };

        tank = new Sprite(new Texture(Gdx.files.internal("Textures\\tank.png")));
        //                    new Texture(Gdx.files.internal("Textures\\tankDead.png")) };

        people = new Sprite(new Texture(Gdx.files.internal("Textures\\people.png")));
        //                      new Texture(Gdx.files.internal("Textures\\peopleDead.png")) };

        antiAir = new Texture[]{new Texture(Gdx.files.internal("Textures\\antiAirLv1.png")),
                new Texture(Gdx.files.internal("Textures\\antiAirLv2.png")),
                new Texture(Gdx.files.internal("Textures\\antiAirLv3.png"))};

        antiTank = new Texture[]{new Texture(Gdx.files.internal("Textures\\antiTankLv1.png")),
                new Texture(Gdx.files.internal("Textures\\antiTankLv2.png")),
                new Texture(Gdx.files.internal("Textures\\antiTankLv3.png"))};

        machineGun = new Texture[]{new Texture(Gdx.files.internal("Textures\\machineGunLv1.png")),
                new Texture(Gdx.files.internal("Textures\\machineGunLv2.png")),
                new Texture(Gdx.files.internal("Textures\\machineGunLv3.png"))};
        cssMachine = new Button.ButtonStyle();
        cssMachine.up = skin.getDrawable("touchpad");
        btnMachine = new Button(cssMachine);
        cssLvlUp = new Button.ButtonStyle();
        cssLvlUp.up = skin.getDrawable("tooltip");
        btnLvlUp = new Button(cssLvlUp);
        cssSell = new Button.ButtonStyle();
        cssSell.up = skin.getDrawable("button");
        btnSell = new Button(cssSell);
    }

    @Override
    public void render(float delta) {
//        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        mtd.batch.begin();
        mtd.batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        turretsAttack();

        for(Turret turret : turrets)
            turret.draw(mtd.batch);

        for (Enemy enemy : enemies)
            enemy.draw(mtd.batch);

        mtd.batch.end();

        stage.act();
        stage.draw();

        verifyTouch();

//        stage.act(Gdx.graphics.getDeltaTime());
//        stage.draw();

        if (life <= 0) {
            //TODO: 27/03/2017 pantalla game over
        } else if (enemies.size == 0) {
            //TODO: 27/03/2017 pantalla ganadora
        }

    }

    private void verifyTouch() {
// TODO: 04/04/2017 no puedo poner torres al borde del camino
        if (Gdx.input.justTouched()) {
            System.out.println("Tocando");
            int x = Gdx.input.getX() / 64,
                    y = (height - Gdx.input.getY()) / 64;
            if ((!isUpgrading || !isBuilding) && path.contains(new Vector2(x, y), false)) { // añadir || camino -1;
                System.out.println("Camino");
                clearAllButtons();
                isBuilding = false;
                isUpgrading = false;
                lastTouch = new Vector2();
            } else if (isBuilding) {
                System.out.println("Abierto construccion");
                if (lastTouch.x - 1 == x && lastTouch.y == y) {
                    System.out.println("Construir machine");
                    turrets.add(new Turret(machineGun, Constants.MACHINEGUN, (int) lastTouch.x * 64, (int) lastTouch.y * 64));
                    clearAllButtons();
                    isBuilding = false;
                    lastTouch = new Vector2();
//                } else if(lastTouch == new Vector2(x-1,y)){
//                    turrets.add(new Turret(antiAir, Constants.ANTIAIR, (int)lastTouch.x, (int)lastTouch.y));
//                    clearAllButtons();
//                    isBuilding = false;
//                    lastTouch = new Vector2();
//                } else if(lastTouch == new Vector2(x,y-1)){
//                    turrets.add(new Turret(antiTank, Constants.ANTITANK, (int)lastTouch.x, (int)lastTouch.y));
//                    clearAllButtons();
//                    isBuilding = false;
//                    lastTouch = new Vector2();
                } else if (lastTouch != new Vector2(x, y)) {
                    System.out.println("Tocando en otro lado");
                    isBuilding = false;
                    clearAllButtons();
                    lastTouch.set(x, y);
//                    verifyTouch();
                }
            } else if (isUpgrading) {
                System.out.println("Abierto mejorando");
                Turret selectTurret = getTurret((int) lastTouch.x, (int) lastTouch.y);
                isUpgrading = false;
                if (lastTouch.x - 1 == x && lastTouch.y == y) {
                    selectTurret.levelUp();
                    clearAllButtons();
                } else if (lastTouch.x + 1 == x && lastTouch.y == y) {
                    clearAllButtons();
                    addMoney((int) selectTurret.getValue());
                    turrets.removeValue(selectTurret, true);
                    isUpgrading = false;
                    lastTouch = new Vector2();

                } else if (lastTouch != new Vector2(x, y)) {
                    isUpgrading = false;
                    clearAllButtons();
                    lastTouch.set(x, y);
                } else {
                    isUpgrading = true;
                }
            } else if (getTurret(x, y) != null) {
                System.out.println("Mejorando");
                clearAllButtons();
                btnLvlUp.setPosition((x - 1) * 64, y * 64);
                btnSell.setPosition((x + 1) * 64, y * 64);
                stage.addActor(btnLvlUp);
                stage.addActor(btnSell);
                isUpgrading = true;
                lastTouch.set(x, y);
            } else {
                System.out.println("Construir");
                clearAllButtons();
                isBuilding = true;
                btnMachine.setPosition((x - 1) * 64, y * 64);
//                btnAntiAir.setPosition((x+1)*64,y*64);
//                btnAntiAir.setPosition((x*64,(y-1)*64);
                stage.addActor(btnMachine);
//                stage.addActor(btnAntiAir);
//                stage.addActor(bntAntiTank);
                lastTouch.set(x, y);
            }

        }
    }

    private void clearAllButtons() {
        for (Actor actor : stage.getActors()) {
            actor.remove();
        }
    }

    private Turret getTurret(int x, int y) {

        for( Turret turret : turrets){
            if(turret.position(x,y)){
                return turret;
            }
        }


        return null;
    }

    private void turretsAttack() {
        for(Turret turret : turrets){
            if (!turret.isReloading()) {
                int i = 1;
                Enemy enemy = enemies.get(0);
                while (i<enemies.size && !enemy.isAttackable() && !turret.isReachable(enemy.getPosition()))
                    enemy = enemies.get(i++);
                if( i < enemies.size ) {
                    turret.attack((int) enemy.getX() / 64, (int) enemy.getY() / 64);
                    enemy.receiveDamage(turret.getAttack());
                }
            }
        }
    }

    private void addMoney(int amount) {
        money += amount;
    }


    public void eliminateEnemy(Enemy enemy) {
        enemies.removeValue(enemy, false);
    }

    public void loseLife(Enemy enemy) {
        eliminateEnemy(enemy);
        life--;
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        fondo.dispose();
        for (int i = 0; i < 3; i++) {
            antiAir[i].dispose();
            antiTank[i].dispose();
            machineGun[i].dispose();
        }

        plane.getTexture().dispose();
        tank.getTexture().dispose();
        people.getTexture().dispose();

    }
}
