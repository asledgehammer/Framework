@file:Suppress("MemberVisibilityCanBePrivate")

package jab.sledgehammer.config

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml
import java.io.*

/**
 * TODO: Document.
 *
 * @author Jab
 */
class ConfigFile : ConfigSection("") {

    /**
     * TODO: Document.
     */
    fun load(file: File): ConfigFile = load(FileInputStream(file))

    /**
     * TODO: Document.
     */
    fun load(inputStream: InputStream): ConfigFile {
        @Suppress("UNCHECKED_CAST")
        load(yaml.load(inputStream) as Map<String, Any>)
        return this
    }

    /**
     * TODO: Document.
     */
    fun save(file: File, overwrite: Boolean = false) {
        if (!overwrite && file.exists()) return
        val bos = BufferedOutputStream(FileOutputStream(file))
        val writer = bos.writer()
        val string = yaml.dump(toMap())
        println(string)
        writer.write(string)
        writer.flush()
        writer.close()
        bos.flush()
        bos.close()
    }

    @Suppress("UNCHECKED_CAST")
    private fun load(map: Map<String, Any>) {
        fun recurse(name: String, map: Map<String, Any>): ConfigSection {
            val section = ConfigSection(name)
            for ((key, value) in map) {
                if (value is Map<*, *>) section.set(key, recurse(key, value as Map<String, Any>))
                else section.set(key, value)
            }
            return section
        }
        for ((key, value) in map) {
            if (value is Map<*, *>) set(key, recurse(key, value as Map<String, Any>))
            else set(key, value)
        }
    }

    companion object {

        /**
         * TODO: Document.
         */
        val yaml: Yaml

        init {
            val dumperOptions = DumperOptions()
            dumperOptions.defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
            dumperOptions.isAllowUnicode = true
            yaml = Yaml(dumperOptions)
        }
    }
}
