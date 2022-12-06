/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import commands.IllegalCommandException;
import java.awt.Point;
import model.common.Unit;
import model.extractors.Extractor;
import model.field.Field;
import model.field.FieldType;
import model.map.Map;
import model.player.Player;
import model.workers.Farmer;
import model.workers.Miner;
import model.workers.Woodcutter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sonrisa
 */
public class ExtractorsTest {
    
    private Map _map;
    private Player _player1;
    private Player _player2;
    private Field _goldField;
    private Field _grassField;
    private Field _forestField;
    
    public ExtractorsTest() {
        _map = new Map();
        _player1 = new Player(1, new Field(_map, new Point(3,4), FieldType.GRASS));
        _player2 = new Player(2, new Field(_map, new Point(27,26), FieldType.GRASS));
        
        _goldField = _map.getField(new Point(1,1));
        _forestField = _map.getField(new Point(7,1));
        _grassField = _map.getField(new Point(2,2));
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testFarmProducesFood() throws IllegalCommandException {
        Farmer.create(_grassField, _player1);
        
        Unit farmer = _map.getField(_grassField.getPos()).getUnits().get(0);
        farmer.setTimer(0);
        
        farmer.getActions().get(0).execute();
        
        Extractor farm = _map.getField(_grassField.getPos()).getExtractor();
        
        int foodBefore = _player1.getTreasury().getFood();
        
        farmer.setTimer(0);
        farm.extract();
        
        int foodAfter = _player1.getTreasury().getFood();
        
        assertTrue(foodBefore < foodAfter);
    }
    
    @Test
    public void testMineProducesGold() throws IllegalCommandException {
        Miner.create(_goldField, _player1);
        
        Unit miner = _map.getField(_goldField.getPos()).getUnits().get(0);
        miner.setTimer(0);
        
        miner.getActions().get(0).execute();
        
        Extractor mine = _map.getField(_goldField.getPos()).getExtractor();
        
        int goldBefore = _player1.getTreasury().getGold();
        
        miner.setTimer(0);
        mine.extract();
        
        int goldAfter = _player1.getTreasury().getGold();
        
        assertTrue(goldBefore < goldAfter);
    }

    @Test
    public void testHutProducesWood() throws IllegalCommandException {
        Woodcutter.create(_forestField, _player1);
        
        Unit woodCutter = _map.getField(_forestField.getPos()).getUnits().get(0);
        woodCutter.setTimer(0);
        
        woodCutter.getActions().get(0).execute();
        
        Extractor hut = _map.getField(_forestField.getPos()).getExtractor();
        
        int lumberBefore = _player1.getTreasury().getLumber();
        
        woodCutter.setTimer(0);
        hut.extract();
        
        int lumberAfter = _player1.getTreasury().getLumber();
        
        assertTrue(lumberBefore < lumberAfter);
    }
}
