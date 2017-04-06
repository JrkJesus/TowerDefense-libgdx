package com.towerdeffense;

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

    private int isUpgrading,
            wave,
            dificulty,
            life,
            money,
            score;
    private Array<Vector2> path;
    private Array<Enemy> enemies;
    private Array<Turret> turrets;
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
    private Vector2 lastTouch;
    private boolean isBuilding;
    private static float deathTime = 5;

    public ControllerGame(int wave, int dificulty){
        path = Constants.PATH_FASE1();
        initTextures();
        this.dificulty = dificulty;
        score = 0;
        money = 150;
        life = 25 - 5*(dificulty-1);
        lastTouch = new Vector2();
        enemies = new Array<Enemy>();
        turrets = new Array<Turret>();
        this.wave = wave;
        newWave(dificulty);
        isUpgrading = 0;
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
        for (int i = 0; i < 5 + (wave * 2 / 5)*dificulty; i++) {
            enemies.add(new Enemy(this, people, Constants.PEOPLE, path, 30 * i));
        }
        for (int i = 0; i < 0 + wave * dificulty / 4; i++) {
            enemies.add(new Enemy(this, tank, Constants.TANK, path, 20 * (5 + (wave * 2 / 5)*dificulty) + 70 * i));
        }
        for (int i = 0; i < 0 + wave * dificulty / 5; i++) {
            enemies.add(new Enemy(this, plane, Constants.PLANE, Constants.PATH_PLANE(), 20 * (5 + (wave * 2 / 5)*dificulty) + 500 * i));
        }
        wave++;
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public void draw(Batch batch){
        verifyTouch();

        for(Turret turret : turrets){
            turret.draw(batch);
        }

        for(Enemy enemy : enemies){
            enemy.draw(batch);
            if(enemy.getDeathTime() > deathTime){
                enemies.removeValue(enemy, false);
            } else if (enemy.getX() > Gdx.graphics.getWidth()){
                enemies.removeValue(enemy, false);
                life--;
            }
        }


        if (isBuilding){
            drawCreateButtons(batch);
        } else if (isUpgrading > 0){
            if(isUpgrading < money) batch.draw(btnLevelUp[0], (lastTouch.x-1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
            else batch.draw(btnLevelUp[1], (lastTouch.x-1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
            batch.draw(btnSell, (lastTouch.x+1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
        }

    }

    private void drawCreateButtons(Batch batch) {

        if(money > 50 ) batch.draw(btnMachineGun[0], (lastTouch.x-1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
        else batch.draw(btnMachineGun[1], (lastTouch.x-1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);

        if(money > 100 ) batch.draw(btnAntiTank[0], (lastTouch.x)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
        else batch.draw(btnAntiTank[1], (lastTouch.x)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);

        if(money > 75 ) batch.draw(btnMissile[0], (lastTouch.x+1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
        else batch.draw(btnMissile[1], (lastTouch.x+1)*Constants.ESCALA_X, (lastTouch.y+1)*Constants.ESCALA_Y);
    }

    private void verifyTouch() {
        if (Gdx.input.justTouched()) {
            int x = (int) (Gdx.input.getX() / Constants.ESCALA_X),
                    y = (int) ((Gdx.graphics.getHeight() - Gdx.input.getY()) / Constants.ESCALA_Y);
           if( isUpgrading > 0 || isBuilding || ! path.contains(new Vector2(x, y), false)){
               Turret turret = isUpgrading > 0 ? getTurret((int)lastTouch.x, (int)lastTouch.y) : getTurret(x, y) ;

               if(turret == null  && isUpgrading <= 0){
                   if(isBuilding) {
                       isBuilding = false;
                       if (lastTouch.x - 1 == x && lastTouch.y + 1 == y) {
                           if( money >= 50 ){
                               turrets.add(new Turret(this, machineGun, Constants.MACHINEGUN, (int) (lastTouch.x * Constants.ESCALA_X), (int) (lastTouch.y * Constants.ESCALA_Y)));
                               money -= 50;
                           }
                           lastTouch.set(-1, -1);
                       } else if (lastTouch.x + 1 == x && lastTouch.y + 1 == y) {
                           if(money >= 75) {
                               turrets.add(new Turret(this, missiles, Constants.ANTIAIR, (int) (lastTouch.x * Constants.ESCALA_X), (int) (lastTouch.y * Constants.ESCALA_Y)));
                               money -= 75;
                           }
                           lastTouch.set(-1, -1);
                       } else if (lastTouch.x == x && lastTouch.y + 1 == y) {
                           if(money >= 100) {
                               turrets.add(new Turret(this, antiTank, Constants.ANTITANK, (int) (lastTouch.x * Constants.ESCALA_X), (int) (lastTouch.y * Constants.ESCALA_Y)));
                                money -= 100;
                           }
                           lastTouch.set(-1, -1);
                       }
                   } else {
                       lastTouch.set(x,y);
                       isBuilding = true;
                   }
               } else{
                   turret.select();
                   if(isUpgrading > 0){
                       isUpgrading = 0;
                       if (lastTouch.x - 1 == x && lastTouch.y + 1 == y) {
                           turret.levelUp();
                           lastTouch.set(-1, -1);
                       } else if (lastTouch.x + 1 == x && lastTouch.y + 1 == y) {
                           money += turret.getValue();
                           turrets.removeValue(turret, false);
                           lastTouch.set(-1, -1);
                       }
                       turret.unselect();
                   }else {
                       isUpgrading = turret.getUpgradeCost();
                       lastTouch.set(x,y);
                   }
               }
           } else {
               isBuilding = false;
               isUpgrading = 0;
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
            btnMachineGun[i].dispose();
            btnMissile[i].dispose();
            btnAntiTank[i].dispose();
            btnLevelUp[i].dispose();
        }

        btnSell.dispose();
    }

    public boolean isWinner() {
        return enemies.size <= 0;
    }

    public boolean isLoser() {
        return life <= 0;
    }

    public int getMoney(){ return money; }

    public int getLife() { return life; }

    public int getScore() { return score; }

    public void addScore() { score = (int) (score * 1.1); }

    public void addScore(int amount) { score += amount; }

    public void addMoney(int amount) { money += amount; }
}
