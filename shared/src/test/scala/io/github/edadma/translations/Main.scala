package io.github.edadma.translations

import io.github.edadma.yaml
import io.github.edadma.yaml.YamlNode
import pprint.*

import scala.language.postfixOps

@main def run(): Unit =
  val languages =
    yaml
      .readFromString(
        """
          |en:
          |  a:
          |    search: Search
          |    books: Books
          |  b: asdf
          |ilo:
          |  search: Sapulen
          |  books: Libro
          |""".stripMargin,
      )
  var language: YamlNode = null

  def setLanguage(lang: String): Unit = language = languages.map(lang)

  setLanguage("en")

  def lookup(key: String): String =
    def lookup: PartialFunction[(List[String], YamlNode), String] = {
      case (hd :: tl, n) => lookup(tl, n.map(hd))
      case (Nil, n)      => n.string
    }

    lookup(key split '.' toList, language)

  pprintln(lookup("b"))

//  implicit class Translations(val x: StringContext) {
//
//    // Defining a method
//    def t(args: Any*): String = {
//
//      // Applying s-method
//      val result = x.s(args: _*)
//
//      // Applying reverse method
//      result.reverse
//    }
//  }
//
//  // Assigning values
//  val value = "GeeksforGeeks"
//
//  // Displays reverse of the
//  // stated string
//  println(t"$value")
