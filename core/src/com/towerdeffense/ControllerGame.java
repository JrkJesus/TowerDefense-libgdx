package com.towerdeffense;

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

    private int wave,
            dificulty,
            life,
            money;
    private Array<Vector2> path;
    private Array<Enemy> enemies;
    private Array<Turret> turrets;
    private Texture[] people,
            tank,
            plane,
            antiTank,
            missiles,
            machineGun;
    private Texture btnMissile,
            btnMachineGun,
            btnAntiTank,
            btnSell,
            btnLevelUp;
    private Vector2 lastTouch;
    private boolean isUpgrading,
            isBuilding;
    private static float deathTime = 5;

    public ControllerGame(int wave, int dificulty){
        path = Constants.PATH_FASE1();
        initTextures();
        this.dificulty = dificulty;
        life = 25 - 5*(dificulty-1);
        lastTouch = new Vector2();
        enemies = new Array<Enemy>();
        turrets = new Array<Turret>();
        newWave(wave, dificulty);
        this.wave = wave;
        isUpgrading = false;
        isBuilding = false;
    }

    public ControllerGame(int dificulty){
        this(0, dificulty);
    }

    private void initTextures(){
        people = new Texture[]{
                new Texture(Gdx.files.internal("Textures\\people.png")),
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
        btnMissile = new Texture(Gdx.files.internal("Textures\\btnMissile.png"));
        btnMachineGun = new Texture(Gdx.files.internal("Textures\\btnMachineGun.png"));
        btnAntiTank = new Texture(Gdx.files.internal("Textures\\btnAntiTank.png"));
        btnSell = new Texture(Gdx.files.internal("Textures\\sell.png"));
        btnLevelUp = new Texture(Gdx.files.internal("Textures\\levelUp.png"));
    }

    private void newWave(int wave, int dificulty) {
        for (int i = 0; i < 5 + (wave * 2 / 5)*dificulty; i++) {
            enemies.add(new Enemy(people, Constants.PEOPLE, path, 30 * i));
        }
        for (int i = 0; i < 0 + wave * dificulty / 4; i++) {
            enemies.add(new Enemy(tank, Constants.TANK, path, 20 * (5 + (wave * 2 / 5)*dificulty) + 70 * i));
        }
        for (int i = 0; i < 0 + wave * dificulty / 5; i++) {
            enemies.add(new Enemy(plane, Constants.PLANE, Constants.PATH_PLANE(), 20 * (5 + (wave * 2 / 5)*dificulty) + 500 * i));
        }
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public void draw(Batch batch){
        verifyTouch();

        batch.begin();
        for(Turret turret : turrets){
            turret.draw(batch);
        }

        for(Enemy enemy : enemies){
            enemy.draw(batch);
            if(enemy.getDeathTime() > deathTime)  enemies.removeValue(enemy, false);
        }


        if (isBuilding){
            batch.draw(btnMachineGun, (lastTouch.x-1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
            batch.draw(btnMissile, (lastTouch.x)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
            batch.draw(btnMissile, (lastTouch.x+1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
        } else if (isUpgrading){
            batch.draw(btnLevelUp, (lastTouch.x-1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
            batch.draw(btnSell, (lastTouch.x+1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);

        }

        batch.end();

    }

    private void verifyTouch() {
        if (Gdx.input.justTouched()) {
            System.out.println("Tocando");
            int x = (int) (Gdx.input.getX() / Constants.ESCALA_X),
                    y = (int) ((Gdx.graphics.getHeight() - Gdx.input.getY()) / Constants.ESCALA_Y);
           if( ! path.contains(new Vector2(x, y), false)){
               lastTouch.set(x,y);
               Turret turret = getTurret(x,y);
               if(turret != null){
                   isBuilding = true;
                   if (lastTouch.x - 1 == x && lastTouch.y+1 == y) {
                       System.out.println("Construir machine");
                       turrets.add(new Turret(this, machineGun, Constants.MACHINEGUN, (int) lastTouch.x * 64, (int) lastTouch.y * 64));
                       isBuilding = false;
                       lastTouch = new Vector2();
                   } else if(lastTouch.x  == x && lastTouch.y+1 == y){
                       turrets.add(new Turret(this, missiles, Constants.ANTIAIR, (int)lastTouch.x, (int)lastTouch.y));
                       isBuilding = false;
                       lastTouch = new Vector2();
                   } else if (lastTouch.x + 1 == x && lastTouch.y+1 == y){
                       turrets.add(new Turret(this, antiTank, Constants.ANTITANK, (int)lastTouch.x, (int)lastTouch.y));
                       isBuilding = false;
                       lastTouch = new Vector2();
                   }
               }
           }
        }
    }

    private Turret getTurret(int x, int y) {
        for (Turret turret : turrets ){
            if( turret.x() == x && turret.y() == y ) return turret;
        }

        return null;
    }

    public void dispose(){
        for (Turret turret : turrets){
            turret.dispose();
        }
        for (int i = 0; i < 3; i++) {
            machineGun[i].dispose();
            missiles[i].dispose();
            antiTank[i].dispose();
        }
        for (int i = 0; i < 2; i++) {
            people[i].dispose();
            tank[i].dispose();
            plane[i].dispose();
        }
        btnAntiTank.dispose();
        btnLevelUp.dispose();
        btnMachineGun.dispose();
        btnMissile.dispose();
        btnSell.dispose();
    }

    public boolean isWinner() {
        return enemies.size <= 0;
    }

    public boolean isLoser() {
        return life <= 0;
    }
}
