@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.asledgehammer.cfg

import java.io.File

/**
 * **ConfigFile** wraps ConfigSection, parsing and writing YAML data.
 *
 * @author Jab
 */
open class CFGFile(val file: File) : CFGSection("")
