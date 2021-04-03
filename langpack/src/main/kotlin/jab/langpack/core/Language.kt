@file:Suppress("SpellCheckingInspection")

package jab.langpack.core

/**
 * The **Language** enum stores the constants for identification of languages supported by the lang-pack library.
 *
 * @author Jab
 *
 * @property abbreviation The abbreviation of the Language.
 * @property fallBack The fallback language to refer to.
 */
enum class Language(val abbreviation: String, private val fallBack: String? = null) {

    // Afrikaans
    AFRIKAANS_GENERIC("af"),
    AFRIKAANS("af_za", "af"),

    // Arabic
    ARABIC_GENERIC("ar"),
    ARABIC("ar_sa", "ar"),

    // Azerbaijani
    AZERBAIJANI_GENERIC("az"),
    AZERBAIJANI("az_az", "az"),

    // Bosnian
    BOSNIAN_GENERIC("bs"),
    BOSNIAN("bs_ba", "bs"),

    // Chinese
    CHINESE_GENERIC("zh"),
    CHINESE_SIMPLIFIED("zh_cn", "zh"),
    CHINESE_TRADITIONAL("zh_tw", "zh"),

    // Czech
    CZECH_GENERIC("cz"),
    CZECH("cs_cz", "cz"),

    // Danish
    DANISH_GENERIC("da"),
    DANISH("da_dk", "da"),

    // Dutch
    DUTCH_GENERIC("nl"),
    DUTCH("nl_nl", "nl"),
    DUTCH_FLEMISH("nl_be", "nl"),

    // English
    ENGLISH_GENERIC("en"),
    ENGLISH_UNITED_STATES("en_us", "en"),
    ENGLISH_AUSTRALIA("en_au", "en"),
    ENGLISH_CANADA("en_ca", "en"),
    ENGLISH_UNITED_KINGDOM("en_gb", "en"),
    ENGLISH_NEW_ZEALAND("en_nz", "en"),
    ENGLISH_SOUTH_AFRICA("en_za", "en"),
    ENGLISH_PIRATE_SPEAK("en_pt", "en"),
    ENGLISH_UPSIDE_DOWN("en_ud", "en"),
    ANGLISH("enp", "en"),
    SHAKESPEAREAN("enws", "en"),

    // Esperanto
    ESPERANTO_GENERIC("eo"),
    ESPERANTO("eo_uy", "eo"),

    // Estonian
    ESTONIAN_GENERIC("et"),
    ESTONIAN("et_ee", "et"),

    // Faroese
    FAROESE_GENERIC("fo"),
    FAROESE("fo_fo", "fo"),

    // Filipino
    FILIPINO_GENERIC("fil"),
    FILIPINO("fil_ph", "fil"),

    // Finnish
    FINNISH_GENERIC("fi"),
    FINNISH("fi_fi", "fi"),

    // French
    FRENCH_GENERIC("fr"),
    FRENCH("fr_fr", "fr"),
    FRENCH_CANADAIAN("fr_ca", "fr"),
    BRETON("br_fr", "fr"),

    // Frisian
    FRISIAN_GENERIC("fy"),
    FRISIAN("fy_nl", "fy"),

    // German
    GERMAN_GENERIC("de"),
    GERMAN("de_de", "de"),
    AUSTRIAN("de_at", "de"),
    SWISS("de_ch", "de"),
    EAST_FRANCONIAN("fra_de", "de"),
    LOW_GERMAN("nds_de", "de"),

    // Greek
    GREEK_GENERIC("gr"),
    GREEK("el_gr", "gr"),

    // Indonesian
    INDONESIAN_GENERIC("id"),
    INDONESIAN("id_id", "id"),

    // Irish
    IRISH_GENERIC("ga"),
    IRISH("ga_ie", "ga"),

    // Italian
    ITALIAN_GENERIC("it"),
    ITALIAN("it_it", "it"),

    // Japanese
    JAPANESE_GENERIC("jp"),
    JAPANESE("ja_jp", "jp"),

    // Kabyle
    KABYLE_GENERIC("kab"),
    KABYLE("kab_kab", "kab"),

    // Korean
    KOREAN_GENERIC("kr"),
    KOREAN_HANGUG("ko_kr", "kr"),

    // Latin
    LATIN_GENERIC("la"),
    LATIN("la_la", "la"),

    // Latvian
    LATVIAN_GENERIC("lv"),
    LATVIAN("lv_lv"),

    // Limburgish
    LIMBURGISH_GENERIC("li"),
    LIMBURGISH("li_li", "li"),

    // Lithuanian
    LITHUANIAN_GENERIC("lt"),
    LITHUANIAN("lt_lt", "lt"),

    // Macedonian
    MACEDONIAN_GENERIC("mk"),
    MACEDONIAN("mk_mk", "mk"),

    // Maltese
    MALTESE_GENERIC("mt"),
    MALTESE("mt_mt", "mt"),

    // Mongolian
    MONGOLIAN_GENERIC("mn"),
    MONGOLIAN("mn_mn", "mn"),

    // Persian
    PERSIAN_GENERIC("fa"),
    PERSIAN("fa_ir"),

