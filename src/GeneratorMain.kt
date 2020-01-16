
class Config {
    private val templateDir: String
    private val outputDir: String
    private val name1: String
    private val name2: String

    constructor( args: Array<String>) {
        this.templateDir = args[0]
        this.outputDir = args[1]
        val names = args[2].split("-")
        name1 = names[0].toLowerCase()
        name2 = names[1].toLowerCase()
    }

    fun serviceName() = "${name1}-${name2}"
    fun serviceCamelName() = "${name1}${name2.capitalize()}"

}

