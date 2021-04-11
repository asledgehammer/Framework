@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package jab.sledgehammer.config

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * TODO: Document.
 *
 * @author Jab
 */
open class ConfigSection internal constructor(val name: String) {

    /**
     * TODO: Document.
     */
    var parent: ConfigSection? = null
        set(value) {
            if (value != null && value.isChildOf(this)) {
                throw CyclicDependencyException("Parent section is a child of the section.")
            }
            field = value
        }

    /**
     * TODO: Document.
     */
    val orphaned: Boolean get() = this !is ConfigFile && parent == null

    /**
     * TODO: Document.
     */
    val sections: Map<String, ConfigSection> get() = Collections.unmodifiableMap(HashMap(children))

    /**
     * TODO: Document.
     */
    internal val children = HashMap<String, ConfigSection>()

    /**
     * TODO: Document.
     */
    internal val fields = HashMap<String, Any>()

    fun getKeys(): List<String> {
        val list = ArrayList<String>()
        list.addAll(children.keys)
        list.addAll(fields.keys)
        return Collections.unmodifiableList(list)
    }

    /**
     * TODO: Document.
     */
    fun toMap(): Map<String, Any> {
        val map = HashMap<String, Any>()
        if (children.isNotEmpty()) for ((key, child) in children) map[key] = child.toMap()
        if (fields.isNotEmpty()) for ((key, value) in fields) map[key] = value
        return map
    }

    /**
     * TODO: Document.
     */
    fun contains(query: String): Boolean {
        if (query.contains(SEPARATOR)) {
            val split = query.split(SEPARATOR)
            val childQuery = split[0].toLowerCase().trim()
            val child = children[childQuery] ?: return false
            val rebuiltQuery = StringBuilder()
            for (index in 1..split.lastIndex) {
                rebuiltQuery.append(if (rebuiltQuery.isEmpty()) split[index] else "$SEPARATOR${split[index]}")
            }
            return child.contains(rebuiltQuery.toString())
        }
        return fields[query] != null || children[query] != null
    }

    /**
     * TODO: Document.
     */
    fun get(query: String): Any {
        if (query.contains(SEPARATOR)) {
            val split = query.split(SEPARATOR)
            val childQuery = split[0].toLowerCase().trim()
            val child = children[childQuery] ?: throw FieldNotFoundException(childQuery)
            val rebuiltQuery = StringBuilder()
            for (index in 1..split.lastIndex) {
                rebuiltQuery.append(if (rebuiltQuery.isEmpty()) split[index] else "$SEPARATOR${split[index]}")
            }
            return child.get(rebuiltQuery.toString())
        }
        return fields[query] ?: children[query] ?: throw FieldNotFoundException(query)
    }

    /**
     * TODO: Document.
     */
    fun set(query: String, value: Any?) {
        if (query.contains(SEPARATOR)) {
            val split = query.split(SEPARATOR)
            val childQuery = split[0].toLowerCase().trim()
            val child = children[childQuery] ?: throw FieldNotFoundException(childQuery)
            val rebuiltQuery = StringBuilder()
            for (index in 1..split.lastIndex) {
                rebuiltQuery.append(if (rebuiltQuery.isEmpty()) split[index] else "$SEPARATOR${split[index]}")
            }
            child.set(rebuiltQuery.toString(), value)
        } else {
            setLocal(query, value)
        }
    }

    /**
     * TODO: Document.
     */
    private fun setLocal(query: String, value: Any?) {
        val lQuery = query.toLowerCase().trim()
        if (value is ConfigSection) {
            if (isChildOf(value)) throw CyclicDependencyException("Cannot set parent as child.")
            children[lQuery] = value
        } else {
            if (value != null) {
                fields[lQuery] = value
            } else {
                children.remove(lQuery)
                fields.remove(lQuery)
            }
        }
    }

    /**
     * TODO: Document.
     */
    fun createSection(name: String): ConfigSection {
        require(children[name] == null) {
            "A section already exists with the name: $name"
        }
        val section = ConfigSection(name)
        section.parent = this
        children[name] = section
        return section
    }

    /**
     * TODO: Document.
     */
    fun getLongList(query: String): List<Long> {
        val rawList = getList(query)
        val list = ArrayList<Long>()
        for (next in rawList) list.add(next.toString().toLong())
        return list
    }

