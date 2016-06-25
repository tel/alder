package jspha.sol.types

import jspha.sol.internal.CssValue

sealed trait TransformFunction {

  /**
    * All transform functions can be represented as matrices in `RP^3`.
    */
  def matrix3d: TransformFunction.Matrix3d

  /**
    * Compose two matrix transformations in order. A metaphor to `Function
    * .andThen` since matrices represent linear function spaces. Defined by
    * passing through a Matrix3d representation.
    */
  def andThen(tf: TransformFunction): TransformFunction =
    this.matrix3d andThen tf

  def compose(tf: TransformFunction): TransformFunction =
    tf.matrix3d andThen this

}

object TransformFunction {

  // We'll consider coordinates in R^2, R^3, and the projective spaces RP^2
  // and RP^3. For the standard spaces, coordinates are vertical vectors [x,
  // y], [x, y, z], [x, y, 1], and [x, y, z, 1] respectively.

  /**
    * Most general 3D transformation possible. Transforms all of the
    * coordinates simultaneously. If not d1 = d2 = d3 = 0 and d4 = 1 then
    * normalization will occur after transformation.
    *
    * [  a1  a2  a3  a4       [ x
    *    b1  b2  b3  b4         y
    *    c1  c2  c3  c4    *    z
    *    d1  d2  d3  d4 ]       1 ]
    *
    */
  case class Matrix3d(a1: Double, a2: Double, a3: Double, a4: Double,
                      b1: Double, b2: Double, b3: Double, b4: Double,
                      c1: Double, c2: Double, c3: Double, c4: Double,
                      d1: Double, d2: Double, d3: Double, d4: Double)
    extends TransformFunction {

    def matrix3d = this

    override def andThen(other: TransformFunction): TransformFunction = {

      val o: Matrix3d = other.matrix3d

      val _a1 = a1 * o.a1 + a2 * o.b1 + a3 * o.c1 + a4 * o.d1
      val _a2 = a1 * o.a2 + a2 * o.b2 + a3 * o.c2 + a4 * o.d2
      val _a3 = a1 * o.a3 + a2 * o.b3 + a3 * o.c3 + a4 * o.d3
      val _a4 = a1 * o.a4 + a2 * o.b4 + a3 * o.c4 + a4 * o.d4

      val _b1 = b1 * o.a1 + b2 * o.b1 + b3 * o.c1 + b4 * o.d1
      val _b2 = b1 * o.a2 + b2 * o.b2 + b3 * o.c2 + b4 * o.d2
      val _b3 = b1 * o.a3 + b2 * o.b3 + b3 * o.c3 + b4 * o.d3
      val _b4 = b1 * o.a4 + b2 * o.b4 + b3 * o.c4 + b4 * o.d4

      val _c1 = c1 * o.a1 + c2 * o.b1 + c3 * o.c1 + c4 * o.d1
      val _c2 = c1 * o.a2 + c2 * o.b2 + c3 * o.c2 + c4 * o.d2
      val _c3 = c1 * o.a3 + c2 * o.b3 + c3 * o.c3 + c4 * o.d3
      val _c4 = c1 * o.a4 + c2 * o.b4 + c3 * o.c4 + c4 * o.d4

      val _d1 = d1 * o.a1 + d2 * o.b1 + d3 * o.c1 + d4 * o.d1
      val _d2 = d1 * o.a2 + d2 * o.b2 + d3 * o.c2 + d4 * o.d2
      val _d3 = d1 * o.a3 + d2 * o.b3 + d3 * o.c3 + d4 * o.d3
      val _d4 = d1 * o.a4 + d2 * o.b4 + d3 * o.c4 + d4 * o.d4

      Matrix3d(
        _a1, _a2, _a3, _a4,
        _b1, _b2, _b3, _b4,
        _c1, _c2, _c3, _c4,
        _d1, _d2, _d3, _d4
      )
    }


  }

  /**
    * 2D affine transformations.
    *
    * [  a   b   tx        [ x
    *    c   d   ty     *    y
    *    0   0   1   ]       1 ]
    *
    */
  case class Matrix2d(a: Double, b: Double,
                      c: Double, d: Double,
                      tx: Double,
                      ty: Double)
    extends TransformFunction {

    def matrix3d: Matrix3d =
      Matrix3d(
        a,  b,  0,  0,
        c,  d,  0,  0,
        0,  0,  1,  0,
        tx, ty, 0,  1
      )

  }

