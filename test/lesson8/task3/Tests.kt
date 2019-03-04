package lesson8.task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {

    @Test
    @Tag("Example")
    fun bfs() {
        val graph = Graph()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addVertex("D")
        graph.addVertex("E")
        graph.addVertex("F")
        graph.addVertex("G")
        graph.addVertex("H")
        graph.connect("A", "B")
        graph.connect("B", "C")
        graph.connect("B", "D")
        graph.connect("C", "E")
        graph.connect("D", "F")
        graph.connect("C", "F")
        graph.connect("G", "H")
        assertEquals(0, graph.bfs("A", "A"))
        assertEquals(3, graph.bfs("A", "F"))
        assertEquals(2, graph.bfs("E", "F"))
        assertEquals(3, graph.bfs("E", "D"))
        assertEquals(1, graph.bfs("H", "G"))
        assertEquals(-1, graph.bfs("H", "A"))
    }


    @Test
    @Tag("Example")
    fun dfs() {
        val graph = Graph()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addVertex("D")
        graph.addVertex("E")
        graph.addVertex("F")
        graph.addVertex("G")
        graph.addVertex("H")
        graph.connect("A", "B")
        graph.connect("B", "C")
        graph.connect("B", "D")
        graph.connect("C", "E")
        graph.connect("D", "F")
        graph.connect("C", "F")
        graph.connect("G", "H")
        assertEquals(0, graph.dfs("A", "A"))
        assertEquals(1, graph.dfs("A", "B"))
        assertEquals(2, graph.dfs("A", "C"))
        assertEquals(2, graph.dfs("B", "F"))
        assertEquals(3, graph.dfs("A", "F"))
        assertEquals(2, graph.dfs("E", "F"))
        assertEquals(3, graph.dfs("E", "D"))
        assertEquals(1, graph.dfs("H", "G"))
        assertEquals(-1, graph.dfs("H", "A"))
    }


}

class Test {
    @Test
    fun filftyfuck(){

        assertEquals("14539.718 кг", fuck(" 231.2458 кг * 62.8756 г"))
        assertEquals("10.0 кг", fuck(" 5 кг + 5 кг"))
        assertEquals("7.0 кг", fuck("70 кг - 63 кг"))
        assertEquals("7.0 кг", fuck("70 кг / 10 кг"))
        assertEquals("28.0 кг", fuck("7 кг * 4 кг"))
        assertEquals("7.0 кг", fuck("35 кг ~ 5 кг"))
        assertEquals(false, fuck("7 кг = 4 кг"))
        assertEquals(true, fuck("4 кг = 4 кг"))

    }
}

