package com.view;

import com.towerdeffense.MainTowerDeffense;
import com.util.XMLReader;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jesus on 16/04/2017.
 */

public class PantallaClasificacion extends PantallaBase {

    Integer[] scores;

    public PantallaClasificacion(MainTowerDeffense _mtd) {
        super(_mtd);
        Integer[] clasification = XMLReader.getScore();
        Arrays.sort(clasification, Collections.reverseOrder());
        int max = clasification.length < 5 ? clasification.length : 5;
        scores = Arrays.copyOf(clasification, max);
        XMLReader.setScore(scores);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
