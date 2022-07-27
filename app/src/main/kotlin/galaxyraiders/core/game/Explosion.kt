package galaxyraiders.core.game

import galaxyraiders.Config
import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

object ExplosionConfig {
  private val config = Config(prefix = "GR__CORE__GAME__EXPLOSION__")

  val lifespan = config.get<Int>("LIFESPAN")
}

class Explosion(
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double
) :
  SpaceObject("Explosion", '*', initialPosition, initialVelocity, radius, mass) {
  private var frameCounter: Int = 0

  public fun stillAliveAfterFrame(): Boolean {
    this.frameCounter++
    return(this.frameCounter <= ExplosionConfig.lifespan)
  }
}
