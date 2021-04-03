package jab.langpack.core.objects.formatter

import jab.langpack.core.LangPack
import jab.langpack.core.objects.FieldProperties

/**
 * **PercentFormatter** Contains the the hard-code for the percent-syntax used in [LangPack].
 *
 * @author Jab
 */
class PercentFormatter : FieldFormatter {

    override fun getFields(string: String): List<FieldProperties> {
        val nextField = StringBuilder()
        val fields = ArrayList<FieldProperties>()
        var insideField = false

        for (next in string.chars()) {
            val c = next.toChar()
            if (c == '%') {
                if (insideField) {
                    if (nextField.isNotEmpty()) {
                        fields.add(getProperties(nextField.toString()))
                    }
                    insideField = false
                } else {
                    insideField = true
                    nextField.clear()
                }
            } else if (insideField) nextField.append(c)
        }
        return fields
    }

    override fun getRawFields(string: String): List<String> {
        val nextField = StringBuilder()
        val fields = ArrayList<String>()
        var insideField = false

        for (next in string.chars()) {
            val c = next.toChar()
            if (c == '%') {
                if (insideField) {
                    if (nextField.isNotEmpty()) fields.add(nextField.toString())
                    insideField = false
                } else {
                    insideField = true
                    nextField.clear()
                }
            } else if (insideField) nextField.append(c)
        }
        return fields
    }

    override fun getProperties(field: String): FieldProperties {
        return FieldProperties(
            "%$field%",
            strip(field),
            getPlaceholder(field),
            isResolve(field),
            isPackageScope(field)
        )
    }

    override fun getFieldCount(string: String): Int {
        val nextField = StringBuilder("")
        var insideField = false
        var count = 0
        for (next in string.chars()) {
            val c = next.toChar()
            if (c == '%') {
                if (insideField) {
                    if (nextField.isNotEmpty()) count++
                    insideField = false
                } else {
                    insideField = true
                    nextField.clear()
                }
            } else {
                if (insideField) nextField.append(c)
            }
        }
        return count
    }

    override fun getPlaceholder(field: String): String {
        if (field.isEmpty()) return ""
        return if (field.contains("=")) {
            field.substring(field.indexOf('=') + 1).replace("%", "")
        } else {
            field.replace("!", "").replace("~", "")
        }
    }

    override fun strip(field: String): String {

        // Invalid placeholder. Don't cause a runtime exception for substring.
        if (field.startsWith("=")) return ""

        var stripped = field.replace("%", "")
            .replace("!", "")
            .replace("~", "")

        // Remove placeholder
        val index = field.indexOf("=")
        if (index > -1) stripped = stripped.substring(0, stripped.indexOf("="))

        return stripped.toLowerCase()
    }

    override fun format(field: String): String = "%${field.toLowerCase()}%"

    override fun isField(string: String?): Boolean {
        if (string == null || string.isEmpty()) return false
        if (string.length > 2 && string.startsWith('%') && string.endsWith('%')) {
            // Check to make sure the string doesn't start with one variable and end with another.
            return getFieldCount(string) == 1
        }
        return false
    }

    override fun isResolve(field: String): Boolean = field.indexOf('!') > -1

    override fun isPackageScope(field: String): Boolean = field.indexOf('~') > -1
}
