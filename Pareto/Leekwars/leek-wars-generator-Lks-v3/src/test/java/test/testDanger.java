package test;

import com.leekwars.generator.Generator;
import com.leekwars.generator.fight.Fight;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.julienStats.old.Danger;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class testDanger {
	private Generator generator;
	private Fight fight;

    @Before
	public void setUp() throws Exception {

		generator = new Generator();
		fight = new Fight(generator);
		
	}


    
}
