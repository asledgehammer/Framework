package jab.langpack.core.objects.complex

import jab.langpack.core.LangPack
import jab.langpack.core.Language
import jab.langpack.core.objects.LangArg
import jab.langpack.core.objects.LangGroup
import jab.langpack.core.objects.definition.LangDefinition
import jab.langpack.core.objects.formatter.FieldFormatter
import jab.sledgehammer.config.ConfigSection

/**
 * **Complex** serves as a wrapper for non-primitive objects utilized in [LangPack].
 * Complex objects construct and resolve as Defined by type **E**.
 *
 * @author Jab
 *
 * @param E The type to resolve when queried.
 */
interface Complex<E> {

    /**
     * Walks the complex object. This is a post-load operation where operations such as resolve-fields are processed.
     *
     * @param definition The instance of the definition walked.
     */
    fun walk(definition: LangDefinition<*>): Complex<E>

    /**
     * Process the complex object using the lang-pack's [LangProcessor].
     *
     * @param pack The lang-pack instance.
     * @param lang The language to query.
     * @param context (Optional) The context group to look up.
     * If not passed, the process will interpret look-ups on the package level.
     * @param args (Optional) Arguments to pass to the processor.
     *
     * @return Returns the processed result.
     */
    fun process(pack: LangPack, lang: Language, context: LangGroup?, vararg args: LangArg): E

    /**
     * @param formatter The formatter to process and identify fields defined throughout the complex object.
     *
     * @return Returns true if the complex object needs to process post-load.
     */
    fun needsWalk(formatter: FieldFormatter): Boolean

    /**
     * Process the complex object.
     *
     * @return Returns the processed result.
     */
    fun get(): E

    /**
     * **Loader** allows third-party installments of complex objects that require code extensions in
     * specific environments.
     *
     * @author Jab
     */
    @Suppress("MemberVisibilityCanBePrivate", "unused")
    interface Loader<E : Complex<*>> {

        /**
         * Load from a configured YAML section as a instance.
         *
         * @param cfg The YAML to read.
         *
         * @return Returns the loaded object.
         */
        fun load(cfg: ConfigSection): E
    }
}