  /**
    * The perspective() CSS function defines the distance between the z=0 plane and the user in order to give to the 3D-positioned element some perspective. Each 3D element with z>0 becomes larger; each 3D-element with z<0 becomes smaller. The strength of the effect is determined by the value of this property.
    */
  case class Perspective(l: Double)
    extends TransformFunction {

    def matrix3d: Matrix3d = {
      val d = -1/l
      Matrix3d(
        1,  0,  0,  0,
        0,  1,  0,  0,
        0,  0,  1,  0,
        0,  0,  d,  1
      )
    }

  }

  /**
    * The rotate() CSS function defines a transformation that moves the element around a fixed point (as specified by the transform-origin property) without deforming it. The amount of movement is defined by the specified angle; if positive, the movement will be clockwise, if negative, it will be counter-clockwise. A rotation by 180° is called point reflection.
    */
  case class Rotate(a: Angle)
    extends TransformFunction {

    def matrix3d: Matrix3d = {
      val cosa = a.cos
      val sina = a.sin
      Matrix3d(
        cosa,  -sina,  0,  0,
        sina,   cosa,  0,  0,
        0,      0,     1,  0,
        0,      0,     0,  1
      )
    }

  }

  /**
    * The rotate3d()CSS function defines a transformation that moves the element around a fixed axis without deforming it. The amount of movement is defined by the specified angle; if positive, the movement will be clockwise, if negative, it will be counter-clockwise.
    *
    * In the 3D space, rotations have three degrees of liberty, describing an axis of rotation. The axis of rotation is defined by an [x, y, z] vector and pass by the origin (as defined by the transform-origin CSS property. If the vector is not normalized, that is the sum of the square of its three coordinates is not 1, it will be normalized internally. A non-normalizable vector, like the null vector, [0, 0, 0], will cause the rotation not to be applied, without invaliding the whole CSS property.
    */
  case class Rotate3d(x: Double, y: Double, z: Double, a: Angle)
    extends TransformFunction {

    def matrix3d: Matrix3d = {

      val sina = a.sin
      val ncosa = 1 - a.cos

      val a1 =  1 + ncosa * (x * x - 1)
      val a2 =  z *  sina + x * y * ncosa
      val a3 = -y *  sina + x * z * ncosa

      val b1 = -z *  sina + x * y * ncosa
      val b2 =  1 + ncosa * (y * y - 1)
      val b3 =  x *  sina + y * z * ncosa

      val c1 =  y *  sina + x * z * ncosa
      val c2 = -x *  sina + y * z * ncosa
      val c3 =  1 + ncosa * (z * z - 1)

      Matrix3d(
        a1, a2, a3, 0,
        b1, b2, b3, 0,
        c1, c2, c3, 0,
        0,  0,  0,  1
      )
    }
  }

  /**
    * The rotateX()CSS function defines a transformation that moves the element around the abscissa without deforming it. The amount of movement is defined by the specified angle; if positive, the movement will be clockwise, if negative, it will be counter-clockwise.
    * The axis of rotation passes by the origin, defined by transform-origin CSS property.
    */
  case class RotateX(a: Angle)
    extends TransformFunction {
    def matrix3d = Rotate3d(1, 0, 0, a).matrix3d
  }

  /**
    * The rotateY()CSS function defines a transformation that moves the element around the ordinate without deforming it. The amount of movement is defined by the specified angle; if positive, the movement will be clockwise, if negative, it will be counter-clockwise.
    * The axis of rotation passes by the origin, defined by transform-origin CSS property.
    */
  case class RotateY(a: Angle)
    extends TransformFunction {
    def matrix3d = Rotate3d(0, 1, 0, a).matrix3d
  }

  /**
    * The rotateZ()CSS function defines a transformation that moves the element around the z-axis without deforming it. The amount of movement is defined by the specified angle; if positive, the movement will be clockwise, if negative, it will be counter-clockwise.
    * The axis of rotation passes by the origin, defined by transform-origin CSS property.
    */
  case class RotateZ(a: Angle)
    extends TransformFunction {
    def matrix3d = Rotate3d(0, 0, 1, a).matrix3d
  }

