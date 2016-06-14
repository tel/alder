package jspha.alder.vdom.syntax

/**
  * Created by tel on 5/31/16.
  */
class SvgAttrs[El, Mod](syntax: Internal[El, Mod]) {

  import syntax.makeJsValueMod

  def accentHeight(value: String): Mod = makeJsValueMod("accentHeight")(value)
  def accumulate(value: String): Mod = makeJsValueMod("accumulate")(value)
  def additive(value: String): Mod = makeJsValueMod("additive")(value)
  def alignmentBaseline(value: String): Mod = makeJsValueMod("alignmentBaseline")(value)
  def allowReorder(value: String): Mod = makeJsValueMod("allowReorder")(value)
  def alphabetic(value: String): Mod = makeJsValueMod("alphabetic")(value)
  def amplitude(value: String): Mod = makeJsValueMod("amplitude")(value)
  def arabicForm(value: String): Mod = makeJsValueMod("arabicForm")(value)
  def ascent(value: String): Mod = makeJsValueMod("ascent")(value)
  def attributeName(value: String): Mod = makeJsValueMod("attributeName")(value)
  def attributeType(value: String): Mod = makeJsValueMod("attributeType")(value)
  def autoReverse(value: String): Mod = makeJsValueMod("autoReverse")(value)
  def azimuth(value: String): Mod = makeJsValueMod("azimuth")(value)
  def baseFrequency(value: String): Mod = makeJsValueMod("baseFrequency")(value)
  def baseProfile(value: String): Mod = makeJsValueMod("baseProfile")(value)
  def baselineShift(value: String): Mod = makeJsValueMod("baselineShift")(value)
  def bbox(value: String): Mod = makeJsValueMod("bbox")(value)
  def begin(value: String): Mod = makeJsValueMod("begin")(value)
  def bias(value: String): Mod = makeJsValueMod("bias")(value)
  def by(value: String): Mod = makeJsValueMod("by")(value)
  def calcMode(value: String): Mod = makeJsValueMod("calcMode")(value)
  def capHeight(value: String): Mod = makeJsValueMod("capHeight")(value)
  def clip(value: String): Mod = makeJsValueMod("clip")(value)
  def clipPath(value: String): Mod = makeJsValueMod("clipPath")(value)
  def clipPathUnits(value: String): Mod = makeJsValueMod("clipPathUnits")(value)
  def clipRule(value: String): Mod = makeJsValueMod("clipRule")(value)
  def colorInterpolation(value: String): Mod = makeJsValueMod("colorInterpolation")(value)
  def colorInterpolationFilters(value: String): Mod = makeJsValueMod("colorInterpolationFilters")(value)
  def colorProfile(value: String): Mod = makeJsValueMod("colorProfile")(value)
  def colorRendering(value: String): Mod = makeJsValueMod("colorRendering")(value)
  def contentScriptType(value: String): Mod = makeJsValueMod("contentScriptType")(value)
  def contentStyleType(value: String): Mod = makeJsValueMod("contentStyleType")(value)
  def cursor(value: String): Mod = makeJsValueMod("cursor")(value)
  def cx(value: String): Mod = makeJsValueMod("cx")(value)
  def cy(value: String): Mod = makeJsValueMod("cy")(value)
  def d(value: String): Mod = makeJsValueMod("d")(value)
  def decelerate(value: String): Mod = makeJsValueMod("decelerate")(value)
  def descent(value: String): Mod = makeJsValueMod("descent")(value)
  def diffuseConstant(value: String): Mod = makeJsValueMod("diffuseConstant")(value)
  def direction(value: String): Mod = makeJsValueMod("direction")(value)
  def display(value: String): Mod = makeJsValueMod("display")(value)
  def divisor(value: String): Mod = makeJsValueMod("divisor")(value)
  def dominantBaseline(value: String): Mod = makeJsValueMod("dominantBaseline")(value)
  def dur(value: String): Mod = makeJsValueMod("dur")(value)
  def dx(value: String): Mod = makeJsValueMod("dx")(value)
  def dy(value: String): Mod = makeJsValueMod("dy")(value)
  def edgeMode(value: String): Mod = makeJsValueMod("edgeMode")(value)
  def elevation(value: String): Mod = makeJsValueMod("elevation")(value)
  def enableBackground(value: String): Mod = makeJsValueMod("enableBackground")(value)
  def end(value: String): Mod = makeJsValueMod("end")(value)
  def exponent(value: String): Mod = makeJsValueMod("exponent")(value)
  def externalResourcesRequired(value: String): Mod = makeJsValueMod("externalResourcesRequired")(value)
  def fill(value: String): Mod = makeJsValueMod("fill")(value)
  def fillOpacity(value: String): Mod = makeJsValueMod("fillOpacity")(value)
  def fillRule(value: String): Mod = makeJsValueMod("fillRule")(value)
  def filter(value: String): Mod = makeJsValueMod("filter")(value)
  def filterRes(value: String): Mod = makeJsValueMod("filterRes")(value)
  def filterUnits(value: String): Mod = makeJsValueMod("filterUnits")(value)
  def floodColor(value: String): Mod = makeJsValueMod("floodColor")(value)
  def floodOpacity(value: String): Mod = makeJsValueMod("floodOpacity")(value)
  def focusable(value: String): Mod = makeJsValueMod("focusable")(value)
  def fontFamily(value: String): Mod = makeJsValueMod("fontFamily")(value)
  def fontSize(value: String): Mod = makeJsValueMod("fontSize")(value)
  def fontSizeAdjust(value: String): Mod = makeJsValueMod("fontSizeAdjust")(value)
  def fontStretch(value: String): Mod = makeJsValueMod("fontStretch")(value)
  def fontStyle(value: String): Mod = makeJsValueMod("fontStyle")(value)
  def fontVariant(value: String): Mod = makeJsValueMod("fontVariant")(value)
  def fontWeight(value: String): Mod = makeJsValueMod("fontWeight")(value)
  def format(value: String): Mod = makeJsValueMod("format")(value)
  def from(value: String): Mod = makeJsValueMod("from")(value)
  def fx(value: String): Mod = makeJsValueMod("fx")(value)
  def fy(value: String): Mod = makeJsValueMod("fy")(value)
  def g1(value: String): Mod = makeJsValueMod("g1")(value)
  def g2(value: String): Mod = makeJsValueMod("g2")(value)
  def glyphName(value: String): Mod = makeJsValueMod("glyphName")(value)
  def glyphOrientationHorizontal(value: String): Mod = makeJsValueMod("glyphOrientationHorizontal")(value)
  def glyphOrientationVertical(value: String): Mod = makeJsValueMod("glyphOrientationVertical")(value)
  def glyphRef(value: String): Mod = makeJsValueMod("glyphRef")(value)
  def gradientTransform(value: String): Mod = makeJsValueMod("gradientTransform")(value)
  def gradientUnits(value: String): Mod = makeJsValueMod("gradientUnits")(value)
  def hanging(value: String): Mod = makeJsValueMod("hanging")(value)
  def horizAdvX(value: String): Mod = makeJsValueMod("horizAdvX")(value)
  def horizOriginX(value: String): Mod = makeJsValueMod("horizOriginX")(value)
  def ideographic(value: String): Mod = makeJsValueMod("ideographic")(value)
  def imageRendering(value: String): Mod = makeJsValueMod("imageRendering")(value)
  def in(value: String): Mod = makeJsValueMod("in")(value)
  def in2(value: String): Mod = makeJsValueMod("in2")(value)
  def intercept(value: String): Mod = makeJsValueMod("intercept")(value)
  def k(value: String): Mod = makeJsValueMod("k")(value)
  def k1(value: String): Mod = makeJsValueMod("k1")(value)
  def k2(value: String): Mod = makeJsValueMod("k2")(value)
  def k3(value: String): Mod = makeJsValueMod("k3")(value)
  def k4(value: String): Mod = makeJsValueMod("k4")(value)
  def kernelMatrix(value: String): Mod = makeJsValueMod("kernelMatrix")(value)
  def kernelUnitLength(value: String): Mod = makeJsValueMod("kernelUnitLength")(value)
  def kerning(value: String): Mod = makeJsValueMod("kerning")(value)
  def keyPoints(value: String): Mod = makeJsValueMod("keyPoints")(value)
  def keySplines(value: String): Mod = makeJsValueMod("keySplines")(value)
  def keyTimes(value: String): Mod = makeJsValueMod("keyTimes")(value)
  def lengthAdjust(value: String): Mod = makeJsValueMod("lengthAdjust")(value)
  def letterSpacing(value: String): Mod = makeJsValueMod("letterSpacing")(value)
  def lightingColor(value: String): Mod = makeJsValueMod("lightingColor")(value)
  def limitingConeAngle(value: String): Mod = makeJsValueMod("limitingConeAngle")(value)
  def local(value: String): Mod = makeJsValueMod("local")(value)
  def markerEnd(value: String): Mod = makeJsValueMod("markerEnd")(value)
  def markerHeight(value: String): Mod = makeJsValueMod("markerHeight")(value)
  def markerMid(value: String): Mod = makeJsValueMod("markerMid")(value)
  def markerStart(value: String): Mod = makeJsValueMod("markerStart")(value)
  def markerUnits(value: String): Mod = makeJsValueMod("markerUnits")(value)
  def markerWidth(value: String): Mod = makeJsValueMod("markerWidth")(value)
  def mask(value: String): Mod = makeJsValueMod("mask")(value)
  def maskContentUnits(value: String): Mod = makeJsValueMod("maskContentUnits")(value)
  def maskUnits(value: String): Mod = makeJsValueMod("maskUnits")(value)
  def mathematical(value: String): Mod = makeJsValueMod("mathematical")(value)
  def mode(value: String): Mod = makeJsValueMod("mode")(value)
  def numOctaves(value: String): Mod = makeJsValueMod("numOctaves")(value)
  def offset(value: String): Mod = makeJsValueMod("offset")(value)
  def opacity(value: String): Mod = makeJsValueMod("opacity")(value)
  def operator(value: String): Mod = makeJsValueMod("operator")(value)
  def order(value: String): Mod = makeJsValueMod("order")(value)
  def orient(value: String): Mod = makeJsValueMod("orient")(value)
  def orientation(value: String): Mod = makeJsValueMod("orientation")(value)
  def origin(value: String): Mod = makeJsValueMod("origin")(value)
  def overflow(value: String): Mod = makeJsValueMod("overflow")(value)
  def overlinePosition(value: String): Mod = makeJsValueMod("overlinePosition")(value)
  def overlineThickness(value: String): Mod = makeJsValueMod("overlineThickness")(value)
  def paintOrder(value: String): Mod = makeJsValueMod("paintOrder")(value)
  def panose1(value: String): Mod = makeJsValueMod("panose1")(value)
  def pathLength(value: String): Mod = makeJsValueMod("pathLength")(value)
  def patternContentUnits(value: String): Mod = makeJsValueMod("patternContentUnits")(value)
  def patternTransform(value: String): Mod = makeJsValueMod("patternTransform")(value)
  def patternUnits(value: String): Mod = makeJsValueMod("patternUnits")(value)
  def pointerEvents(value: String): Mod = makeJsValueMod("pointerEvents")(value)
  def points(value: String): Mod = makeJsValueMod("points")(value)
  def pointsAtX(value: String): Mod = makeJsValueMod("pointsAtX")(value)
  def pointsAtY(value: String): Mod = makeJsValueMod("pointsAtY")(value)
  def pointsAtZ(value: String): Mod = makeJsValueMod("pointsAtZ")(value)
  def preserveAlpha(value: String): Mod = makeJsValueMod("preserveAlpha")(value)
  def preserveAspectRatio(value: String): Mod = makeJsValueMod("preserveAspectRatio")(value)
  def primitiveUnits(value: String): Mod = makeJsValueMod("primitiveUnits")(value)
  def r(value: String): Mod = makeJsValueMod("r")(value)
  def radius(value: String): Mod = makeJsValueMod("radius")(value)
  def refX(value: String): Mod = makeJsValueMod("refX")(value)
  def refY(value: String): Mod = makeJsValueMod("refY")(value)
  def renderingIntent(value: String): Mod = makeJsValueMod("renderingIntent")(value)
  def repeatCount(value: String): Mod = makeJsValueMod("repeatCount")(value)
  def repeatDur(value: String): Mod = makeJsValueMod("repeatDur")(value)
  def requiredExtensions(value: String): Mod = makeJsValueMod("requiredExtensions")(value)
  def requiredFeatures(value: String): Mod = makeJsValueMod("requiredFeatures")(value)
  def restart(value: String): Mod = makeJsValueMod("restart")(value)
  def result(value: String): Mod = makeJsValueMod("result")(value)
  def rotate(value: String): Mod = makeJsValueMod("rotate")(value)
  def rx(value: String): Mod = makeJsValueMod("rx")(value)
  def ry(value: String): Mod = makeJsValueMod("ry")(value)
  def scale(value: String): Mod = makeJsValueMod("scale")(value)
  def seed(value: String): Mod = makeJsValueMod("seed")(value)
  def shapeRendering(value: String): Mod = makeJsValueMod("shapeRendering")(value)
  def slope(value: String): Mod = makeJsValueMod("slope")(value)
  def spacing(value: String): Mod = makeJsValueMod("spacing")(value)
  def specularConstant(value: String): Mod = makeJsValueMod("specularConstant")(value)
  def specularExponent(value: String): Mod = makeJsValueMod("specularExponent")(value)
  def speed(value: String): Mod = makeJsValueMod("speed")(value)
  def spreadMethod(value: String): Mod = makeJsValueMod("spreadMethod")(value)
  def startOffset(value: String): Mod = makeJsValueMod("startOffset")(value)
  def stdDeviation(value: String): Mod = makeJsValueMod("stdDeviation")(value)
  def stemh(value: String): Mod = makeJsValueMod("stemh")(value)
  def stemv(value: String): Mod = makeJsValueMod("stemv")(value)
  def stitchTiles(value: String): Mod = makeJsValueMod("stitchTiles")(value)
  def stopColor(value: String): Mod = makeJsValueMod("stopColor")(value)
  def stopOpacity(value: String): Mod = makeJsValueMod("stopOpacity")(value)
  def strikethroughPosition(value: String): Mod = makeJsValueMod("strikethroughPosition")(value)
  def strikethroughThickness(value: String): Mod = makeJsValueMod("strikethroughThickness")(value)
  def string(value: String): Mod = makeJsValueMod("string")(value)
  def stroke(value: String): Mod = makeJsValueMod("stroke")(value)
  def strokeDasharray(value: String): Mod = makeJsValueMod("strokeDasharray")(value)
  def strokeDashoffset(value: String): Mod = makeJsValueMod("strokeDashoffset")(value)
  def strokeLinecap(value: String): Mod = makeJsValueMod("strokeLinecap")(value)
  def strokeLinejoin(value: String): Mod = makeJsValueMod("strokeLinejoin")(value)
  def strokeMiterlimit(value: String): Mod = makeJsValueMod("strokeMiterlimit")(value)
  def strokeOpacity(value: String): Mod = makeJsValueMod("strokeOpacity")(value)
  def strokeWidth(value: String): Mod = makeJsValueMod("strokeWidth")(value)
  def surfaceScale(value: String): Mod = makeJsValueMod("surfaceScale")(value)
  def systemLanguage(value: String): Mod = makeJsValueMod("systemLanguage")(value)
  def tableValues(value: String): Mod = makeJsValueMod("tableValues")(value)
  def targetX(value: String): Mod = makeJsValueMod("targetX")(value)
  def targetY(value: String): Mod = makeJsValueMod("targetY")(value)
  def textAnchor(value: String): Mod = makeJsValueMod("textAnchor")(value)
  def textDecoration(value: String): Mod = makeJsValueMod("textDecoration")(value)
  def textLength(value: String): Mod = makeJsValueMod("textLength")(value)
  def textRendering(value: String): Mod = makeJsValueMod("textRendering")(value)
  def to(value: String): Mod = makeJsValueMod("to")(value)
  def transform(value: String): Mod = makeJsValueMod("transform")(value)
  def u1(value: String): Mod = makeJsValueMod("u1")(value)
  def u2(value: String): Mod = makeJsValueMod("u2")(value)
  def underlinePosition(value: String): Mod = makeJsValueMod("underlinePosition")(value)
  def underlineThickness(value: String): Mod = makeJsValueMod("underlineThickness")(value)
  def unicode(value: String): Mod = makeJsValueMod("unicode")(value)
  def unicodeBidi(value: String): Mod = makeJsValueMod("unicodeBidi")(value)
  def unicodeRange(value: String): Mod = makeJsValueMod("unicodeRange")(value)
  def unitsPerEm(value: String): Mod = makeJsValueMod("unitsPerEm")(value)
  def vAlphabetic(value: String): Mod = makeJsValueMod("vAlphabetic")(value)
  def vHanging(value: String): Mod = makeJsValueMod("vHanging")(value)
  def vIdeographic(value: String): Mod = makeJsValueMod("vIdeographic")(value)
  def vMathematical(value: String): Mod = makeJsValueMod("vMathematical")(value)
  def values(value: String): Mod = makeJsValueMod("values")(value)
  def vectorEffect(value: String): Mod = makeJsValueMod("vectorEffect")(value)
  def version(value: String): Mod = makeJsValueMod("version")(value)
  def vertAdvY(value: String): Mod = makeJsValueMod("vertAdvY")(value)
  def vertOriginX(value: String): Mod = makeJsValueMod("vertOriginX")(value)
  def vertOriginY(value: String): Mod = makeJsValueMod("vertOriginY")(value)
  def viewBox(value: String): Mod = makeJsValueMod("viewBox")(value)
  def viewTarget(value: String): Mod = makeJsValueMod("viewTarget")(value)
  def visibility(value: String): Mod = makeJsValueMod("visibility")(value)
  def widths(value: String): Mod = makeJsValueMod("widths")(value)
  def wordSpacing(value: String): Mod = makeJsValueMod("wordSpacing")(value)
  def writingMode(value: String): Mod = makeJsValueMod("writingMode")(value)
  def x(value: String): Mod = makeJsValueMod("x")(value)
  def x1(value: String): Mod = makeJsValueMod("x1")(value)
  def x2(value: String): Mod = makeJsValueMod("x2")(value)
  def xChannelSelector(value: String): Mod = makeJsValueMod("xChannelSelector")(value)
  def xHeight(value: String): Mod = makeJsValueMod("xHeight")(value)
  def xlinkActuate(value: String): Mod = makeJsValueMod("xlinkActuate")(value)
  def xlinkArcrole(value: String): Mod = makeJsValueMod("xlinkArcrole")(value)
  def xlinkHref(value: String): Mod = makeJsValueMod("xlinkHref")(value)
  def xlinkRole(value: String): Mod = makeJsValueMod("xlinkRole")(value)
  def xlinkShow(value: String): Mod = makeJsValueMod("xlinkShow")(value)
  def xlinkTitle(value: String): Mod = makeJsValueMod("xlinkTitle")(value)
  def xlinkType(value: String): Mod = makeJsValueMod("xlinkType")(value)
  def xmlBase(value: String): Mod = makeJsValueMod("xmlBase")(value)
  def xmlLang(value: String): Mod = makeJsValueMod("xmlLang")(value)
  def xmlSpace(value: String): Mod = makeJsValueMod("xmlSpace")(value)
  def y(value: String): Mod = makeJsValueMod("y")(value)
  def y1(value: String): Mod = makeJsValueMod("y1")(value)
  def y2(value: String): Mod = makeJsValueMod("y2")(value)
  def yChannelSelector(value: String): Mod = makeJsValueMod("yChannelSelector")(value)
  def z(value: String): Mod = makeJsValueMod("z")(value)
  def zoomAndPan(value: String): Mod = makeJsValueMod("zoomAndPan")(value)

}