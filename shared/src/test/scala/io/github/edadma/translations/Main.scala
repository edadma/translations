package io.github.edadma.translations

@main def run(): Unit =
  setLanguages(
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
  setLanguage("en")

  val x = "zxcv"

  println(t"a.search b, $x a.books.")