  /**
    * The scale() CSS function modifies the size of the element. It can either augment or decrease its size and as the amount of scaling is defined by a vector, it can do so more in one direction than in another one.
    * This transformation is characterized by a vector whose coordinates define how much scaling is done in each direction. If both coordinates of the vector are equal, the scaling is uniform, or isotropic, and the shape of the element is preserved. In that case, the scaling function defines a homothetic transformation.
    * When outside the [-1, 1] range, the scaling enlarges the element in the direction of the coordinate; when inside the range, it shrinks the element in that direction. When equal to 1 it does nothing and when negative it performs a point reflection and the size modification.
    */
  case class Scale(sx: Double, sy: Double)
    extends TransformFunction {
    def matrix3d = Matrix3d(
      sx, 0,  0, 0,
      0,  sy, 0, 0,
      0,  0,  1, 0,
      0,  0,  0, 1
    )
  }

  /**
    * The scale3d() CSS function modifies the size of an element. Because the amount of scaling is defined by a vector, it can resize different dimensions at different scales.
    * This transformation is characterized by a vector whose coordinates define how much scaling is done in each direction. If all three coordinates of the vector are equal, the scaling is uniform, or isotropic, and the shape of the element is preserved. In that case, the scaling function defines a homothetic transformation.
    * When outside the [-1, 1] range, the scaling enlarges the element in the direction of the coordinate; when inside the range, it shrinks the element in that direction. When equal to 1 it does nothing and when negative it performs a point reflection and the size modification.
    */
  case class Scale3d(sx: Double, sy: Double, sz: Double)
    extends TransformFunction {
    def matrix3d = Matrix3d(
      sx, 0,  0,  0,
      0,  sy, 0,  0,
      0,  0,  sz, 0,
      0,  0,  0,  1
    )
  }

  /**
    * The scaleX() CSS function modifies the abscissa of each element point by a constant factor, except if this scale factor is 1, in which case the function is the identity transform. The scaling is not isotropic and the angles of the element are not conserved.
    */
  case class ScaleX(s: Double)
    extends TransformFunction {
    def matrix3d = Scale(s, 1).matrix3d
  }

  /**
    * The scaleY() CSS function modifies the ordinate of each element point by a constant factor except if this scale factor is 1, in which case the function is the identity transform. The scaling is not isotropic and the angles of the element are not conserved.
    */
  case class ScaleY(s: Double)
    extends TransformFunction {
    def matrix3d = Scale(1, s).matrix3d
  }

  /**
    * The scaleZ() CSS function modifies the z-coordinate of each element point by a constant factor, except if this scale factor is 1, in which case the function is the identity transform. The scaling is not isotropic and the angles of the element are not conserved.
    */
  case class ScaleZ(s: Double)
    extends TransformFunction {
    def matrix3d = Scale3d(1, 1, s).matrix3d
  }

  /**
    * The skew() CSS function is a shear mapping, or transvection, distorting each point of an element by a certain angle in each direction. It is done by increasing each coordinate by a value proportionate to the specified angle and to the distance to the origin. The more far from the origin, the more away the point is, the greater will be the value added to it.
    */
  case class Skew(ax: Angle, ay: Angle)
    extends TransformFunction {
    def matrix3d = Matrix3d(
      1,      ax.tan, 0, 0,
      ay.tan, 1,      0, 0,
      0,      0,      1, 0,
      0,      0,      0, 1
    )
  }

  case class SkewX(a: Angle)
    extends TransformFunction {
    def matrix3d = Skew(a, Angle.rad(0)).matrix3d
  }

  case class SkewY(a: Angle)
    extends TransformFunction {
    def matrix3d = Skew(Angle.rad(0), a).matrix3d
  }

  /**
    * The translate() CSS function moves the position of the element on the plane. This transformation is characterized by a vector whose coordinates define how much it moves in each direction.
    */
  case class Translate(tx: Double, ty: Double)
    extends TransformFunction {
    def matrix3d = Translate3d(tx, ty, 0).matrix3d
  }

  /**
    * The translate3d() CSS function moves the position of the element in the 3D space. This transformation is characterized by a 3-dimension vector whose coordinates define how much it moves in each direction.
    */
  case class Translate3d(tx: Double, ty: Double, tz: Double)
    extends TransformFunction {
    def matrix3d = Matrix3d(
      1, 0, 0, tx,
      0, 1, 0, ty,
      0, 0, 1, tz,
      0, 0, 0, 1
    )
  }

  case class TranslateX(t: Double)
    extends TransformFunction {
    def matrix3d = Translate3d(t, 0, 0).matrix3d
  }

  case class TranslateY(t: Double)
    extends TransformFunction {
    def matrix3d = Translate3d(0, t, 0).matrix3d
  }

