package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.util.Constants;
import com.towerdeffense.ControllerGame;

/**
 * Created by jesus on 16/03/2017.
 * <p>
 * Clase que guarda toda la información de las torretas.
 */

public class Turret extends Sprite {
    private Texture[] texturas;
    private int rango,
            buildCost,
            upgradeCost,
            nivel,
            damage,
            type;
    private boolean isSelected;
    private Pixmap pixmap;
    private Texture circuloRango;
    private Projectile bullet;

    public Turret(Texture[] t, int tipo, int x, int y) {
        super(t[0]);
        setPosition(x, y);
        setScale(Constants.ESCALA_X, Constants.ESCALA_Y);
        nivel = 1;
        texturas = t;
        int altura = Gdx.graphics.getHeight();
        switch (tipo) {
            case Constants.ANTIAIR:
                rango = 3;
                damage = 10;
//                attackSpeed = 2;
                buildCost = Constants.PLANE_COST;
                upgradeCost = (int) (buildCost * .75);
                break;
            case Constants.ANTITANK:
                rango = 1;
                damage = 30;
//                attackSpeed = 4;
                buildCost = Constants.TANK_COST;
                upgradeCost = (int) (buildCost * .75);
                break;
            case Constants.MACHINEGUN:
                rango = 2;
                damage = 10;
//                attackSpeed = 2;
                buildCost = Constants.MACHINE_COST;
                upgradeCost = (int) (buildCost * .75);
                break;
        }
        isSelected = false;
        pixmap = new Pixmap(rango * Constants.GRID_RESIZE_X * 2, rango * Constants.GRID_RESIZE_Y * 2, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2 - 1);
        circuloRango = new Texture(pixmap);
        type = tipo;
    }

    public void update(ControllerGame control) {
        if (shootable()) {
            shoot(control);
        } else {
            bullet.nextStep(Gdx.graphics.getDeltaTime());
        }
    }

    public void shoot(ControllerGame control) {
        Array<Enemy> enemies = control.getEnemies();
        int n = enemies.size;
        int i = 0;
        while (i < n && !reachable(enemies.get(i++))) ;
        i--;
        //
        if (n > 0 && (i < n - 1 || (i == n - 1 && reachable(enemies.get(i))))) {
            Enemy e = enemies.get(i);
            System.out.println("Shoot");
            e.receiveDamage(control, damage);
            this.setRotation((float) (Math.atan2(e.y() - y(), e.x() - x())) * MathUtils.radiansToDegrees);
            bullet = new Projectile(new Texture(Gdx.files.internal("Textures\\proyectil" + type + ".png")),
                    new Vector2(getX() + getTexture().getWidth() / 2, getY() + getTexture().getHeight() / 2),
                    new Vector2(e.getX() + e.getWidth() / 2, e.getY() + e.getHeight()/2));
        }
    }

    public int x() {
        return (int) getX() / Constants.GRID_RESIZE_X;
    }

    public int y() {
        return (int) getY() / Constants.GRID_RESIZE_Y;
    }

    private boolean reachable(Enemy enemy) {
        return enemy.getType() % type == 0
                && enemy.getLife() > 0
                && enemy.getX() >= 0
                && enemy.getX() < Gdx.graphics.getWidth()
                && isNeighbour(enemy, rango);
    }

    private boolean isNeighbour(Enemy enemy, int rango) {
        int enemyX = Math.round(enemy.getX() / Constants.GRID_RESIZE_X),
                enemyY = Math.round(enemy.getY() / Constants.GRID_RESIZE_Y),
                x = Math.round(getX() / Constants.GRID_RESIZE_X),
                y = Math.round(getY() / Constants.GRID_RESIZE_Y);
        return (Math.abs(enemyX - x) <= rango &&
                Math.abs(enemyY - y) <= rango);
    }

    public boolean shootable() {
        //(bullet != null && bullet.isHundido()) || (bullet==null)
        return (bullet == null || bullet.isHundido());
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public Projectile getBullet() {
        return bullet;
    }

    public void dispose() {
        getTexture().dispose();
        pixmap.dispose();
        circuloRango.dispose();
        if (bullet != null)
            bullet.dispose();
    }

    public int lvlUp() {
        setTexture(texturas[nivel++]);
        damage += (type == Constants.ANTITANK) ? 3 : 1;
        pixmap.dispose();
        circuloRango.dispose();
        pixmap = new Pixmap(rango * 2, rango * 2, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2 - 1);
        circuloRango = new Texture(pixmap);

        return upgradeCost + buildCost * (nivel - 2);
    }

    public int getLvl() {
        return nivel;
    }

    public int getValue() {
        int value = buildCost;
        if (nivel < 3) {
            value += upgradeCost + buildCost * (nivel - 2);
        }
        if (nivel < 2) {
            value += upgradeCost;
        }
        return (int) (value / 3.5f);
    }

    public int getUpgradeCost() {
        return upgradeCost + buildCost * (nivel - 2);
    }

}
