package jab.sledgehammer.config.test

import com.asledgehammer.cfg.CFGFile
import com.asledgehammer.cfg.YamlFile
import org.junit.jupiter.api.Test
import java.io.File

class TestConfigSection {

    lateinit var config: CFGFile

    @Test
    fun testList() {
        load()
        println("isList: ${config.isList("section.section2.list")}")
        val list = config.getStringList("section.section2.list")
        println("list: $list")
    }

    private fun load() {
        config = YamlFile(File("src/test/resources/test.yml")).read()
    }

}