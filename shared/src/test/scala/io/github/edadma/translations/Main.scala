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

  val x = "a"
  val y = "search"

  println(t"$x.$y")
