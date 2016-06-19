
# Notes

- Don't forget!
  - [ ] `@charset` when creating a stylesheet???

- references
  - https://css-modules.github.io/webpack-demo/
  - http://nicolasgallagher.com/about-html-semantics-front-end-architecture/
  - https://developer.mozilla.org/en-US/docs/Web/CSS/Reference

- namespaces
  - classes (munged)
  - animations "@keyframes" (munged)
  - fonts
- munged names by default: just create whatever names you want
  - munging *should preserve* the names in the module definition
    - let's readers understand the symbology at least a bit
    - postpend a random symbol
    - perhaps not necessary in production (!)
      - just use the random symbol
- XXX global selectors: allows for unmunged-string based selector creation

- nested definition
  - does not create CSS nesting and run afoul of specificity issues
  - instead, creates nested *Scala* names which resolve to (concatenative,
    e.g., "a-b-c") final, munged names
    - like this
      .btn { ... }
      .uilist { ... }
      .uilist-item { ... }
    - not
      .btn { ... }
      .uilist { ... }
      .uilist a { ... }

- style-sets
  - sets of definitions, attribute->value mapping
  - *refinement* selectors
    - e.g.
        ```
        &.hover                             // :hover
        &.hover.visited                     // :hover:visited
        &.hover.not(_.visited)              // :hover:not(:visited)
        &.hover.not(_.firstChild.visited)   // :hover:not(:first-child:visited)
        &.nthChild(3).not(".debug")         // :nth-child(3):not(.debug)
        &.nthChild("3n+2")                  // :nth-child(3n+2)
        &.attr("custom-attr", "bla").hover  // [custom-attr="bla"]:hover
        &.attrExists("custom-attr").hover   // [custom-attr]:hover
        ```
    - but also e.g.
        @media screen
        @media screen, not handheld
        @media screen and (color), not handheld, (min-height:240px)
    - each attribute->value mapping may exist in the context of a constraint
      - e.g. Map[Constraint, Map[Attribute, Value]]
  - child selectors ("nesting") is disallowed (???)
    - reasons to use it (https://www.sitepoint.com/bem-smacss-advice-from-developers/)
      - "Usually when I’m nesting with BEM is when I want to target an
         element via HTML tag name, or to win a specificity battle when
         components styling overlaps."

- composes: import a set of constraints from another location
  - local constraints override composed constraints
  - multiple compositions override one another?

- autoprefixer---sort of
  - STRETCH GOAL at best
  - we control the AST so we should just emit conformant CSS
    - use https://github.com/bestiejs/platform.js/
  - scalacss on this (+100):
    "When you're generating CSS in the browser (ie. a Scala.JS project)
     you don't need to generate different prefixes because you know what
     rendering engine to target. The Scala.JS version of Defaults provides
     an implicit Env that uses platform.js (embedded) to determine which
     prefixes you need according to caniuse.com data."

- static generation?
  - pros:
    - no runtime (generation) cost
    - more "inspectable" CSS files
  - cons:
    - packaging two files is necessary
    - sophisticated server integration required for build/generation

- Not implemented
  - `attr()`
    - do this by hand if you want using `::=`, the types are meaningless
  - `calc()` 
    - do this is Scala
  - `counter()` function in content definitions
    - Just unsure how to do this nicely!
  - `@import`
    - Keep all your imported definitions Scala-local
  - `@namespace`
    - Better handled locally with Scala vars
  - `var()`
    - Better handled in Scala
  - `@supports`
    - Use case overlaps with built-in compatibility handling
  - Types
    - Frequency (not used in CSS today)
      - hz (becomes unit "Hz", with no space)
      - khz (becomes unit "kHz", with no space)
