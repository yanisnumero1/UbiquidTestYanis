package fr.yanisnumero1.ubiquidtestyanis;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;

public class EvaluationTest {



    @Test
    public void addElements(){
        HashMap<Integer,String> map=new HashMap<>();
        map.put(0,"premier");
        map.put(1,"deuxieme");
        assertEquals("premier",map.get(0));
    }

}
