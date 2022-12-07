/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import commands.IllegalCommandException;
import java.awt.Point;
import model.common.Unit;
import model.field.Field;
import model.field.FieldType;
import model.map.Map;
import model.player.Player;
import model.trainers.Barracks;
import model.trainers.Castle;
import model.trainers.Trainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author favor
 */
public class TrainersTest {
    
    private Map _map;
    private Player _player1;
    private Field _field1;
    
    public TrainersTest() {
        _map = new Map();
        _player1 = new Player(1, new Field(_map, new Point(3,4), FieldType.GRASS));        
        _field1 = _map.getField(new Point(12,6));
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

    public void testCanTrainPeasant() throws IllegalCommandException{
        //Given
        Barracks.create(_field1, _player1);
        Barracks barrack = (Barracks)_field1.getTrainer();
        barrack.setTimer(0);
        
       //When
        barrack.trainPeasant();  
        Unit peasant = _map.getField(_field1.getPos()).getUnits().get(0);
       
        //Then
        assertNotNull(peasant);
    }
}
