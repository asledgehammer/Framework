package jab.langpack.core.processor

import jab.langpack.core.LangPack
import jab.langpack.core.Language
import jab.langpack.core.objects.LangArg
import jab.langpack.core.objects.LangGroup

/**
 * **LangProcessor** implements syntax formats for use in [LangPack].
 *
 * @author Jab
 */
interface LangProcessor {

    /**
     * Processes a string, inserting arguments and fields set in the lang-pack.
     *
     * The scope will be at the package level.
     *
     * @param string The string to process.
     * @param pack The lang-pack instance.
     * @param lang The language context.
     * @param args (Optional) The arguments to process into the string.
     *
     * @return Returns the processed string.
     */
    fun process(string: String, pack: LangPack, lang: Language, vararg args: LangArg): String {
        return process(string, pack, lang, null, *args)
    }

    /**
     * Processes a string, inserting arguments and fields set in the lang-pack.
     *
     * @param string The string to process.
     * @param pack The lang-pack instance.
     * @param lang The language context.
     * @param context (Optional) The scope of the string.
     * @param args (Optional) The arguments to process into the string.
     *
     * @return Returns the processed string.
     */
    fun process(
        string: String,
        pack: LangPack,
        lang: Language,
        context: LangGroup? = null,
        vararg args: LangArg,
    ): String

    /**
     * Processes a string, inserting provided arguments.
     *
     * @param string The string to process.
     * @param args (Optional) The arguments to process into the string.
     *
     * @return Returns the processed string.
     */
    fun process(string: String, vararg args: LangArg): String
}
