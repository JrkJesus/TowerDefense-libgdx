package com.models;

/**
 * Created by jesus on 16/03/2017.
 *
 * Clase que guarda toda la información de las torretas.
 * @param radiusAttack radio de accion de la torreta
 * 
 */

public class Turret extends Actor{
  
  private float radiusAttack, speedAttack;
  private int dmg, initialCost, upgradeCost, level;
  private boolean reachFlying, optionsReveal = false;
  private Vector2 position;
  private Texture turretLv1, turretLv2, turretLv3, currentTurret;
  
  public Turret(Texture _turret, int type, int x, int y){
    setBounds(x,y, _turret.getWidth(), _turret.getHeight());
    position = new Vector2(x,y);
    //TODO: 21/03/2017 Ver si con el listener puede ejecutar alguna funcion desde el stage (player)
    addListener(new InputListener(){
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            ((MyActor)event.getTarget()).optionsReveal = !((MyActor)event.getTarget()).optionsReveal;
            return true;
        }
    });
    turretLv1 = _turret;
    currentTurret = turretLv1;
    level = 1;
    int heigh = Gdx.graphics.getHeigh();
    switch(type){
        case Constants.ANTIPLANE:
          radiusAttack = heigh * 0.25;
          dmg = 7;
          speedAttack = 1000;
          initalcost = 75;
          upgradeCost = new int[]{ 50, 100 };
        break;
        case Constants.ANTITANK:
          radiusAttack = heigh * 0.14;
          dmg = 6;
          speedAttack = 2000;
          initalcost = 100;
          upgradeCost = new int[]{ 75, 150 };
        break;
        case Constants.MACHINEGUN:
          radiusAttack = heigh * 0.18;
          dmg = 2;
          speedAttack = 500;
          initalcost = 50;
          upgradeCost = new int[]{ 25, 75 };
        break;
      }
  }
    
  public boolean isReachable(int x, int y){
      return Math.sqrt( (x-position.x)*(x-position.x) + (y-position.y)*(y-position.y) ) < radiusAttack;
  }
  
  public setUpgradeTexture(Texture lv2, Texture lv3){
    // Se puede hacer en un Array si vamos a querer que leven mas de 3 niveles
    turretLv2 = lv2;
    turretLv3 = lv3;
  }
  
  public void levelUp(){
    if( level < 3 ){ // si lo controlamos desde el panel se puede obviar.
      radiusAttack += radiusAttack*0.1;
      speedAttack -= speedAttack*0.05;
      dmg += dmg*0.1 > 0 ? dmg*0.1 : 1;
      level++;
      currentTurret = level == 2 ? turretLv2 : turretLv3;
    }
  }
  
  @Override
  public void draw(Batch batch, float alpha){
      batch.draw(currentTurret, position.x, position.y);
      if( optionReveal ){
        //TODO: 21/03/2017 Añadir los botones de acciones con sus propias opciones.
      }
  }
  
//TODO: 21/03/2017 No se si seria obligatorio hacer un @Overrride act() ya que desde el stage se invocaria el metodo act
  
}
