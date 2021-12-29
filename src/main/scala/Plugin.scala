import javax.servlet.ServletContext
import gitbucket.core.plugin.{PluginRegistry}
import gitbucket.core.service.SystemSettingsService
import gitbucket.core.service.SystemSettingsService.SystemSettings
import io.github.gitbucket.solidbase.model.Version

import scala.util.Try

class Plugin extends gitbucket.core.plugin.Plugin {
  override val pluginId: String = "3dfile"
  override val pluginName: String = "3d file renderer Plugin"
  override val description: String = "Rendering 3d file by three.js"
  override val versions: List[Version] = List(
    new Version("1.0.0"),
    new Version("1.0.1"),
    new Version("1.0.2"),
  )

  override val assetsMappings = Seq("/threed" -> "/threed/assets")

  private[this] var renderer: Option[ThreedRenderer] = None

  override def initialize(registry: PluginRegistry, context: ServletContext, settings: SystemSettingsService.SystemSettings): Unit = {
    val test = Try{ new ThreedRenderer() }
    val threed = test.get
    registry.addRenderer("stl", threed)
    renderer = Option(threed)
    super.initialize(registry, context, settings)
  }

  override def shutdown(registry: PluginRegistry, context: ServletContext, settings: SystemSettings): Unit = {
    renderer.map(r => r.shutdown())
  }

}