    /**
     * TODO: Document.
     */
    fun getDoubleList(query: String): List<Double> {
        val rawList = getList(query)
        val list = ArrayList<Double>()
        for (next in rawList) list.add(next.toString().toDouble())
        return list
    }

    /**
     * TODO: Document.
     */
    fun getIntList(query: String): List<Int> {
        val rawList = getList(query)
        val list = ArrayList<Int>()
        for (next in rawList) list.add(next.toString().toInt())
        return list
    }

    /**
     * TODO: Document.
     */
    fun getBooleanList(query: String): List<Boolean> {
        val rawList = getList(query)
        val list = ArrayList<Boolean>()
        for (next in rawList) list.add(next.toString().toBoolean())
        return list
    }

    /**
     * TODO: Document.
     */
    fun getStringList(query: String): List<String> {
        val rawList = getList(query)
        val list = ArrayList<String>()
        for (next in rawList) list.add(next.toString())
        return list
    }

    /**
     * TODO: Document.
     */
    fun isChildOf(parent: ConfigSection): Boolean {
        if (this.parent == null) return false
        if (this.parent == parent) return true
        return this.parent!!.isChildOf(parent)
    }

    /**
     * TODO: Document.
     */
    fun <E> isType(query: String, clazz: Class<E>): Boolean =
        contains(query) && clazz.isAssignableFrom(get(query)::class.java)

    /**
     * TODO: Document.
     */
    @Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")
    fun <E> get(query: String, clazz: Class<E>): E = get(query) as E

    /**
     * TODO: Document.
     */
    fun isSection(query: String): Boolean = isType(query, ConfigSection::class.java)

    /**
     * TODO: Document.
     */
    fun getSection(query: String): ConfigSection = get(query, ConfigSection::class.java)

    /**
     * TODO: Document.
     */
    fun isString(query: String): Boolean = isType(query, String::class.java)

    /**
     * TODO: Document.
     */
    fun getString(query: String): String = get(query).toString()

    /**
     * TODO: Document.
     */
    fun isBoolean(query: String): Boolean = isType(query, Boolean::class.java)

    /**
     * TODO: Document.
     */
    fun getBoolean(query: String): Boolean = get(query, Boolean::class.java)

    /**
     * TODO: Document.
     */
    fun isInt(query: String): Boolean = isType(query, Int::class.java)

    /**
     * TODO: Document.
     */
    fun getInt(query: String): Int = get(query, Int::class.java)

    /**
     * TODO: Document.
     */
    fun isDouble(query: String): Boolean = isType(query, Double::class.java)

    /**
     * TODO: Document.
     */
    fun getDouble(query: String): Double = get(query, Double::class.java)

    /**
     * TODO: Document.
     */
    fun isLong(query: String): Boolean = isType(query, Long::class.java)

    /**
     * TODO: Document.
     */
    fun getLong(query: String): Long = get(query, Long::class.java)

    /**
     * TODO: Document.
     */
    fun isList(query: String): Boolean = isType(query, List::class.java)

    /**
     * TODO: Document.
     */
    fun getList(query: String): List<*> = get(query, List::class.java)

    /**
     * TODO: Document.
     */
    internal open fun toJson(prefix: String = "", prefixHeader: String = ""): String {
        var value = "${prefixHeader}{\n"
        var actualPrefix = prefix
        val add = "  "

        fun tab() {
            actualPrefix += add
        }

        fun unTab() {
            actualPrefix = actualPrefix.substring(0, actualPrefix.length - add.length)
        }

        fun line(line: String) {
            value += "$actualPrefix$line\n"
        }

        tab()
        if (children.isNotEmpty()) {
            line("\"children\": {")
            tab()
            for ((key, child) in children) {
                line("\"$key\": ${child.toJson(actualPrefix)},")
            }
            unTab()
            line("},")
        } else {
            line("\"children\": {},")
        }
        if (fields.isNotEmpty()) {
            line("\"fields\": {")
            tab()
            for ((key, field) in fields) {
                val oVal = if (field !is Boolean && field !is Number) {
                    "\"$field\""
                } else {
                    "$field"
                }
                line("\"$key\": $oVal,")
            }
            unTab()
            line("},")
        } else {
            line("\"fields\": {},")
        }
        unTab()
        return "$value$actualPrefix}"
    }

    companion object {

        /**
         * TODO: Document.
         */
        const val SEPARATOR = '.'
    }
}
