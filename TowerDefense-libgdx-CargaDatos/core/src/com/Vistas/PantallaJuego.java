package com.Vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.models.Enemy;
import com.models.Turret;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {
     //TODO: 21/03/2017 ver si se puede cambiar el fondo por Actor y asi no hay que llamar al mtd.
            // ademas si lo declaramos touchable sería mas facil a la hora de poner las torretas.
    private static  Texture fondo;
    private static  Texture[] machineGun, antiAir, antiTank, plane, tank, people;
    private Array<Enemy> enemies;
    private Array<Turret> turrets;
    private Stage stage;
    private int life, money;
    
    public PantallaJuego(MainTowerDeffense _mtd, int fase) {
        super(_mtd);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        enemies = new Array<Enemy>();
        turrets = new Array<Turret>();
        initTexture(fase);
        Random rnd = new Random(System.currentTimeMillis());
        switch(fase){
            case Constants.FASE1:
                for(int i = 0; i < 10; i++){
                    enemies.add( new Enemy(this, people, Constants.PATH_FASE1, (int) rnd.nextInt(6)*1000, Constants.PEOPLE) );
                }
                for(int i = 0; i < 5; i++){
                    enemies.add( new Enemy(this, tank, Constants.PATH_FASE1, (int) (rnd.nextInt(5)+2)*1000, Constants.TANK) );
                }
                enemies.add( new Enemy(this, plane, Constants.PATH_FASE1, (int) (rnd.nextInt(4)+4)*1000, Constants.PLANE) );
            case Constants.FASE2:
                for(int i = 0; i < 20; i++){
                    enemies.add( new Enemy(this, people, Constants.PATH_FASE1, (int) (rnd.nextInt(6)+6)*1000, Constants.PEOPLE) );
                }
                for(int i = 0; i < 10; i++){
                    enemies.add( new Enemy(this, tank, Constants.PATH_FASE1, (int) (rnd.nextInt(5)+9)*1000, Constants.TANK) );
                }
                for(int i = 0; i < 2; i++){
                    enemies.add( new Enemy(this, plane, Constants.PATH_FASE1, (int) (rnd.nextInt(4)+10)*1000, Constants.PLANE) );
                }
            case Constants.FASE3:
                for(int i = 0; i < 30; i++){
                    enemies.add( new Enemy(this, people, Constants.PATH_FASE1, (int) (rnd.nextInt(6)+10)*1000, Constants.PEOPLE) );
                }
                for(int i = 0; i < 15; i++){
                    enemies.add( new Enemy(this, tank, Constants.PATH_FASE1, (int) (rnd.nextInt(5)+15)*1000, Constants.TANK) );
                }
                for(int i = 0; i < 5; i++){
                    enemies.add( new Enemy(this, plane, Constants.PATH_FASE1, (int) (rnd.nextInt(4)+18)*1000, Constants.PLANE) );
                }
            break;
        }
        life = 10; 
        //TODO: 21/03/2017 la vida tiene que depender de la dificultad elegida.
    }
    
    public static void initTextures(int fase){
        fondo = new Texture(Gdx.files.internal("Paths\\towerDefense_background"+ String.format("%02d", fase)+ ".png"));
        
        plane = new Texture[]{new Texture(Gdx.files.internal("Textures\\plane.png")), 
                              new Texture(Gdx.files.internal("Textures\\planeDeath.png")) };
        
        tank = new Texture[]{new Texture(Gdx.files.internal("Textures\\tank.png")), 
                             new Texture(Gdx.files.internal("Textures\\tankDeath.png")) };
        
        people = new Texture[]{new Texture(Gdx.files.internal("Textures\\people.png")), 
                               new Texture(Gdx.files.internal("Textures\\peopleDeath.png")) };
        
        antiAir = new Texture[]{new Texture(Gdx.files.internal("Textures\\antiAirLv1.png")), 
                               new Texture(Gdx.files.internal("Textures\\antiAirLv2.png")),
                               new Texture(Gdx.files.internal("Textures\\antiAirLv3.png"))};
        
        antiTank = new Texture[]{new Texture(Gdx.files.internal("Textures\\antiTankLv1.png")), 
                               new Texture(Gdx.files.internal("Textures\\antiTankLv2.png")),
                               new Texture(Gdx.files.internal("Textures\\antiTankLv3.png"))};
        
        machineGun = new Texture[]{new Texture(Gdx.files.internal("Textures\\machineGunLv1.png")), 
                               new Texture(Gdx.files.internal("Textures\\machineGunLv2.png")),
                               new Texture(Gdx.files.internal("Textures\\machineGunLv3.png"))};
    }
    
    @Override
    public void create(){
        for(Enemy enemy : enemies){
            stage.addActor(enemy);
        }
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        mtd.batch.setProjectionMatrix(cam.combined);

        //TODO: 21/03/2017 No se si hacer un mtd batch y un stag draw es eficiente o no
        mtd.batch.begin();
        mtd.batch.draw(fondo, 0, 0);
        mtd.batch.end();
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
        //TODO: 21/03/2017 controlar de alguna forma la destruccion de enemigos
            // mejor forma de hacer pasandole un objeto al enemigo y que llame a metodos de aqui.

    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        fondo.dispose();
        for(int i = 0; i < 3; i++){
            antiAir.get(i).dispose();
            antiTank.get(i).dispose();
            machineGun.get(i).dispose();
        }
        
        for(int i = 0; i < 2; i++){
            plane.get(i).dispose();
            tank.get(i).dispose();
            people.get(i).dispose();
        }
        
    }
}
