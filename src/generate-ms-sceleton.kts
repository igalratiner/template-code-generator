import java.io.File
import kotlin.streams.toList

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

val cfg = Config(args)
println("${cfg.outputDir.absolutePath} ${cfg.templateDir.absolutePath}")
var outputDir = cfg.outputDir

cfg.templateDir.copyRecursively(outputDir, overwrite = true)

cfg.outputDir.walkBottomUp().forEach {
    val newName = "${it.absolutePath.substringBeforeLast("/")}/${substitute(it.name, cfg)}"
    it.renameTo(File(newName))
}

cfg.outputDir.walkBottomUp().filter { it.isFile }.forEach {
    println("--- file ${it.absolutePath}")
    val content = it.readLines().stream().map {l -> substitute(l, cfg)}.toList().joinToString("\n")
    it.writeText(content)
    println("END --- file ${it.absolutePath}")
}