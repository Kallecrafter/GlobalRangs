import net.labymod.labygradle.common.extension.LabyModAnnotationProcessorExtension.ReferenceType

dependencies {
    labyProcessor()
    api(project(":api"))
    addonMavenDependency("org.jeasy:easy-random:5.0.0")
}

labyModAnnotationProcessor {
    referenceType = ReferenceType.DEFAULT
}