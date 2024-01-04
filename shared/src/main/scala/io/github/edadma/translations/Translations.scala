package io.github.edadma.translations

import io.github.edadma.yaml
import io.github.edadma.yaml.YamlNode

import scala.language.postfixOps

private var languages: YamlNode = null
private var language: YamlNode = null

def setLanguages(l: String): Unit = languages = yaml.readFromString(l)

def setLanguage(lang: String): Unit = language = languages.map(lang)

implicit class Translations(val x: StringContext):
  def lookup(key: String): String =
    def lookup: PartialFunction[(List[String], YamlNode), String] = {
      case (hd :: tl, n) => lookup(tl, n.map(hd))
      case (Nil, n)      => n.string
    }

    lookup(key split '.' toList, language)

  def t(args: Any*): String = lookup(x.s(args: _*))