    // Polish
    POLISH_GENERIC("pl"),
    POLISH("pl_pl", "pl"),

    // Portuguese
    PORTUGUESE_GENERIC("pt"),
    PORTUGUESE("pt_pt", "pt"),

    // Romanian
    ROMANIAN_GENERIC("ro"),
    ROMANIAN("ro_ro"),

    // Russian
    RUSSIAN_GENERIC("ru"),
    RUSSIAN("ru_ru", "ru"),
    BASHKIR("ba_ru", "ru"),
    BELARUSIAN("be_by", "ru"),
    BULGARIAN("bg_bg", "ru"),

    // Scottish
    SCOTTISH_GENERIC("gd"),
    SCOTTISH_GAELIC("gd_gb", "gd"),

    // Somali
    SOMALI_GENERIC("so"),
    SOMALI("so_so", "so"),

    // Spanish
    ESPANOL_GENERIC("es"),
    ESPANOL_ARGENTINA("es_ar", "es"),
    ESPANOL_CHILE("es_cl", "es"),
    ESPANOL_EQUADOR("es_ec", "es"),
    ESPANOL_ESPANA("es_es", "es"),
    ESPANOL_MEXICO("es_mx", "es"),
    ESPANOL_URUGUAY("es_uy", "es"),
    ESPANOL_VENEZUELA("es_ve", "es"),
    ASTURIAN("ast_es", "es"),
    BASQUE("eu_es", "es"),
    CATALAN("ca_es", "es"),

    // Thai
    THAI_GENERIC("th"),
    THAI("th_th", "th"),

    // Turkish
    TURKISH_GENERIC("tr"),
    TURKISH("tr_tr", "tr"),

    // Welsh
    WELSH_GENERIC("cy"),
    WELSH("cy_gb", "cy"),

    // Independent
    ANDALUSIAN("esan"),
    BAVARIAN("bar"),
    BRABANTIAN("brb"),
    GALICIAN("gl_es"),
    GOTHIC("got_de"),
    ALBANIAN("sq_al"),
    ALLGOVIAN_GERMAN("swg"),
    ARMENIAN("hy_am"),
    BRAZILIAN_PORTUGUESE("pt_br", "pt"),
    CORNISH("kw_gb"),
    CROATIAN("hr_hr"),
    ELFDALIAN("ovd"),
    GEORGIAN("ka_ge"),
    HAWAIIAN("haw_us"),
    HEBREW("he_il"),
    HINDI("hi_in"),
    HUNGARIAN("hu_hu"),
    ICELANDIC("is_is"),
    IDO("io_en"),
    IGBO("ig_ng"),
    INTERSLAVIC("isv"),
    KANNADA("kn_in"),
    KAZAKH("kk_kz"),
    KLINGON("til_aa"),
    KOLSCH_RIPUARIAN("ksh"),
    LOJBAN("jbo_en"),
    LOLCAT("lol_us"),
    LUXEMBOURGISH("lb_lu"),
    MALAY("ms_my"),
    MANX("gv_im"),
    MOHAWK("moh_ca"),
    MAON("mi_nz"),
    NORTHERN_SAMI("sme"),
    NORTHERN_BOKMAL("nb_no"),
    NORWEGIAN_NYNORSK("nn_no"),
    NUUCHAHNULTH("nuk"),
    OCCITAN("oc_fr"),
    OJIBWE("oj_ca"),
    QUENYA("qya_aa"),
    SICILIAN("scn"),
    SLOVAK("sk_sk"),
    SLOVENIAN("sl_si"),
    SERBIAN("sr_sp"),
    SWEDISH("sv_se"),
    UPPER_SAXON_GERMAN("sxu"),
    SILESIAN("szl"),
    TAMIL("ta_in"),
    TATAR("tt_ru"),
    TALOSSAN("tzl_tzl"),
    UKRAINIAN("uk_ua"),
    VALENCIAN("val_es"),
    VENETIAN("vec_it"),
    VIETNAMESE("vi_vn"),
    YIDDISH("yi_de"),
    YORUBA("yo_ng");

    override fun toString(): String = "Language(abbreviation='$abbreviation', fallBack=$fallBack)"

    /**
     * @return Returns the fallback language. If a fallback language is not defined, null is returned.
     */
    fun getFallback(): Language? {
        return if (fallBack != null) {
            getAbbrev(fallBack)
        } else {
            null
        }
    }

    companion object {

        /**
         * @param name The name of the Language.
         *
         * @return Returns the Language that identifies with the given name. If none identifies, null is returned.
         */
        fun get(name: String): Language? {
            for (lang in values()) {
                if (lang.name.equals(name, true)) {
                    return lang
                }
            }
            return null
        }

        /**
         * @param abbreviation The abbreviation of the Language.
         *
         * @return Returns the Language that identifies with the given abbreviation. If none identifies, null is returned.
         */
        fun getAbbrev(abbreviation: String): Language? {
            for (lang in values()) {
                if (lang.abbreviation.equals(abbreviation, true)) {
                    return lang
                }
            }
            return null
        }
    }
}
