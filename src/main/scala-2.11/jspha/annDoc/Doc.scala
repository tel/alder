package jspha.annDoc

import java.io.StringWriter

import scala.annotation.tailrec
import scala.language.implicitConversions
import scala.{Char => SChar}

/**
  * Annotated pretty-printer based on a strict version of Wadler's "A
  * Prettier Printer" as modified by Daan Leijen and David Christiansen to
  * accommodate annotations which can be used to augment printing via the
  * `Simple[A]` type.
  *
  * Original resource is Wadler
  * <http://homepages.inf.ed.ac.uk/wadler/papers/prettier/prettier.pdf>
  *
  * Annotated implementation shamelessly translated from
  * <https://hackage.haskell.org/package/annotated-wl-pprint-0.7.0/docs/src/Text-PrettyPrint-Annotated-Leijen.html>
  *
  * All errors are my own.
  */
sealed trait Doc[+A] {

  import Doc._

  def map[B](f: A => B): Doc[B]

  /**
    * Annotate a document with a value
    */
  def annotate[AA >: A](ann: AA): Doc[AA] =
    Annotate(ann, this)

  /**
    * Annotate a document with a value
    */
  def ?[AA >: A](ann: AA): Doc[AA] =
    annotate(ann)

  /**
    * Flatten a documents annotation.
    */
  def noAnnotate: Doc[Nothing] =
    dsl.noAnnotate(this)

  /**
    * Join two documents without a space
    */
  def <>[AA >: A](other: Doc[AA]): Doc[AA] =
    dsl.beside(this, other)

  /**
    * Join two documents without a space
    */
  def beside[AA >: A](other: Doc[AA]): Doc[AA] =
    this <> other

  /**
    * Join two documents with a space
    */
  def <+>[AA >: A](other: Doc[AA]): Doc[AA] =
    this <> dsl.space <> other

  /**
    * Provide a space-like breakpoint for when a line doesn't fit.
    */
  def <||>[AA >: A](other: Doc[AA]): Doc[AA] =
    this <> dsl.softline <> other

  /**
    * Provide an Empty-like breakpoint for when a line doesn't fit.
    */
  def <|>[AA >: A](other: Doc[AA]): Doc[AA] =
    this <> dsl.softbreak <> other

  /**
    * Break a line without re-indenting.
    */
  def &[AA >: A](other: Doc[AA]): Doc[AA] =
    this <> dsl.line <> other

  /**
    * Break a line and maintain indentation.
    */
  def &->[AA >: A](other: Doc[AA]): Doc[AA] =
    this <> dsl.linebreak <> other

}

object Doc extends DocConstructorClasses with DocPrettyPrinter {

  def map[A, B](f: A => B)(doc: Doc[A]): Doc[B] =
    doc.map(f)

  object dsl
    extends CoreDSL
      with CharsetDSL
      with SurroundDSL
      with CatenationDSL
      with IndentDSL
      with ListsDSL
      with FillDSL

  private def spaces(amt: Int): String = {
    val writer = new StringWriter()
    for (_ <- Range(0, amt)) {
      writer.append(' ')
    }
    val result = writer.toString
    writer.close()
    result
  }

  trait CoreDSL {

    /**
      * | The `line` document advances to the next line and indents to the
      * current nesting level. Doc aument `line` behaves like `text(" ")`
      * if the line break is undone by 'group'.
      */
    val line: Doc[Nothing] =
      Line(false)

    /**
      * The `linebreak` document advances to the next line and indents to
      * the current nesting level. Document `linebreak` behaves like
      * `empty` if the line break is undone by `group`.
      */
    val linebreak: Doc[Nothing] =
      Line(true)

    /**
      * The document `softline` behaves like `space` if the resulting
      * output fits the page, otherwise it behaves like `line`.
      */
    val softline: Doc[Nothing] =
      group(line)

    /**
      * The document `softbreak` behaves like `empty` if the resulting
      * output fits the page, otherwise it behaves like `line`.
      */
    val softbreak: Doc[Nothing] =
      group(linebreak)

