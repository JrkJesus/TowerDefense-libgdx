package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.utils.Array;
import com.models.Enemy;
import com.models.Turret;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {
     //TODO: 21/03/2017 ver si se puede cambiar el fondo por Actor y asi no hay que llamar al mtd.
            // ademas si lo declaramos touchable sería mas facil a la hora de poner las torretas.
    private Texture fondo;
    private Texture[] machineGun, antiAir, antiTank;
    private Button btnMachine, btnAntiAir, bntAntiTank;
    private 
    private Sprite plane, tank, people;
    private Array<Enemy> enemies;
    //private Array<Turret> turrets;
    private Stage stage;
    private int life, money;
    
    public PantallaJuego(MainTowerDeffense _mtd, int fase) {
        super(_mtd);
        stage = new Stage();
        enemies = new Array<Enemy>();
        turrets = new Array<Turret>();
        initTextures(fase);
        Random rnd = new Random(System.currentTimeMillis());
        switch(fase){
            case Constants.FASE3:
//                for(int i = 0; i < 10; i++){
//                    enemies.add( new Enemy(this, people, Constants.PATH_FASE1(), (int) rnd.nextInt(6)*100, Constants.PEOPLE) );
//                }
//                for(int i = 0; i < 5; i++){
//                    enemies.add( new Enemy(this, tank, Constants.PATH_FASE1(), (int) (rnd.nextInt(5)+2)*100, Constants.TANK) );
//                }
//                enemies.add( new Enemy(this, plane, Constants.PATH_FASE1(), (int) (rnd.nextInt(4)+4)*100, Constants.PLANE) );
            case Constants.FASE2:
//                for(int i = 0; i < 20; i++){
//                    enemies.add( new Enemy(this, people, Constants.PATH_FASE1(), (int) (rnd.nextInt(6)+6)*100, Constants.PEOPLE) );
//                }
//                for(int i = 0; i < 10; i++){
//                    enemies.add( new Enemy(this, tank, Constants.PATH_FASE1(), (int) (rnd.nextInt(5)+9)*100, Constants.TANK) );
//                }
//                for(int i = 0; i < 2; i++){
//                    enemies.add( new Enemy(this, plane, Constants.PATH_FASE1(), (int) (rnd.nextInt(4)+10)*100, Constants.PLANE) );
//                }
            case Constants.FASE1:
//                for(int i = 0; i < 30; i++){
//                    enemies.add( new Enemy(this, people, Constants.PATH_FASE1(), (int) (rnd.nextInt(6)+10)*100, Constants.PEOPLE) );
//                }
//                for(int i = 0; i < 15; i++){
//                    enemies.add( new Enemy(this, tank, Constants.PATH_FASE1(), (int) (rnd.nextInt(5)+15)*100, Constants.TANK) );
//                }
//                for(int i = 0; i < 5; i++){
//                    enemies.add( new Enemy(this, plane, Constants.PATH_FASE1(), (int) (rnd.nextInt(4)+18)*100, Constants.PLANE) );
//                }
                enemies.add(new Enemy(this, people, Constants.PATH_FASE1(), rnd.nextInt(6)*100, Constants.PEOPLE));
                enemies.add(new Enemy(this, tank, Constants.PATH_FASE1(), (rnd.nextInt(5)+2)*100, Constants.TANK));
                enemies.add(new Enemy(this, plane, Constants.PATH_PLANE(), (rnd.nextInt(4)+4)*100, Constants.PLANE));
            break;
        }
        life = 10;
        Gdx.input.setInputProcessor(stage);
        //TODO: 21/03/2017 la vida tiene que depender de la dificultad elegida.
    }
    
    public void initTextures(int fase){
        fondo = new Texture(Gdx.files.internal("Paths\\path_stage"+fase+".png"));
        
        plane = new Sprite( new Texture(Gdx.files.internal("Textures\\plane.png")) );
         //                     new Texture(Gdx.files.internal("Textures\\planeDead.png")) };
        
        tank = new Sprite( new Texture(Gdx.files.internal("Textures\\tank.png")) );
         //                    new Texture(Gdx.files.internal("Textures\\tankDead.png")) };
        
        people = new Sprite( new Texture(Gdx.files.internal("Textures\\people.png")) );
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
    }
    
    @Override
    public void render(float delta) {
//        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mtd.batch.begin();
        mtd.batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
         //turretsAttack();
        for( Enemy enemy : enemies)
             enemy.draw(mtd.batch);
        mtd.batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    
        if( life <= 0 ){
             //TODO: 27/03/2017 pantalla game over
        } else if ( enemies.size == 0 ) {
            //TODO: 27/03/2017 pantalla ganadora
        }

    }
    //TODO: 27/03/2017 sobrecargar el touchdown para que pinte las torres
     
    private turretsAttack(){
       for(Actor turret : stage.getActors()){
          if( !turret.isReloading() ){
               int i = 1;
               Enemy enemy = enemies.get(0);
               while( !enemy.isAttackable() && !turret.isReachable(enemy.getPosition()) )
                    enemy = enemies.get(i++);
               enemy.reciveDamage(turret.getAttack());
               turret.reload();
          }
       }
    }
    
    private void addMoney(int amount){
          money += amount;
    }
     
    public void addTurret(Turret turret){
         stage.addActor(turret);
    }
     
    public void eliminateTurret(Turret eliminate){
         //TODO: 27/03/2017 hacerlo con un while
         for(Actor turret : stage.getActors()){
              if(turret.equals(eliminate)){
                   //TODO: 27/03/2017 quitar del stage turret
                   addMoney( (int) (turret.getValue() / 3.5) );
              }
         }
    }
    
    public void eliminateEnemy(Enemy enemy){
       enemies.removeValue(enemy, false);
    }

    public void loseLife(Enemy enemy){
        eliminateEnemy(enemy);
        life--;
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        fondo.dispose();
        for(int i = 0; i < 3; i++){
            antiAir[i].dispose();
            antiTank[i].dispose();
            machineGun[i].dispose();
        }
        
        plane.getTexture().dispose();
        tank.getTexture().dispose();
        people.getTexture().dispose();
        
    }
}
