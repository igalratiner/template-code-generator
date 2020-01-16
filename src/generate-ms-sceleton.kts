import java.io.File
import kotlin.streams.toList
import kotlin.system.exitProcess

class Config {
    val templateDir: File
    val outputDir: File
    private val name1: String
    private val name2: String
    val substitutionMap: Map<String, String>

    constructor( args: Array<String>) {
        this.templateDir = File(args[0])
        this.outputDir = File(args[1])
        name1 = args[2].toLowerCase()
        name2 = args[3].toLowerCase()
        substitutionMap = mapOf(
            "__name__" to "${name1}-${name2}",
            "__camelname__" to "${name1.capitalize()}${name2.capitalize()}",
            "__packagename__" to "${name1}${name2}",
            "__methodname__" to "${name1}${name2.capitalize()}")
    }
}

fun substitute(str: String, cfg: Config): String {
    var currStr = "" + str
    cfg.substitutionMap.forEach{ (pattern, value) ->
        currStr = currStr.replace(pattern, value)
    }
    return currStr
}

fun substituteFileName(file: File): File {
    val newName = "${file.absolutePath.substringBeforeLast("/")}/${substitute(file.name, cfg)}"
    return File(newName)
}

val cfg = Config(args)
println("${cfg.outputDir.absolutePath} ${cfg.templateDir.absolutePath}")

val rootDir = substituteFileName(File("${cfg.outputDir}/${cfg.templateDir.name}"))
rootDir.mkdir()

println("Root dir: ${rootDir.absolutePath}")

if(!cfg.templateDir.copyRecursively(rootDir)) {
    println("Template copy from ${cfg.templateDir.absolutePath} to ${cfg.outputDir.absolutePath} fault")
    exitProcess(-1)
}

rootDir.walkBottomUp().forEach { it.renameTo(substituteFileName(it)) }

rootDir.walkBottomUp().filter { it.isFile }.forEach {
    println("---> ${it.absolutePath}")
    val content = it.readLines().stream().map {l -> substitute(l, cfg)}.toList().joinToString("\n")
    it.writeText(content)
}