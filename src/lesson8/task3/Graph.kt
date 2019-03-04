package lesson8.task3

import java.util.*

class Graph {
    private data class Vertex(val name: String) {
        val neighbors = mutableSetOf<Vertex>()
    }

    private val vertices = mutableMapOf<String, Vertex>()

    private operator fun get(name: String) = vertices[name] ?: throw IllegalArgumentException()

    fun addVertex(name: String) {
        vertices[name] = Vertex(name)
    }

    private fun connect(first: Vertex, second: Vertex) {
        first.neighbors.add(second)
        second.neighbors.add(first)
    }

    fun connect(first: String, second: String) = connect(this[first], this[second])

    /**
     * Пример
     *
     * По двум вершинам рассчитать расстояние между ними = число дуг на самом коротком пути между ними.
     * Вернуть -1, если пути между вершинами не существует.
     *
     * Используется поиск в ширину
     */
    fun bfs(start: String, finish: String) = bfs(this[start], this[finish])

    private fun bfs(start: Vertex, finish: Vertex): Int {
        val queue = ArrayDeque<Vertex>()
        queue.add(start)
        val visited = mutableMapOf(start to 0)
        while (queue.isNotEmpty()) {
            val next = queue.poll()
            val distance = visited[next]!!
            if (next == finish) return distance
            for (neighbor in next.neighbors) {
                if (neighbor in visited) continue
                visited.put(neighbor, distance + 1)
                queue.add(neighbor)
            }
        }
        return -1
    }

    /**
     * Пример
     *
     * По двум вершинам рассчитать расстояние между ними = число дуг на самом коротком пути между ними.
     * Вернуть -1, если пути между вершинами не существует.
     *
     * Используется поиск в глубину
     */
    fun dfs(start: String, finish: String): Int = dfs(this[start], this[finish], setOf()) ?: -1

    private fun dfs(start: Vertex, finish: Vertex, visited: Set<Vertex>): Int? =
            if (start == finish) 0
            else {
                val min = start.neighbors
                        .filter { it !in visited }
                        .mapNotNull { dfs(it, finish, visited + start) }
                        .min()
                if (min == null) null else min + 1
            }
}


data class Disc(var num: Double, var str: String) {
    fun addition(another: Disc): Double = num + another.num

    fun subtraction(another: Disc): Double = num - another.num

    fun multiplication(another: Disc): Double = num * another.num

    fun division(another: Disc): Double = num / another.num

    fun divisionWithDisc(another: Disc): Double = num / another.num

    fun comparison(another: Disc): Boolean = num == another.num

    fun fromMMtoM(one: Disc) = num * 1000

    fun fromSMtoM(one: Disc) = num * 100

    fun fromDMtoM(one: Disc) = num * 10

    fun fromKMtoM(one: Disc) = ((num * 1000).toInt().toDouble() / 1000)

    fun fromGtoKG(one: Disc) = num * 1000

    fun fromZtoKG(one: Disc) = ((num * 100).toInt().toDouble() / 100)

    fun fromTtoKG(one: Disc) = ((num * 1000).toInt().toDouble() / 1000)

}


fun fuck(rece: String): Any {
    val one = Disc(0.0, "")
    val another = Disc(0.0, "")
    if (rece.matches(Regex("""\s*\d+[.]?\d*\s+[а-яА-ЯёЁ]+\s+[-+*/~=]\s+\d+[.]?\d*\s+[а-яА-ЯёЁ]+\s*"""))) {
        Regex("""\s+""").replace(rece, " ").trim()
        val per = rece.split(" ").toMutableList()
        if ("" in per) per.remove("")
        one.num = per[0].toDouble()
        one.str = per[1]
        val condition = per[2]
        another.num = per[3].toDouble()
        another.str = per[4]

        val listOfWeight = listOf("г", "кг", "ц", "т")
        val listOfDistance = listOf("мм", "см", "дм", "м", "км")
        val listOfTime = listOf("мс", "с", "мин", "ч", "день", "мес", "год")
        if ((one.str !in listOfDistance && one.str !in listOfTime && one.str !in listOfWeight) ||
                another.str !in listOfDistance && another.str !in listOfTime && another.str !in listOfWeight) {
            throw IllegalArgumentException("Не указана размерность")
        }
        if (one.str in listOfTime && another.str !in listOfTime || one.str in listOfDistance && another.str !in listOfDistance ||
                one.str in listOfWeight && another.str !in listOfWeight ||
                one.str in listOfTime && another.str !in listOfTime || one.str in listOfTime && another.str !in listOfTime ||
                one.str in listOfTime && another.str !in listOfTime) {
            throw IllegalArgumentException("Размерности не соответствуют друг другу")
        }
        var numRez = 0.0
        val bool: Boolean
        if (condition == "+" && one.str == "км")
            numRez = one.addition(another)
        if (condition == "-" && one.str == another.str)
            numRez = one.subtraction(another)
        if (condition == "/")
            numRez = one.division(another)
        if (condition == "*")
            numRez = one.multiplication(another)
        if (condition == "~" && one.str == another.str)
            numRez = one.divisionWithDisc(another)
        if (condition == "=" && one.str == another.str) {
            bool = one.comparison(another)
            return bool
        }
        /*val subRez: String
        if ('.' in numRez) {
            subRez = numRez.substring(0, numRez.indexOf('.', 0) + 4)
            return "$subRez ${one.str}"
        }*/
        numRez = ((numRez * 1000).toInt().toDouble() / 1000)
        return "$numRez ${one.str}"
    } else throw IllegalArgumentException("Неверный формат ввода")
}


/*
fun mainOperation():Double {
    val number = 7.0
    val receive = "   5 кг   "
    if (receive.matches(Regex("""\s*\d+\s+[а-яА-ЯёЁ]+\s*"""))) {
        Regex("""\s+""").replace(receive, " ").trim()
        val per = receive.split(" ")
        val one = Disc(per[0].toDouble(), per[1])
        val another = Disc(5.0, "кг")
        val condition = "+"
        if (condition == "+" && one.str == another.str)
            one.addition(another)
        if (condition == "-" && one.str == another.str)
            one.subtraction(another)
        if (condition == "*")
            one.multiplication(number)
        if (condition == "/")
            one.division(number)
        if (condition == "//" && one.str == another.str)
            one.divisionWithDisc(another)
        if (condition == "==" && one.str == another.str)
            one.comparison(another)
    } else throw IllegalArgumentException("Неверный формат ввода")
}
*/

