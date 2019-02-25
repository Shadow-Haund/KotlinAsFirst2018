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

    fun multiplication(another: Double): Double = num * another

    fun division(number: Double): Double = num / number

    fun divisionWithDisc(another: Disc): Double = num / another.num

    fun comparison(another: Disc): Boolean = num == another.num
}

fun mainOperation(receive: String, one: Disc, another: Disc, number: Double) {
    val rec = "   5 кг   "
    if (rec.matches(Regex("""\s*\d+\s+[а-яА-ЯёЁ]+\s*"""))) {
        Regex("""\s+""").replace(receive, " ").trim()
        val per = rec.split(" ")
        one.num = per[0].toDouble()
        one.str = per[1]
        another.num = 5.0
        another.str = "кг"
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

fun main(args: Array<String>) {
    return mainOperation
}

