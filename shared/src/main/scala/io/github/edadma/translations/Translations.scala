package io.github.edadma.translations

import io.github.edadma.yaml
import io.github.edadma.yaml.YamlNode

import scala.language.postfixOps

private var languages: YamlNode = null
private var language: YamlNode = null

def setLanguages(l: String): Unit = languages = yaml.readFromString(l)

def setLanguage(lang: String): Unit = language = languages.map(lang)

implicit class Translations(val x: StringContext):
  val keyRegex = raw"[a-zA-Z]+(?:\.[a-zA-Z]+)*".r

  def lookup(key: String): Option[String] =
    def lookup: PartialFunction[(List[String], YamlNode), Option[String]] = {
      case (hd :: tl, n) =>
        n.map get hd match
          case None    => None
          case Some(v) => lookup(tl, v)
      case (Nil, n) => Some(n.string)
    }

    lookup(key split '.' toList, language)

  def t(args: Any*): String = keyRegex.replaceSomeIn(x.s(args: _*), m => lookup(m.matched))