    /**
      * The empty document is, indeed, empty. Although `empty` has no
      * content, it does have a 'height' of 1 and behaves exactly like
      * `(text "")` (and is therefore not a unit of `<$>`).
      */
    val empty: Doc[Nothing] =
      Empty

    /**
      * | The document `char(c)` contains the literal character `c`. The
      * character shouldn't be a newline (`'\n'`), the function `line`
      * should be used for line breaks.
      */
    implicit def char(c: SChar): Doc[Nothing] = c match {
      case '\n' => line
      case _ => Char(c)
    }

    /**
      * The document `string(s)` concatenates all characters in `s`
      * using `line` for newline characters and `char` for all other
      * characters. It is used instead of 'text' whenever the text contains
      */
    implicit def string(s: String): Doc[Nothing] = {
      val fragments = s.split("\n")
      var first = true
      var result = empty
      fragments.foreach { frag =>
        if (first) {
          result = result <> text(frag)
          first = false
        }
        else
          result = result <> line <> text(frag)
      }
      result
    }

    /**
      * The document `text(s)` contains the literal string `s`. The
      * string shouldn't contain any newline (`'\n'`) characters. If the
      * string contains newline characters, the function 'string' should be
      * used.
      */
    def text(s: String): Doc[Nothing] = s match {
      case "" => empty
      case _ => Text(s.length, s)
    }

    def beside[A](l: Doc[A], r: Doc[A]): Doc[A] =
      Cat(l, r)

    /**
      * | The document `(nest i x)` renders document `x` with the current
      * indentation level increased by i (See also `hang`, `align` and
      * `indent`).
      *
      * nest 2 (text("hello") +/+ text("world")) +/+ text("!")
      *
      * outputs as:
      *
      * hello
      * world
      * !
      */
    def nest[A](amt: Int, doc: Doc[A]): Doc[A] =
      Nest(amt, doc)

    def column[A](run: Int => Doc[A]): Doc[A] =
      Column(run)

    def nesting[A](run: Int => Doc[A]): Doc[A] =
      Nesting(run)

    /**
      * The `group` combinator is used to specify alternative
      * layouts. The document `(group x)` undoes all line breaks in
      * document `x`. The resulting line is added to the current line if
      * that fits the page. Otherwise, the document `x` is rendered without
      * any changes.
      */
    def group[A](doc: Doc[A]): Doc[A] =
      Union(flatten(doc), doc)

    private def flatten[A](doc: Doc[A]): Doc[A] = doc match {
      case Cat(l, r) => Cat(flatten(l), flatten(r))
      case Nest(i, x) => Nest(i, flatten(x))
      case Line(tight) => if (tight) empty else Text(1, " ")
      case Union(x, y) => flatten(x)
      case Column(run) => Column(run andThen flatten)
      case Nesting(run) => Nesting(run andThen flatten)
      case _ => doc
    }

    def annotate[A](ann: A, doc: Doc[A]): Doc[A] =
      doc ? ann

    /**
      * Strip annotations from a document. This is useful for re-using the
      * textual formatting of some sub-document, but applying a different
      * high-level annotation.
      */
    def noAnnotate[A](doc: Doc[A]): Doc[Nothing] = doc match {
      case Cat(l, r) => Cat(noAnnotate(l), noAnnotate(r))
      case Nest(i, x) => Nest(i, noAnnotate(x))
      case Union(x, y) => Union(noAnnotate(x), noAnnotate(y))
      case Column(run) => Column(run andThen noAnnotate)
      case Nesting(run) => Nesting(run andThen noAnnotate)
      case Annotate(_, x) => noAnnotate(x)
      case _ => doc.asInstanceOf
    }

  }

