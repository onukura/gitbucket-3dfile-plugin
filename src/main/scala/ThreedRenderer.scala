import gitbucket.core.controller.Context
import gitbucket.core.plugin.{RenderRequest, Renderer}
import gitbucket.core.service.RepositoryService.RepositoryInfo

import play.twirl.api.Html

class ThreedRenderer extends Renderer {

  def render(request: RenderRequest): Html = {
    import request._
    Html(toHtml()(context))
  }

  def toHtml()(implicit context: Context): String = {

    val path = context.baseUrl

    val renderStyles =
      s"""
         |<link rel="stylesheet" type="text/css" href="$path/plugin-assets/threed/style.css">
         |""".stripMargin

    val renderMaterials =
      """
        |<div class="row">
        |  <div id="stl-viewer"></div>
        |</div>
        |<div class="row" style="background-color:#F3F3F3;">
        |  <div class="btn-group btn-group-toggle" id="stl-control" data-toggle="buttons">
        |    <label class="btn">
        |      <input type="radio" name="stl-options">Wireframe
        |    </label>
        |    <label class="btn">
        |      <input type="radio" name="stl-options" checked>Solid
        |    </label>
        |  </div>
        |</div>
        |""".stripMargin

    val renderAssets =
      s"""
         |<script src="$path/plugin-assets/threed/three.js"></script>
         |<script src="$path/plugin-assets/threed/STLLoader.js"></script>
         |<script src="$path/plugin-assets/threed/Detector.js"></script>
         |<script src="$path/plugin-assets/threed/OrbitControls.js"></script>
         |<script src="$path/plugin-assets/threed/handLoader.js"></script>
         |""".stripMargin

    val renderFunctions =
      s"""
         |<script>
         |  function myrender() {
         |    init("${context.request.getRequestURL + "?raw=true"}", document.getElementById("stl-viewer"), document.getElementsByName("stl-options"))
         |  }
         |</script>
         |""".stripMargin

    val renderUpdater =
      """
        |<script>
        |  $(function() {
        |    myrender()
        |  })
        |  $('#stl-control :input').change(function() {
        |    myrender()
        |  });
        |</script>
        |""".stripMargin

    s"""
       |$renderStyles
       |$renderMaterials
       |$renderAssets
       |$renderFunctions
       |$renderUpdater
       |""".stripMargin

  }

  def shutdown(): Unit = {
  }

}
