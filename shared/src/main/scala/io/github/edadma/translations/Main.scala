package io.github.edadma.translations

import io.github.edadma.yaml
import io.github.edadma.yaml.YamlNode
import pprint.*

@main def run(): Unit =
  val languages =
    yaml
      .readFromString(
        """
          |en:
          |  search: Search
          |  books: Books
          |ilo:
          |  search: Sapulen
          |  books: Libro
          |""".stripMargin,
      )
  var language: Map[String, YamlNode] = languages.map("en").map

  pprintln(language("search").string)

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