  trait CharsetDSL { self: CoreDSL =>
    val lparen: Doc[Nothing] = '('
    val rparen: Doc[Nothing] = ')'
    val langle: Doc[Nothing] = '<'
    val rangle: Doc[Nothing] = '>'
    val lbrace: Doc[Nothing] = '{'
    val rbrace: Doc[Nothing] = '}'
    val lbracket: Doc[Nothing] = '['
    val rbracket: Doc[Nothing] = ']'
    val squote: Doc[Nothing] = '''
    val dquote: Doc[Nothing] = '"'
    val semi: Doc[Nothing] = ';'
    val colon: Doc[Nothing] = ':'
    val space: Doc[Nothing] = ' '
    val comma: Doc[Nothing] = ','
    val doc: Doc[Nothing] = '.'
    val backslash: Doc[Nothing] = '\\'
    val equals: Doc[Nothing] = '='
    val pipe: Doc[Nothing] = '|'

  }

  trait SurroundDSL {

    self: CoreDSL with CharsetDSL =>

    /**
      * Document `squotes(x)` encloses document `x` with single quotes `"'"`.
      */
    def squotes[A](doc: Doc[A]): Doc[A] =
      enclose(squote, squote)(doc)

    /**
      * Document `squotes(x)` encloses document `x` with double quotes `'"'`.
      */
    def dquotes[A](doc: Doc[A]): Doc[A] =
      enclose(dquote, dquote)(doc)

    /**
      * Document `braces(x)` encloses document `x` with braces `{...}`.
      */
    def braces[A](doc: Doc[A]): Doc[A] =
      enclose(lbrace, rbrace)(doc)

    /**
      * Document `parens(x)` encloses document `x` with braces `(...)`.
      */
    def parens[A](doc: Doc[A]): Doc[A] =
      enclose(lparen, rparen)(doc)

    /**
      * Document `angles(x)` encloses document `x` with angle brackets `<...>`.
      */
    def angles[A](doc: Doc[A]): Doc[A] =
      enclose(langle, rangle)(doc)

    /**
      * Document `brackets(x)` encloses document `x` with square brackets `[...]`.
      */
    def brackets[A](doc: Doc[A]): Doc[A] =
      enclose(lbracket, rbracket)(doc)

    /**
      * The document `enclose(l, r)(x)` encloses document `x` between
      * documents `l` and `r` using `<>`.
      */
    def enclose[A](left: Doc[A], right: Doc[A])(doc: Doc[A]): Doc[A] =
      left <> doc <> right

  }

  trait CatenationDSL {

    self: CoreDSL =>

    /**
      * The document `sep(xs)` concatenates all documents `xs` either
      * horizontally with `<+>`, if it fits the page, or vertically with
      * `+/+`.
      */
    def sep[A](docs: Doc[A]*): Doc[A] =
      group(vsep(docs: _*))

    /**
      * The document `fillSep(xs)` concatenates documents `xs`
      * horizontally with `<+>` as long as its fits the page, than
      * inserts a `line` and continues doing that for all documents in
      * `xs`.
      */
    def fillSep[A](docs: Doc[A]*): Doc[A] =
      docs.foldLeft[Doc[A]](empty)(_ <||> _)

    /**
      * The document `hsep(xs)` concatenates all documents `xs` horizontally
      * with `<+>`.
      */
    def hsep[A](docs: Doc[A]*): Doc[A] =
      docs.foldLeft[Doc[A]](empty)(_ <+> _)

    /**
      * The document `vsep(xs)` concatenates all documents `xs`
      * vertically with `+/+`. If a `group` undoes the line breaks
      * inserted by `vsep`, all documents are separated with a space.
      *
      * someText = map text (words ("text to lay out"))
      *
      * test     = text "some" <+> vsep someText
      *
      * This is layed out as:
      *
      * some text
      * to
      * lay
      * out
      *
      * The `align` combinator can be used to align the documents under
      * their first element
      *
      * test     = text "some" <+> align (vsep someText)
      *
      * Which is printed as:
      *
      * some text
      * to
      * lay
      * out
      */
    def vsep[A](docs: Doc[A]*): Doc[A] =
      docs.foldLeft[Doc[A]](empty)(_ & _)

    /**
      * The document `cat(xs)` concatenates all documents `xs` either
      * horizontally with `<>`, if it fits the page, or vertically with
      * `<\//>`.
      */
    def cat[A](docs: Doc[A]*): Doc[A] =
      group(vcat(docs: _*))

    /**
      * The document `fillCat(xs)` concatenates documents `xs`
      * horizontally with `<>` as long as its fits the page, than inserts
      * a `linebreak` and continues doing that for all documents in `xs`.
      */
    def fillCat[A](docs: Doc[A]*): Doc[A] =
      docs.foldLeft[Doc[A]](empty)(_ <|> _)

    /**
      * The document `hcat(xs)` concatenates all documents `xs` horizontally
      * with `<>`.
      */
    def hcat[A](docs: Doc[A]*): Doc[A] =
      docs.foldLeft[Doc[A]](empty)(_ <> _)

    /**
      * The document `vcat(xs)` concatenates all documents `xs`
      * vertically with `+//+`. If a 'group' undoes the line breaks
      * inserted by `vcat`, all documents are directly concatenated.
      */
    def vcat[A](docs: Doc[A]*): Doc[A] =
      docs.foldLeft[Doc[A]](empty)(_ &-> _)

  }

  trait IndentDSL {

    self: CoreDSL =>

    /**
      * The document `indent(i, x)` indents document `x` with `i` spaces.
      *
      * test  = indent 4 (fillSep (map text
      * (words "the indent combinator indents these words !")))
      *
      * Which lays out with a page width of 20 as:
      *
      * the indent
      * combinator
      * indents these
      * words !
      */
    def indent[A](amt: Int, doc: Doc[A]): Doc[A] =
      hang(amt, text(spaces(amt)) <> doc)

    /**
      * The hang combinator implements hanging indentation. The document
      * `hang(i, x)` renders document `x` with a nesting level set to the
      * current column plus `i`. The following example uses hanging
      * indentation for some text:
      *
      * test  = hang 4 (fillSep (map text
      * (words "the hang combinator indents these words !")))
      *
      * Which lays out on a page with a width of 20 characters as:
      *
      * the hang combinator
      * indents these
      * words !
      */
    def hang[A](amt: Int, doc: Doc[A]): Doc[A] =
      align(nest(amt, doc))

    /**
      * The document `align(x)` renders document `x` with the nesting
      * level set to the current column. It is used for example to
      * implement 'hang'.
      *
      * As an example, we will put a document right above another one,
      * regardless of the current nesting level:
      *
      * x $$ y  = align (x <$> y)
      *
      * test    = text "hi" <+> (text "nice" $$ text "world")
      *
      * which will be layed out as:
      *
      * hi nice
      * world
      */
    def align[A](doc: Doc[A]): Doc[A] =
      column { (k: Int) =>
        nesting { (i: Int) =>
          nest(k - i, doc)
        }
      }

  }

  trait ListsDSL {

    self: CoreDSL with CharsetDSL with IndentDSL with CatenationDSL =>

    /**
      * The document `list(xs...)` comma separates the documents `xs` and
      * encloses them in square brackets. The documents are rendered
      * horizontally if that fits the page. Otherwise they are aligned
      * vertically. All comma separators are put in front of the elements.
      */
    def list[A](docs: Doc[A]*): Doc[A] =
      encloseSep(lbracket, rbracket, comma)(docs)

    /**
      * The document `tupled(xs...)` comma separates the documents `xs` and
      * encloses them in parenthesis. The documents are rendered
      * horizontally if that fits the page. Otherwise they are aligned
      * vertically. All comma separators are put in front of the elements.
      */
    def tupled[A](docs: Doc[A]*): Doc[A] =
      encloseSep(lparen, rparen, comma)(docs)

    /**
      * The document `semiBraces(xs...)` separates the documents `xs` with
      * semi colons and encloses them in braces. The documents are rendered
      * horizontally if that fits the page. Otherwise they are aligned
      * vertically. All semi colons are put in front of the elements.
      */
    def semiBraces[A](docs: Doc[A]*): Doc[A] =
      encloseSep(lbrace, rbrace, semi)(docs)

    /**
      * | The document `encloseSep(l, r, sep)(xs)` concatenates the documents
      *
      * `xs` separated by `sep` and encloses the resulting document by `l`
      * and `r`. The documents are rendered horizontally if that fits the
      * page. Otherwise they are aligned vertically. All separators are put
      * in front of the elements. For example, the combinator 'list' can be
      * defined with `encloseSep`:
      *
      * def list(xs: Doc[A]*) = encloseSep(lbracket, rbracket, comma)(xs)
      * val test = text("list") +-+ list(Seq(10,200,3000).map(int))
      *
      * Which is layed out with a page width of 20 as:
      *
      * list [10,200,3000]
      *
      * But when the page width is 15, it is layed out as:
      *
      * list [10
      * ,200
      * ,3000]
      */
    def encloseSep[A](left: Doc[A], right: Doc[A], sep: Doc[A])(docs: Seq[Doc[A]]): Doc[A] =
      docs match {
        case Nil => left <> right
        case d :: Nil => left <> d <> right
        case d :: ds =>
          val lines = (left <> d) :: ds.map(sep <> _)
          align(cat(lines: _*) <> right)
      }
  }

  trait FillDSL {

    self: CoreDSL =>

    private def width[A](doc: Doc[A])(f: Int => Doc[A]): Doc[A] =
      column { (k1: Int) => doc <> column { (k2:Int) => f(k2 -k1) } }


    /**
      * | The document `fillBreak(i, x)` first renders document `x`. It
      * than appends `space`s until the width is equal to `i`. If the
      * width of `x` is already larger than `i`, the nesting level is
      * increased by `i` and a `line` is appended. When we redefine `ptype`
      * in the previous example to use `fillBreak`, we get a useful
      * variation of the previous output:
      *
      *     ptype (name,tp)
      *            = fillBreak 6 (text name) <+> text "::" <+> text tp
      *
      * The output will now be:
      *
      *     let empty  :: Doc a
      *         nest   :: Int -> Doc a -> Doc a
      *         linebreak
      *                :: Doc a
      */
    def fillBreak[A](fill: Int, doc: Doc[A]): Doc[A] =
      width(doc) { (w: Int) =>
        if (w > fill)
          nest(fill, linebreak)
        else
          text(spaces(fill - w))
      }

    /**
      * | The document `fill(i, x)` renders document `x`. It than appends
      * `space`s until the width is equal to `i`. If the width of `x` is
      * already larger, nothing is appended. This combinator is quite
      * useful in practice to output a list of bindings. The following
      * example demonstrates this.
      *
      *     types  = [("empty","Doc a")
      *              ,("nest","Int -> Doc a -> Doc a")
      *              ,("linebreak","Doc a")]
      *
      *     ptype (name,tp)
      *            = fill 6 (text name) <+> text "::" <+> text tp
      *
      *     test   = text "let" <+> align (vcat (map ptype types))
      *
      * Which is layed out as:
      *
      *     let empty  :: Doc a
      *         nest   :: Int -> Doc a -> Doc a
      *         linebreak :: Doc a
      */
    def fill[A](len: Int, doc: Doc[A]): Doc[A] =
      width(doc) { (w: Int) =>
        if (w >= len)
          empty
        else
          text (spaces(len - w))
      }

  }

}

trait DocConstructorClasses {
  case object Empty extends Doc[Nothing] {
    def map[B](f: Nothing => B) = asInstanceOf
  }

  // invariant: not \n
  case class Char(c: SChar) extends Doc[Nothing] {
    def map[B](f: Nothing => B) = asInstanceOf
  }

  // invariant: does not contain \n
  case class Text(len: Int, t: String) extends Doc[Nothing] {
    def map[B](f: Nothing => B) = asInstanceOf
  }

  // invariant: true => doesn't rep space
  case class Line(tight: Boolean) extends Doc[Nothing] {
    def map[B](f: Nothing => B) = asInstanceOf
  }

  case class Cat[A](l: Doc[A], r: Doc[A]) extends Doc[A] {
    def map[B](f: A => B) = copy(l = l.map(f), r = r.map(f))
  }

  case class Nest[A](amt: Int, doc: Doc[A]) extends Doc[A] {
    def map[B](f: A => B) = copy(doc = doc.map(f))
  }

  case class Union[A](l: Doc[A], r: Doc[A]) extends Doc[A] {
    def map[B](f: A => B) = copy(l = l.map(f), r = r.map(f))
  }

  case class Column[A](run: Int => Doc[A]) extends Doc[A] {
    def map[B](f: A => B) = Column { (i: Int) => run(i).map(f) }
  }

  case class Nesting[A](run: Int => Doc[A]) extends Doc[A] {
    def map[B](f: A => B) = Nesting { (i: Int) => run(i).map(f) }
  }

  case class Annotate[A](ann: A, doc: Doc[A]) extends Doc[A] {
    def map[B](f: A => B) = copy(ann = f(ann), doc = doc.map(f))
  }

  case object AnnotEnd extends Doc[Nothing] {
    def map[B](f: Nothing => B) = asInstanceOf
  }

}

trait DocPrettyPrinter {

  self: DocConstructorClasses =>

  /**
    * List of (indentation, doc) pairs
    */
  protected sealed trait Docs[+A]
  protected case object DNil extends Docs[Nothing]
  protected case class DCons[A](indented: Int, head: Doc[A], tail: Docs[A]) extends Docs[A]

  /**
    * Does a SimpleDoc fit in a given width?
    */
  @tailrec
  final def fits[A](width: Int, doc: SimpleDoc[A]): Boolean =
    if (width < 0) false
    else doc match {
      case SimpleDoc.Empty => true
      case SimpleDoc.Char(char, next) => fits(width - 1, next)
      case SimpleDoc.Text(len, str, next) => fits(width - len, next)
      case SimpleDoc.Line(indent, next) => true
      case SimpleDoc.AnnotStart(ann, next) => fits(width, next)
      case SimpleDoc.AnnotStop(next) => fits(width, next)
    }

  /**
    * Pick the first `SimpleDoc` if it fits and the second otherwise
    */
  def nicest[A](ribbonChars: Int,
                pageWidth: Int,
                indented: Int,
                colHere: Int,
                x: SimpleDoc[A],
                y: SimpleDoc[A]): SimpleDoc[A] = {
    val width = Math.min(pageWidth - colHere, ribbonChars - colHere + indented)
    if (fits(width, x)) x else y
  }

  /**
    * This is the default pretty printer which is used by 'show',
    * 'putDoc' and 'hPutDoc'. `renderPretty(x, ribbonfrac, width)` renders
    * document `x` with a page width of `width` and a ribbon width of
    * `ribbonfrac * width` characters. The ribbon width is the maximal
    * amount of non-indentation characters on a line. The parameter
    * `ribbonfrac` should be between `0.0` and `1.0`. If it is lower or
    * higher, the ribbon width will be 0 or `width` respectively.
    */
  def renderPretty[A](doc: Doc[A], ribbonFrac: Double = 0.4, pageWidth: Int = 80): SimpleDoc[A] = {
    val ribbonChars =
      Math.max(0, Math.min(pageWidth, (pageWidth * ribbonFrac).toInt))

    // TODO: Make this @tailrec
    // TODO: Or really, trampoline it.
    def best(indented: Int, colHere: Int, docs: Docs[A]): SimpleDoc[A] = {
      val n = indented
      val k = colHere

      docs match {
        case DNil => SimpleDoc.Empty
          
        // TODO: Why do we need an @unchcecked pragma; it's clearly exhaustive
        case DCons(i, head, tail) => (head: @unchecked) match {
          case Empty => best(n, k, tail)

          case Char(c) => SimpleDoc.Char(c, best(n, k + 1, tail))
          case Text(len, text) => SimpleDoc.Text(len, text, best(n, k + len, tail))
          case Line(_) => SimpleDoc.Line(i, best(i, i, tail))
          case Cat(x, y) => best(n, k, DCons(i, x, DCons(i, y, tail)))
          case Nest(amt, x) => best(n, k, DCons(i + amt, x, tail))

          case Column(run) => best(n, k, DCons(i, run(k), tail))
          case Nesting(run) => best(n, k, DCons(i, run(i), tail))

          case Union(x, y) =>
            nicest(
              ribbonChars, pageWidth, n, k,
              best(n, k, DCons(i, x, tail)),
              best(n, k, DCons(i, y, tail))
            )

          case Annotate(ann, x) =>
            SimpleDoc.AnnotStart(
              ann,
              best(
                n, k,
                DCons(i, x, DCons(i, AnnotEnd, tail)))
            )

          case AnnotEnd =>
            SimpleDoc.AnnotStop(best(n, k, tail))
        }
      }
    }

    best(0, 0, DCons[A](0, doc, DNil))
  }

}

