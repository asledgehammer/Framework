package jab.sledgehammer.config.test

import com.asledgehammer.config.ConfigFile
import org.junit.jupiter.api.Test
import java.io.File

class TestConfigSection {

    lateinit var config: ConfigFile

    @Test
    fun testList() {
        load()
        println("isList: ${config.isList("section.section2.list")}")
        val list = config.getStringList("section.section2.list")
        println("list: $list")
    }

    private fun load() {
        config = ConfigFile().load(File("src/test/resources/test.yml"))
    }

}