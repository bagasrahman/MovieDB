import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.*

fun DependencyHandler.libraries() {

    androidX()
    androidPaging()
    DaggerHilt()
    glide()
    gson()
    gander()
    materialDesign()
    NavGraph()
    okHttp()
    retrofit()
    vmLifeCycle()
    googleFirebase()
}