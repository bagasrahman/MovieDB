import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.*

fun DependencyHandler.libraries() {

    androidX()
    androidPaging()
    DaggerHilt()
    glide()
    gson()
    gander()
    youtubePlayer()
    materialDesign()
    NavGraph()
    okHttp()
    retrofit()
    vmLifeCycle()
    googleFirebase()
}