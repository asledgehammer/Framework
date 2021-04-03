package jab.langpack.core.processor

import jab.langpack.core.LangPack
import jab.langpack.core.Language
import jab.langpack.core.objects.LangArg
import jab.langpack.core.objects.LangGroup
import jab.langpack.core.objects.formatter.FieldFormatter
import jab.langpack.core.util.StringUtil.color

/**
 * **DefaultProcessor** implements the default field syntax for lang-packs.
 *
 *  @author Jab
 */
class DefaultProcessor(private val formatter: FieldFormatter) : LangProcessor {

    override fun process(
        string: String,
        pack: LangPack,
        lang: Language,
        context: LangGroup?,
        vararg args: LangArg,
    ): String {

        val fields = formatter.getFields(string)
        if (fields.isEmpty()) return color(string)

        var processed = string

        // Process all fields in the string.
        for (field in fields) {

            var found = false

            // Check the passed fields for the defined field.
            for (arg in args) {
                if (arg.key.equals(field.name, true)) {
                    found = true
                    processed = processed.replace(field.raw, arg.value.toString(), true)
                }
                break
            }

            // Check lang-pack for the defined field.
            if (!found) {
                val stringGot = pack.getString(field.name, lang, context, *args)
                processed = if (stringGot != null) {
                    processed.replace(field.raw, stringGot, true)
                } else {
                    processed.replace(field.raw, field.placeholder, true)
                }
            }
        }

        // Remove all field characters.
        for (field in fields) {
            processed = processed.replace(field.raw, field.placeholder, true)
        }

        return color(processed)
    }

    override fun process(string: String, vararg args: LangArg): String {

        val fields = formatter.getFields(string)
        if (fields.isEmpty()) return color(string)

        var processedString = string

        // Process all fields in the string.
        for (field in fields) {
            for (arg in args) {
                if (arg.key.equals(field.name, true)) {
                    val value = arg.value.toString()
                    processedString = processedString.replace(field.raw, value, true)
                }
                break
            }
        }

        // Remove all field characters.
        for (field in fields) {
            processedString = processedString.replace(field.raw, field.placeholder)
        }

        return color(processedString)
    }
}
