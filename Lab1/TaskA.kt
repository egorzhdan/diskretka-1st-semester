import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Egor Zhdan
 */
object Input {
    private var tokenizer = StringTokenizer("")

    fun nextInt(): Int {
        if (!tokenizer.hasMoreTokens()) tokenizer = StringTokenizer(readLine()!!)
        return tokenizer.nextToken().toInt()
    }
}


fun main(args: Array<String>) {
    val n = Input.nextInt()
    val m = Input.nextInt()
    val graph = Array(n * 2 + 1, { HashSet<Int>() })
    val reversed = Array(n * 2 + 1, { HashSet<Int>() })
    repeat(m) {
        val v1 = Input.nextInt()
        val v2 = Input.nextInt()

        graph[n - v1].add(n + v2) // !v1 -> v2
        graph[n - v2].add(n + v1) // !v2 -> v1

        reversed[n + v2].add(n - v1)
        reversed[n + v1].add(n - v2)
    }

    val visited = BooleanArray(graph.size)
    val order = ArrayList<Int>(graph.size)
    fun topSort(v: Int) {
        visited[v] = true
        graph[v].forEach { if (!visited[it]) topSort(it) }
        order.add(v)
    }

    (0 until graph.size).forEach { if (!visited[it]) topSort(it) }

    visited.fill(false)
    val components = IntArray(graph.size, { -1 })
    fun findComponents(v: Int, compIdx: Int) {
        visited[v] = true
        components[v] = compIdx
        reversed[v].forEach { if (!visited[it]) findComponents(it, compIdx) }
    }
    order.asReversed().forEachIndexed { i, v ->
        if (!visited[v]) findComponents(v, i)
    }

    if ((1..n).firstOrNull { components[n + it] == components[n - it] } == null) {
        println("NO")
    } else {
        println("YES")
    }
}
