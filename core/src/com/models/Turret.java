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

    public void shoot(ControllerGame control) {
        Array<Enemy> enemies = control.getEnemies();
        int n = enemies.size;
        int i = 0;
        while (i < n && !reachable(enemies.get(i++))) ;
        i--;
        if (n > 0 && (i < n - 1 || (i == n - 1 && reachable(enemies.get(i))))) {
            System.out.println("Shoot");
            enemies.get(i).receiveDamage(control, damage);
            this.setRotation((float) (Math.atan2(enemies.get(i).y() - y(), enemies.get(i).x() - x())) * MathUtils.radiansToDegrees);
            bullet = new Projectile(new Texture(Gdx.files.internal("Textures\\proyectil" + type + ".png")), new Vector2(getX(), getY()), new Vector2(enemies.get(i).getX(), enemies.get(i).getY()));
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
        int enemyX = (int) (enemy.getX() / Constants.GRID_RESIZE_X),
                enemyY = (int) (enemy.getY() / Constants.GRID_RESIZE_Y),
                x = (int) (getX() / Constants.GRID_RESIZE_X),
                y = (int) (getY() / Constants.GRID_RESIZE_Y);

        return (Math.abs(enemyX - x) <= rango &&
                Math.abs(enemyY - y) <= rango );
    }

    public boolean shootable() {
        return (bullet != null && bullet.isHundido());
    }

    @Override
    public void draw(Batch batch) {

        super.draw(batch);
//        if (isSelected) {
//        batch.draw(circuloRango, getX() - rango + (Constants.GRID_RESIZE_X / 2), getY() - rango + (Constants.GRID_RESIZE_Y / 2));
//        }
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