  case class TranslateZ(t: Double)
    extends TransformFunction {
    def matrix3d = Translate3d(0, 0, t).matrix3d
  }

  implicit val tfIsCssValue = new CssValue[TransformFunction] {
    def cssRepr(tf: TransformFunction) = tf match {
      case Matrix3d(a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4) =>
        s"matrix3d($a1, $a2, $a3, $a4, $b1, $b2, $b3, $b4, $c1, $c2, $c3, $c3, $d1, $d2, $d3, $d4)"
      case Matrix2d(a, b, c, d, tx, ty) =>
        s"matrix($a, $b, $c, $d, $tx, $ty)"
      case Perspective(l) =>
        s"perspective($l)"
      case Rotate(a) =>
        s"rotate(${CssValue.of(a)})"
      case Rotate3d(x, y, z, a) =>
        s"rotate3d($x, $y, $z, ${CssValue.of(a)})"
      case RotateX(a) =>
        s"rotateX(${CssValue.of(a)})"
      case RotateY(a) =>
        s"rotateY(${CssValue.of(a)})"
      case RotateZ(a) =>
        s"rotateZ(${CssValue.of(a)})"
      case Scale(sx, sy) =>
        s"scale($sx, $sy)"
      case Scale3d(sx, sy, sz) =>
        s"scale3d($sx, $sy, $sz)"
      case ScaleX(s) =>
        s"scaleX($s)"
      case ScaleY(s) =>
        s"scaleY($s)"
      case ScaleZ(s) =>
        s"scaleZ($s)"
      case Skew(ax, ay) =>
        s"skew(${CssValue.of(ax)}, ${CssValue.of(ay)})"
      case SkewX(a) =>
        s"skewX(${CssValue.of(a)})"
      case SkewY(a) =>
        s"skewY(${CssValue.of(a)})"
      case Translate(tx, ty) =>
        s"translate($tx, $ty)"
      case Translate3d(tx, ty, tz) =>
        s"translate3d($tx, $ty, $tz)"
      case TranslateX(t) =>
        s"translateX($t)"
      case TranslateY(t) =>
        s"translateY($t)"
      case TranslateZ(t) =>
        s"translateZ($t)"
    }
  }

  def matrix(a: Double, b: Double, c: Double, d: Double, tx: Double = 0, ty: Double = 0): Matrix2d =
    Matrix2d(a, b, c, d, tx, ty)

  def matrix(a1: Double, a2: Double, a3: Double, a4: Double,
             b1: Double, b2: Double, b3: Double, b4: Double,
             c1: Double, c2: Double, c3: Double, c4: Double,
             d1: Double, d2: Double, d3: Double, d4: Double): Matrix3d =
    Matrix3d(
      a1, a2, a3, a4,
      b1, b2, b3, b4,
      c1, c2, c3, c4,
      d1, d2, d3, d4
    )

  def perspective(l: Double): Perspective = Perspective(l)

  def rotate(a: Angle): Rotate = Rotate(a)
  def rotate(x: Double, y: Double, z: Double, a: Angle): Rotate3d = Rotate3d(x, y, z, a)
  def rotateX(a: Angle): RotateX = RotateX(a)
  def rotateY(a: Angle): RotateY = RotateY(a)
  def rotateZ(a: Angle): RotateZ = RotateZ(a)

  def scale(sx: Double, sy: Double = 1): Scale = Scale(sx, sy)
  def scale(sx: Double, sy: Double, sz: Double): Scale3d = Scale3d(sx, sy, sz)
  def scaleX(s: Double): ScaleX = ScaleX(s)
  def scaleY(s: Double): ScaleY = ScaleY(s)
  def scaleZ(s: Double): ScaleZ = ScaleZ(s)

  def skew(ax: Angle, ay: Angle = Angle.rad(0)): Skew = Skew(ax, ay)
  def skewX(a: Angle): SkewX = SkewX(a)
  def skewY(a: Angle): SkewY = SkewY(a)

  def translate(tx: Double, ty: Double = 0): Translate = Translate(tx, ty)
  def translate(tx: Double, ty: Double, tz: Double): Translate3d = Translate3d(tx, ty, tz)
  def translateX(t: Double): TranslateX = TranslateX(t)
  def translateY(t: Double): TranslateY = TranslateY(t)
  def translateZ(t: Double): TranslateZ = TranslateZ(t)

}
