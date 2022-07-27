@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

const val RADIAN_DEGREES = 180 / PI

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = sqrt((this.dx).pow(2) + (this.dy).pow(2))

  val radiant: Double
    get() = atan2(this.dy, this.dx)

  val degree: Double
    get() = RADIAN_DEGREES * this.radiant

  val unit: Vector2D
    get() = this.div(this.magnitude)

  val normal: Vector2D
    get() = Vector2D(this.dy, -this.dx).unit

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(this.dx * scalar, this.dy * scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    if (scalar == 0.0) {
      return INVALID_VECTOR
    }
    return Vector2D(this.dx / scalar, this.dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return ((this.dx * v.dx) + (this.dy * v.dy))
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(this.dx + v.dx, this.dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(this.dx + p.x, this.dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(-this.dx, -this.dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(this.dx - v.dx, this.dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return this.times(target) / target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return target.unit.times(this.scalarProject(target))
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(this.times(v.dx), this.times(v.dy))
}
