/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import commands.IllegalCommandException;
import java.awt.Point;
import model.common.Unit;
import model.field.Field;
import model.field.FieldType;
import model.map.Map;
import model.player.Player;
import model.warriors.Dragon;
import model.warriors.Warrior;
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
public class WarriorsTest {
    
    private Map _map;
    private Player _player1;
    private Player _player2;
    private Field _field1;
    private Field _field2;
    
    public WarriorsTest() {
        _map = new Map();
        _player1 = new Player(1, new Field(_map, new Point(3,4), FieldType.GRASS));
        _player2 = new Player(2, new Field(_map, new Point(27,26), FieldType.GRASS));
        
        _field1 = _map.getField(new Point(12,6));
        _field2 = _map.getField(new Point(12,7));
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
    public void testDragonsFight() throws IllegalCommandException{
        Dragon.create(_field1, _player1);
        Dragon.create(_field2, _player2);
        
        Warrior dragon1 = _field1.getWarriors().get(0);
        Unit dragon2 = _field2.getWarriors().get(0);
        
        dragon1.setTimer(0);
        
        dragon1.attack(dragon2);
        
        assertTrue(dragon2.getHealth() < dragon1.getHealth());
    }
}
