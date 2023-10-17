package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import com.alibaba.fastjson.JSONReader;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.JsonEnum;

public interface IStatsEnumTest<T extends IStatsEnum> {

    T createStatsEnum();

    @Test
    public default void testJSONFileEnum(){
        T statEnum = createStatsEnum();
        File f = new File(statEnum.getFilePath());
        if(!f.exists()){
            statEnum.writeDefaultEnumJson();
        }


        try(FileReader jsonFile = new FileReader(statEnum.getFilePath())){
            JSONReader parser = new JSONReader(jsonFile);
            JsonEnum json_enum = (JsonEnum) parser.readObject();

            assertTrue(json_enum.name.equals(statEnum.getClassName()));
            assertTrue(json_enum.enumIndex.length == statEnum.values().length);
            assertTrue(json_enum.enumName.length == statEnum.values().length);
            for (int i = 0; i < json_enum.enumIndex.length; i++) {
                assertTrue(json_enum.enumIndex[i] == statEnum.getIndexOf(statEnum.values()[i]));
                assertTrue(json_enum.enumName[i].equals(statEnum.values()[i].name()));
            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}